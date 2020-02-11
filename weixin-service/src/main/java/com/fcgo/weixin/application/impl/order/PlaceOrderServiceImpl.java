package com.fcgo.weixin.application.impl.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.dto.OrderConfirmDetailDTO;
import com.fcgo.weixin.application.dto.OrderConfirmProductDTO;
import com.fcgo.weixin.application.order.PlaceOrderService;
import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.common.constants.OrderStateConstants;
import com.fcgo.weixin.common.constants.PayWayType;
import com.fcgo.weixin.common.constants.ProductSource;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.OrderNumberUtil;
import com.fcgo.weixin.persist.dao.IOrderInfoDAO;
import com.fcgo.weixin.persist.dao.IOrderItemDAO;
import com.fcgo.weixin.persist.dao.IParentOrderInfoDAO;
import com.fcgo.weixin.persist.dao.IProductSpecDAO;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import com.fcgo.weixin.persist.po.OrderItemPO;
import com.fcgo.weixin.persist.po.ParentOrderInfoPO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.fcgo.weixin.persist.po.SellerShopPO;
import com.fcgo.weixin.persist.po.UserAddressPO;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private IOrderInfoDAO orderInfoDAO;

    @Autowired
    private IOrderItemDAO orderItemDAO;

    @Autowired
    private IProductSpecDAO productSpecDAO;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService imageService;

    @Autowired
    private SellerShopService sellerShopService;

    @Autowired
    private IParentOrderInfoDAO parentOrderInfoDAO;

    @Override
    public List<OrderConfirmProductDTO> resolveProductInfoJson(String tempProductInfoJson) {
        List<OrderConfirmProductDTO> list = new ArrayList<OrderConfirmProductDTO>();
        if (tempProductInfoJson != null) {
            JSONArray jsonArray = JSONArray.fromObject(tempProductInfoJson);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int productId = Integer.parseInt(jsonObject.get("productId").toString());
                int specId = Integer.parseInt(jsonObject.get("specId").toString().trim());
                int productCount = Integer.parseInt(jsonObject.get("productCount").toString());
                OrderConfirmProductDTO orderConfirmProductDTO =
                        new OrderConfirmProductDTO(productId, productCount, specId);
                // 判断这个商品是自营的还是平台的
                ProductPO productPO = productService.getById(productId);
                if (productPO != null && productPO.getFromType() == 1) {
                    ProductSpecPO productSpecPO = productSpecDAO.selectByPrimaryKey(specId);
                    orderConfirmProductDTO.setProductPrice(productSpecPO.getSalesPrice());
                    orderConfirmProductDTO.setPicUrl(imageService.getImageUrlByProductId(productId));
                }
                if (productPO != null && productPO.getFromType() == 2) {
                    orderConfirmProductDTO.setProductPrice(null);
                    orderConfirmProductDTO.setPicUrl(null);
                }
                list.add(orderConfirmProductDTO);
            }
        }
        return list;

    }

    @Override
    public BigDecimal getOrderTotalMoney(List<OrderConfirmDetailDTO> orderConfirmDetailDTOs) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderConfirmDetailDTO orderConfirmDetailDTO : orderConfirmDetailDTOs) {
            for (OrderConfirmProductDTO orderConfirmProductDTO : orderConfirmDetailDTO.getOrderConfirmProductDTOs()) {
                BigDecimal productTotalPrice = new BigDecimal(0);
                ProductPO productPO = productService.getById(orderConfirmProductDTO.getProductId());
                if (productPO != null && productPO.getFromType() == 1) {
                    ProductSpecPO productSpecPO = productSpecDAO.selectByPrimaryKey(orderConfirmProductDTO.getSpecId());
                    productTotalPrice =
                            productSpecPO.getSalesPrice().multiply(
                                    new BigDecimal(orderConfirmProductDTO.getProductCount()));
                }
                if (productPO != null && productPO.getFromType() == 2) {
                    orderConfirmProductDTO.setProductPrice(null);
                }
                totalPrice = totalPrice.add(productTotalPrice);
            }
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public int placeOrder(List<OrderConfirmDetailDTO> orderConfirmDetailDTOList, BaseSessionUserDTO baseSessionUserDTO,
            UserAddressPO userAddressPO) {
        // 先生成一条主订单
        ParentOrderInfoPO parentOrderInfoPO = new ParentOrderInfoPO();
        parentOrderInfoPO.setOrderNumber(OrderNumberUtil.generateOrderNo());
        parentOrderInfoPO.setCreateTime(new Date());
        parentOrderInfoPO.setCreateName(baseSessionUserDTO.getNickName());
        parentOrderInfoDAO.insert(parentOrderInfoPO);
        for (OrderConfirmDetailDTO orderConfirmDetailDTO : orderConfirmDetailDTOList) {
            SellerShopPO sellerShopPO =
                    sellerShopService
                            .findByParam(null, Integer.valueOf(orderConfirmDetailDTO.getSellerId()).toString());
            OrderInfoPO orderInfoPO = new OrderInfoPO();
            orderInfoPO.setOrderNum(OrderNumberUtil.generateOrderNo());
            orderInfoPO.setCreateName(baseSessionUserDTO.getNickName());
            orderInfoPO.setCreateTime(new Date());
            orderInfoPO.setUpdateTime(new Date());
            orderInfoPO.setUpdateName(baseSessionUserDTO.getNickName());
            orderInfoPO.setUserId(baseSessionUserDTO.getUserId());
            orderInfoPO.setSellerId(orderConfirmDetailDTO.getSellerId());
            orderInfoPO.setShopId(sellerShopPO.getId());
            orderInfoPO.setPayWay(PayWayType.WEIXIN.getValue());
            orderInfoPO.setProAmount(getOrderTotalMoney(orderConfirmDetailDTOList));
            orderInfoPO.setFreightAmount(getOrderTotalFreight(orderConfirmDetailDTOList));
            orderInfoPO.setActPayAmount(getOrderTotalMoney(orderConfirmDetailDTOList).add(
                    getOrderTotalFreight(orderConfirmDetailDTOList)));
            orderInfoPO.setIsEaluate(0);
            orderInfoPO.setOrderType(Integer.valueOf(ProductSource.SELFSALE.getKey()));
            orderInfoPO.setSellerRemarks(orderConfirmDetailDTO.getRemark());
            orderInfoPO.setOrderState(OrderStateConstants.WAIT_PAY.getKey());
            orderInfoPO.setIsDelete(0);
            orderInfoPO.setParentOrderId(parentOrderInfoPO.getId());
            orderInfoDAO.insert(orderInfoPO);
            for (OrderConfirmProductDTO orderConfirmProductDTO : orderConfirmDetailDTO.getOrderConfirmProductDTOs()) {
                ProductPO productPO = productService.getById(orderConfirmProductDTO.getProductId());
                OrderItemPO orderItemPO = new OrderItemPO();
                orderItemPO.setCreateName(baseSessionUserDTO.getNickName());
                orderItemPO.setCreateTime(new Date());
                orderItemPO.setUpdateTime(new Date());
                orderItemPO.setUpdateName(baseSessionUserDTO.getNickName());
                orderItemPO.setOrderId(orderInfoPO.getId());
                orderItemPO.setProductId(orderConfirmProductDTO.getProductId());
                orderItemPO.setProductSpec(orderConfirmProductDTO.getProductSpecName());
                orderItemPO.setProductName(productPO.getProName());
                if (productPO != null && productPO.getFromType() == 1) {
                    ProductSpecPO productSpecPO = productSpecDAO.selectByPrimaryKey(orderConfirmProductDTO.getSpecId());
                    orderItemPO.setProductPrice(productSpecPO.getSalesPrice());
                    orderItemPO.setProductTotalAmount(orderItemPO.getProductPrice().multiply(
                            new BigDecimal(orderConfirmProductDTO.getProductCount())));
                }
                if (productPO != null && productPO.getFromType() == 2) {
                    orderConfirmProductDTO.setProductPrice(null);
                    orderItemPO.setProductTotalAmount(null);
                    orderItemPO.setFcgProductPrice(null);
                }
                orderItemPO.setReturnState(0);
                orderItemPO.setProductPicUrl(orderConfirmProductDTO.getPicUrl());
                orderItemPO.setIsDelete(0);
                orderItemPO.setProductQuant(orderConfirmProductDTO.getProductCount());
                orderItemDAO.insert(orderItemPO);

            }
        }
        return parentOrderInfoPO.getId();
    }

    @Override
    public BigDecimal getOrderTotalFreight(List<OrderConfirmDetailDTO> orderConfirmDetailDTOs) {
        BigDecimal totalFreight = new BigDecimal(0);
        for (OrderConfirmDetailDTO orderConfirmDetailDTO : orderConfirmDetailDTOs) {
            for (OrderConfirmProductDTO orderConfirmProductDTO : orderConfirmDetailDTO.getOrderConfirmProductDTOs()) {
                BigDecimal productTotalFreight = new BigDecimal(0);
                ProductPO productPO = productService.getById(orderConfirmProductDTO.getProductId());
                if (productPO != null && productPO.getFromType() == 1) {
                    productTotalFreight =
                            productPO.getFreight().multiply(new BigDecimal(orderConfirmProductDTO.getProductCount()));
                }
                if (productPO != null && productPO.getFromType() == 2) {
                    orderConfirmProductDTO.setProductPrice(null);
                }
                totalFreight = totalFreight.add(productTotalFreight);
            }
        }
        return totalFreight;
    }

    @Override
    public List<OrderConfirmDetailDTO> resolveOrderInfoJson(String tempProductInfoJson) {
        List<OrderConfirmDetailDTO> orderConfirmDetailDTOs = new ArrayList<OrderConfirmDetailDTO>();
        Map<Integer, List<OrderConfirmProductDTO>> parm = new HashMap<Integer, List<OrderConfirmProductDTO>>();
        JSONArray jsonArray = JSONArray.fromObject(tempProductInfoJson);
        for (int i = 0; i < jsonArray.size(); i++) {
            OrderConfirmDetailDTO orderConfirmDetailDTO = new OrderConfirmDetailDTO();
            List<OrderConfirmProductDTO> orderConfirmProductDTOs = new ArrayList<OrderConfirmProductDTO>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int sellerId = Integer.parseInt(jsonObject.get("sellerId").toString());
            if (parm.keySet().contains(sellerId)) {
                orderConfirmDetailDTO.setSellerId(sellerId);
                SellerShopPO sellerShopPO = sellerShopService.findByParam(null, Integer.valueOf(sellerId).toString());
                orderConfirmDetailDTO.setShopName(sellerShopPO.getShopName());
                orderConfirmDetailDTO.setShopId(sellerShopPO.getId());
                int productId = Integer.parseInt(jsonObject.get("productId").toString());
                int specId = Integer.parseInt(jsonObject.get("specId").toString().trim());
                int productCount = Integer.parseInt(jsonObject.get("productCount").toString());
                OrderConfirmProductDTO orderConfirmProductDTO =
                        new OrderConfirmProductDTO(productId, specId, productCount);
                // 判断这个商品是自营的还是平台的
                ProductPO productPO = productService.getById(productId);
                if (productPO != null && productPO.getFromType() == 1) {
                    ProductSpecPO productSpecPO = productSpecDAO.selectByPrimaryKey(specId);
                    orderConfirmProductDTO.setProductPrice(productSpecPO.getSalesPrice());
                    orderConfirmProductDTO.setPicUrl(imageService.getImageUrlByProductId(productId));
                }
                if (productPO != null && productPO.getFromType() == 2) {
                    orderConfirmProductDTO.setProductPrice(null);
                    orderConfirmProductDTO.setPicUrl(null);
                }
                orderConfirmProductDTO.setProductName(productPO.getProName());
                orderConfirmProductDTOs.add(orderConfirmProductDTO);
                orderConfirmDetailDTO.setOrderConfirmProductDTOs(orderConfirmProductDTOs);
                orderConfirmDetailDTOs.add(orderConfirmDetailDTO);
                continue;
            }
            orderConfirmDetailDTO.setSellerId(sellerId);
            SellerShopPO sellerShopPO = sellerShopService.findByParam(null, Integer.valueOf(sellerId).toString());
            orderConfirmDetailDTO.setShopName(sellerShopPO.getShopName());
            orderConfirmDetailDTO.setShopId(sellerShopPO.getId());
            int productId = Integer.parseInt(jsonObject.get("productId").toString());
            int specId = Integer.parseInt(jsonObject.get("specId").toString().trim());
            int productCount = Integer.parseInt(jsonObject.get("productCount").toString());
            OrderConfirmProductDTO orderConfirmProductDTO = new OrderConfirmProductDTO(productId, specId, productCount);
            // 判断这个商品是自营的还是平台的
            ProductPO productPO = productService.getById(productId);
            if (productPO != null && productPO.getFromType() == 1) {
                ProductSpecPO productSpecPO = productSpecDAO.selectByPrimaryKey(specId);
                orderConfirmProductDTO.setProductPrice(productSpecPO.getSalesPrice());
                orderConfirmProductDTO.setPicUrl(imageService.getImageUrlByProductId(productId));
                orderConfirmProductDTO.setProductSpecName(productSpecPO.getSpecName());
            }
            if (productPO != null && productPO.getFromType() == 2) {
                // TODO
                orderConfirmProductDTO.setProductPrice(null);
                orderConfirmProductDTO.setPicUrl(null);
                orderConfirmProductDTO.setProductSpecName(null);
            }
            orderConfirmProductDTO.setProductName(productPO.getProName());
            orderConfirmProductDTOs.add(orderConfirmProductDTO);
            orderConfirmDetailDTO.setOrderConfirmProductDTOs(orderConfirmProductDTOs);
            orderConfirmDetailDTOs.add(orderConfirmDetailDTO);
            parm.put(sellerId, orderConfirmProductDTOs);
        }
        return orderConfirmDetailDTOs;
    }
}
