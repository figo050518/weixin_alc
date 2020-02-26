package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.OrderConvert;
import com.fcgo.weixin.convert.OrderGoodsConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.OrderBo;
import com.fcgo.weixin.model.backend.bo.OrderGoodsBo;
import com.fcgo.weixin.model.backend.req.OrderDetailReq;
import com.fcgo.weixin.model.backend.req.OrderListReq;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.persist.dao.BrandMapper;
import com.fcgo.weixin.persist.dao.OrderMapper;
import com.fcgo.weixin.persist.dao.OrderProductMapper;
import com.fcgo.weixin.persist.dao.UserMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.Order;
import com.fcgo.weixin.persist.model.OrderProduct;
import com.fcgo.weixin.persist.model.User;
import com.fcgo.weixin.persist.model.dto.OrderListQueryDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private UserMapper userMapper;

    OrderListQueryDto check(OrderListReq req){
         String orderCode = req.getOrderCode();
         if (StringUtils.isNotBlank(orderCode)){
             orderCode = orderCode.trim();
         }
         Integer startTime = null, endTime = null;
         String startTimeStr = req.getStartTime();
         String endTimeStr = req.getEndTime();
         if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)){
             startTime = DateUtil.getTimeSecondsFromStr(startTimeStr);
             endTime = DateUtil.getTimeSecondsFromStr(endTimeStr);
         }
         String buyerPhone = req.getBuyerPhone();
         if (StringUtils.isNotBlank(buyerPhone)){
             buyerPhone = buyerPhone.trim();
         }

         String status = req.getStatus();
         if (StringUtils.isNotBlank(status)){
             status = status.trim();
         }

         return OrderListQueryDto.builder().orderCode(orderCode)
                 .status(status)
                 .startTime(startTime)
                 .endTime(endTime)
                 .buyerPhone(buyerPhone)
                 .build();
    }

    public PageResponseBO<OrderBo> getList(OrderListReq req) throws SessionExpireException {
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<OrderBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        LoginUserResp userResp = accountService.getLoginUser();
        if (Objects.isNull(userResp)){
            throw new SessionExpireException();
        }
        Integer uid = userResp.getUid();
        if (Objects.isNull(uid)){
            throw new ServiceException(401,"uid不正确");
        }
        String userName = userResp.getUserName();
        if (StringUtils.isBlank(userName)){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = accountService.isAdmin(uid, userName);
        OrderListQueryDto condition = check(req);
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        Supplier<Integer> totalSupplier;
        Supplier<List<Order>> prdListSupplier;
        if (isAdmin){
            logger.info("get product list user is admin, req {}", req);
            totalSupplier = ()-> orderMapper.selectCnt(condition);
            prdListSupplier = () -> orderMapper.selectAll(condition, offset, pageSize);
        }else{
            Integer brandId;
            if (Objects.isNull(brandId=userResp.getBrandId()) || brandId<1){
                throw new ServiceException(401, "品牌ID不正确");
            }
            logger.info("get product list user is brand, req {}", req);
            condition.setBrandId(brandId);
            totalSupplier = ()->  orderMapper.selectCnt(condition);
            prdListSupplier = () -> orderMapper.selectAll(condition, offset, pageSize);
        }

        int total = totalSupplier.get();
        if (total==0){
            return pageBuilder.build();
        }

        List<Order> dolist = prdListSupplier.get();
        Set<Integer> brandIds = new HashSet<>(dolist.size());
        Set<Integer> userIds = new HashSet<>(dolist.size());
        dolist.stream().forEach(order -> {
            brandIds.add(order.getBrandId());
            userIds.add(order.getBuyerId());
        });
        Map<Integer, Brand> brandMap = brandService.getIdBrandMap(brandIds);
        Map<Integer, User> userMap = userManageService.getUserMap(userIds);
        List<OrderBo> bos = dolist.stream().map(order->{
            Brand brand = brandMap.get(order.getBrandId());
            User buyer = userMap.get(order.getBuyerId());
            return OrderConvert.do2Bo(order, brand, buyer);
        }).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }

    public OrderBo getDetail(OrderDetailReq req) throws SessionExpireException {
        String orderCode;
        if (StringUtils.isBlank(orderCode = req.getOrderCode())){
            throw new ServiceException(401,"订单号不能为空");
        }
        //trim
        orderCode = orderCode.trim();
        //
        LoginUserResp userResp = accountService.getLoginUser();
        if (Objects.isNull(userResp)){
            throw new SessionExpireException();
        }
        Integer uid = userResp.getUid();
        if (Objects.isNull(uid)){
            throw new ServiceException(401,"uid不正确");
        }
        String userName = userResp.getUserName();
        if (StringUtils.isBlank(userName)){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = accountService.isAdmin(uid, userName);

        Order condition = new Order();
        condition.setCode(orderCode);
        if (!isAdmin){
            condition.setBrandId(userResp.getBrandId());
        }
        Order order = orderMapper.selectByOrderCode(condition);
        if (Objects.isNull(order)){
            logger.warn("getDetail fail order null, order code {},login user {}", order, userResp);
            return null;
        }
        Brand brand = brandMapper.selectByPrimaryKey(order.getBrandId());
        User buyer = userMapper.selectByPrimaryKey(order.getBuyerId());
        OrderBo orderBo = OrderConvert.do2Bo(order, brand, buyer);
        List<OrderProduct> orderProducts =  orderProductMapper.selectByOrderCode(orderCode);
        if (CollectionUtils.isNotEmpty(orderProducts)){
            List<OrderGoodsBo> orderGoodsBos = orderProducts.stream().map(OrderGoodsConvert::do2Bo)
                    .collect(Collectors.toList());
            orderBo.setOrderGoodsList(orderGoodsBos);
        }
        return orderBo;
    }

}
