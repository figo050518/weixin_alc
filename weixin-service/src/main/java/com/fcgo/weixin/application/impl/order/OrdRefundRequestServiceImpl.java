package com.fcgo.weixin.application.impl.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.order.IOrdRefundRequestService;
import com.fcgo.weixin.application.order.IOrderItemService;
import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.common.constants.UserType;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.OrderNumberUtil;
import com.fcgo.weixin.persist.generate.criteria.OrderRefundRequestCriteria;
import com.fcgo.weixin.persist.po.OrderRefundImagePO;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;
import com.fcgo.weixin.persist.po.ProductPO;

@Service
@Transactional
public class OrdRefundRequestServiceImpl implements IOrdRefundRequestService {
    @Autowired
    private IOrderRefundRequestDAO orderRefundRequestDAO;
    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderRefundImageDAO orderRefundImageDAO;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IProductImageService imageService;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Page findOrdRufundBypage(BaseSessionUserDTO baseSessionUserDTO, String refundStatus, int pageIndex) {
        Page page = new Page();
        // 更新当前页码
        if (pageIndex >= 1) {
            page.setPageIndex(pageIndex);
        }
        // 当订单售后状态为-1全部订单 清空售后订单状态
        if (StringUtils.isNotBlank(refundStatus) && refundStatus.equals("-1")) {
            refundStatus = null;
        }
        // 参数map
        Map parm = new HashMap();
        // 用户ID
        parm.put("userId", baseSessionUserDTO.getUserId());
        // 用户类型
        if (baseSessionUserDTO.getIsBuyer()) {
            // 买家
            parm.put("userType", UserType.BUYER.getKey());
        }
        else {
            // 卖家
            parm.put("userType", UserType.SELLER.getKey());
        }
        // 订单状态 （可空）
        parm.put("refundStatus", refundStatus);
        // 默认排序字段 方式（如果需要可更改）
        parm.put("sorter", "updateTime desc");
        // 分页信息
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("endPage", page.getPageIndex() * page.getPageSize());
        // 获取分页数据
        List<OrderRefundRequestPO> orderRefundRequestPOs = orderRefundRequestDAO.findOrdRefundByPage(parm);
        // 分页总数量
        int orderRefundCount = orderRefundRequestDAO.getCountBypage(parm);
        page.setRow(orderRefundRequestPOs);
        page.setRecords(orderRefundCount);
        return page;
    }

    @Override
    public OrderRefundRequestPO findById(int id) {
        return orderRefundRequestDAO.selectByPrimaryKey(id);
    }

    @Override
    public String updateTransferFcg(int id, BaseSessionUserDTO baseSessionUserDTO) {
        OrderRefundRequestPO refundRequestPO = this.findById(id);
        refundRequestPO.setUpdateName(baseSessionUserDTO.getNickName());
        refundRequestPO.setUpdateTime(new Date());
        refundRequestPO.setFcgRefundFlag(1);
        orderRefundRequestDAO.updateByPrimaryKey(refundRequestPO);
        return "1";
    }

    @Override
    public String updateAuditBySeller(int id, String refundStatus, String redundsReason,
            BaseSessionUserDTO baseSessionUserDTO) {
        OrderRefundRequestPO refundRequestPO = this.findById(id);
        if (baseSessionUserDTO.getIsBuyer()) {
            refundRequestPO.setCancelFlag(1);
        }
        refundRequestPO.setUpdateName(baseSessionUserDTO.getNickName());
        refundRequestPO.setUpdateTime(new Date());
        refundRequestPO.setRedundsReason(redundsReason);
        refundRequestPO.setAuditName(baseSessionUserDTO.getNickName());
        refundRequestPO.setAuditTime(new Date());
        refundRequestPO.setRefundStatus(refundStatus);
        orderRefundRequestDAO.updateByPrimaryKey(refundRequestPO);
        return "1";
    }

    @Override
    public String updateFinsh(int id, BaseSessionUserDTO baseSessionUserDTO) {
        OrderRefundRequestPO refundRequestPO = this.findById(id);
        refundRequestPO.setUpdateName(baseSessionUserDTO.getNickName());
        refundRequestPO.setUpdateTime(new Date());
        refundRequestPO.setAuditName(baseSessionUserDTO.getNickName());
        refundRequestPO.setAuditTime(new Date());
        refundRequestPO.setRefundStatus("3");
        refundRequestPO.setFinishTime(new Date());
        orderRefundRequestDAO.updateByPrimaryKey(refundRequestPO);
        return "1";
    }

    @Override
    @Transactional
    public void insertInfo(OrderRefundRequestPO orderRefundRequestPO, BaseSessionUserDTO baseSessionUserDTO,
            String itemId) {
        orderRefundRequestPO.setCreateTime(new Date());
        orderRefundRequestPO.setUpdateTime(new Date());
        orderRefundRequestPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderRefundRequestPO.setFcgRefundFlag(0);
        orderRefundRequestPO.setCancelFlag(0);
        // 平台商品退货时退还佣金
        ProductPO productPO = productService.getById(orderRefundRequestPO.getProductId());
        // 平台商品时 获取平台当时售卖价格
        if (productPO.getFromType() == 2) {
            orderRefundRequestPO.setFcgProductPrice(orderItemService.getById(Integer.valueOf(itemId))
                    .getFcgProductPrice());
        }
        orderRefundRequestPO.setRefundNumber(OrderNumberUtil.generateOrderNo());
        orderRefundRequestPO.setIsDelete(0);
        orderRefundRequestPO.setRefundStatus("1");
        orderRefundRequestPO.setCreateName(baseSessionUserDTO.getNickName());
        if (orderRefundRequestPO.getIsTuihuo() == 0) {
            orderRefundRequestPO.setRefundMoney(orderRefundRequestPO.getProductPrice().multiply(
                    new BigDecimal(orderRefundRequestPO.getProductCount())));
        }
        if (orderRefundRequestPO.getIsTuihuo() == 1) {
            orderRefundRequestPO.setRefundMoney(orderRefundRequestPO.getProductPrice().multiply(
                    new BigDecimal(orderRefundRequestPO.getProductQuant())));
        }
        orderRefundRequestPO.setProductPicUrl(imageService.getImageUrlByProductId(orderRefundRequestPO.getProductId()));
        orderRefundRequestDAO.insert(orderRefundRequestPO);
        // 图片
        List<String> imageUrlList = orderRefundRequestPO.getImageUrl();
        if (imageUrlList != null && imageUrlList.size() > 0) {
            for (int i = 0; i < imageUrlList.size(); i++) {
                if (StringUtils.isNotBlank(imageUrlList.get(i))) {
                    OrderRefundImagePO orderRefundImagePO = new OrderRefundImagePO();
                    orderRefundImagePO.setDisplayOrder(Integer.valueOf(i));
                    orderRefundImagePO.setCreateName(baseSessionUserDTO.getNickName());
                    orderRefundImagePO.setCreateTime(new Date());
                    orderRefundImagePO.setUpdateName(baseSessionUserDTO.getNickName());
                    orderRefundImagePO.setUpdateTime(new Date());
                    orderRefundImagePO.setIsDelete(0);
                    orderRefundImagePO.setOrdRefundId(orderRefundRequestPO.getId());
                    orderRefundImagePO.setImgUrl(imageUrlList.get(i));
                    orderRefundImageDAO.insert(orderRefundImagePO);
                }
            }
        }

    }

    @Override
    public OrderRefundRequestPO getByOrdIdAndProdId(int orderId, int productId, String productSpec) {
        OrderRefundRequestCriteria orderRefundRequestCriteria = new OrderRefundRequestCriteria();
        orderRefundRequestCriteria.createCriteria().andOrderIdEqualTo(orderId);
        orderRefundRequestCriteria.createCriteria().andProductIdEqualTo(productId);
        orderRefundRequestCriteria.createCriteria().andProductSpecEqualTo(productSpec);
        List<OrderRefundRequestPO> list = orderRefundRequestDAO.selectByCriteria(orderRefundRequestCriteria);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void updateTuihuiPro(int ordRefundId, String logisticsCompany, String logisticsNumber,
            BaseSessionUserDTO baseSessionUserDTO) {
        OrderRefundRequestPO orderRefundRequestPO = this.findById(ordRefundId);
        orderRefundRequestPO.setUpdateTime(new Date());
        orderRefundRequestPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderRefundRequestPO.setLogisticsCompany(logisticsCompany);
        orderRefundRequestPO.setLogisticsNumber(logisticsNumber);
        orderRefundRequestDAO.updateByPrimaryKey(orderRefundRequestPO);
    }

    @Override
    public Page findOrdRefundRequestUnionListByPage(OrderRefundRequestUnionPO orderRefundRequestUnionPO, int pageIndex) {
        // 查询记录
        Page page = new Page();
        // 更新当前页码
        if (pageIndex >= 1) {
            page.setPageIndex(pageIndex);
        }
        // 装载参数
        Map parm = new HashMap();
        if (StringUtils.isNotEmpty(orderRefundRequestUnionPO.getProductName())) {
            parm.put("productName", "%" + orderRefundRequestUnionPO.getProductName() + "%");
        }
        parm.put("isTuihuo", orderRefundRequestUnionPO.getIsTuihuo());
        parm.put("refundStatus", orderRefundRequestUnionPO.getRefundStatus());
        parm.put("fcgRefundFlag", orderRefundRequestUnionPO.getFcgRefundFlag());

        // 查询总记录数
        int count = orderRefundRequestDAO.getOdrRfdRequestUnionCountByPage(parm);

        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        // 查询记录
        List<OrderRefundRequestUnionPO> orderRefundRequestUnionPOs =
                orderRefundRequestDAO.getOrdRefundRequestUnionListByPage(parm);

        page.setRow(orderRefundRequestUnionPOs);
        page.setRecords(count);
        return page;
    }

}
