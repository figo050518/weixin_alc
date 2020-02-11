package com.fcgo.weixin.application.impl.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.dto.OrderPayDTO;
import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.common.constants.OrderStateConstants;
import com.fcgo.weixin.common.constants.UserType;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.dao.IOrderInfoDAO;
import com.fcgo.weixin.persist.dao.IOrderPaymentLogDAO;
import com.fcgo.weixin.persist.dao.IParentOrderInfoDAO;
import com.fcgo.weixin.persist.generate.criteria.OrderInfoCriteria;
import com.fcgo.weixin.persist.generate.criteria.ParentOrderInfoCriteria;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import com.fcgo.weixin.persist.po.OrderPaymentLogPO;
import com.fcgo.weixin.persist.po.ParentOrderInfoPO;

@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {
    @Autowired
    private IOrderInfoDAO orderInfoDAO;

    @Autowired
    private IParentOrderInfoDAO parentOrderInfoDAO;

    @Autowired
    private IOrderPaymentLogDAO orderPaymentLogDAO;

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Page getOrderInfoList(BaseSessionUserDTO baseSessionUserDTO, String orderState, String buyerId, int pageIndex) {
        Page page = new Page();
        // 更新当前页码
        if (pageIndex >= 1) {
            page.setPageIndex(pageIndex);
        }
        // 当订单状态为-1全部订单 清空订单状态
        if (StringUtils.isNotBlank(orderState) && orderState.equals("-1")) {
            orderState = null;
        }
        // 参数map
        Map parm = new HashMap();
        // 用户ID
        parm.put("userId", baseSessionUserDTO.getUserId());
        if (StringUtils.isNotBlank(buyerId)) {
            parm.put("buyerId", buyerId);
        }
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
        parm.put("orderState", orderState);
        // 默认排序字段 方式（如果需要可更改）
        parm.put("sorter", "updateTime desc");
        // 分页信息
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("endPage", page.getPageIndex() * page.getPageSize());
        // 获取主编分页数据
        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.orderInfoPage(parm);
        // 获取本次查询数据总条数
        int records = orderInfoDAO.getOrdInfoCount(parm);
        // 组装数据
        // 返回数据集合
        page.setRow(orderInfoPOs);
        page.setRecords(records);
        return page;
    }

    @Override
    public OrderInfoPO getByOrderId(Integer orderId) {
        return orderInfoDAO.selectByPrimaryKey(orderId);
    }

    @Override
    public void updatePay(Integer orderId, String payType, BaseSessionUserDTO baseSessionUserDTO) {
        OrderInfoPO orderInfoPO = orderInfoDAO.selectByPrimaryKey(orderId);
        orderInfoPO.setId(orderId);
        orderInfoPO.setPayWay(payType);
        orderInfoPO.setOrderState(OrderStateConstants.WAIT_SHIP.getKey());
        orderInfoPO.setPayTime(new Date());
        orderInfoPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderInfoPO.setUpdateTime(new Date());
        orderInfoDAO.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public void updateSendPro(Integer orderId, String logisticsNumber, String logisticsCompany,
            BaseSessionUserDTO baseSessionUserDTO) {
        OrderInfoPO orderInfoPO = orderInfoDAO.selectByPrimaryKey(orderId);
        orderInfoPO.setId(orderId);
        orderInfoPO.setSendTime(new Date());
        orderInfoPO.setOrderState(OrderStateConstants.WAIT_CONFIRM.getKey());
        orderInfoPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderInfoPO.setUpdateTime(new Date());
        orderInfoPO.setLogisticsComp(logisticsCompany);
        orderInfoPO.setLogisticsNum(logisticsNumber);
        orderInfoDAO.updateByPrimaryKey(orderInfoPO);

    }

    @Override
    public void updateSurePro(Integer orderId, BaseSessionUserDTO baseSessionUserDTO) {
        OrderInfoPO orderInfoPO = orderInfoDAO.selectByPrimaryKey(orderId);
        orderInfoPO.setId(orderId);
        orderInfoPO.setFinishTime(new Date());
        orderInfoPO.setOrderState(OrderStateConstants.FINISHED.getKey());
        orderInfoPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderInfoPO.setUpdateTime(new Date());
        orderInfoDAO.updateByPrimaryKey(orderInfoPO);

    }

    @Override
    public void updateCancel(Integer orderId, String closeDesc, BaseSessionUserDTO baseSessionUserDTO) {
        OrderInfoPO orderInfoPO = orderInfoDAO.selectByPrimaryKey(orderId);
        orderInfoPO.setId(orderId);
        orderInfoPO.setOrderState(OrderStateConstants.CANCELED.getKey());
        orderInfoPO.setCancelTime(new Date());
        orderInfoPO.setCloseDesc(closeDesc);
        if (baseSessionUserDTO.getIsBuyer()) {
            orderInfoPO.setCancelType(UserType.BUYER.getKey());
        }
        else {
            orderInfoPO.setCancelType(UserType.SELLER.getKey());
        }
        orderInfoPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderInfoPO.setUpdateTime(new Date());
        orderInfoDAO.updateByPrimaryKey(orderInfoPO);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean getIsRefund(int orderId, int productId, String productSpec) {
        // 参数map
        Map parm = new HashMap();
        parm.put("productId", productId);
        parm.put("orderId", orderId);
        parm.put("productSpec", productSpec);
        // 获取当前条件申请售后数量
        int refundCount = orderInfoDAO.getIsRefund(parm);
        boolean isRefund = false;
        if (refundCount > 0) {
            isRefund = true;
        }
        return isRefund;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Page getOrderInfoListPage(OrderInfoPO orderInfoPO, int pageIndex) {
        // TODO Auto-generated method stub
        // 查询记录
        Page page = new Page();
        // 更新当前页码
        if (pageIndex >= 1) {
            page.setPageIndex(pageIndex);
        }
        Map parm = new HashMap();
        if (StringUtils.isNotEmpty(orderInfoPO.getOrderNum())) {
            parm.put("orderNum", "%" + orderInfoPO.getOrderNum() + "%");
        }
        parm.put("orderState", orderInfoPO.getOrderState());
        parm.put("isDelete", orderInfoPO.getIsDelete());
        if (StringUtils.isNotEmpty(orderInfoPO.getLogisticsNum())) {
            parm.put("logisticsNum", "%" + orderInfoPO.getLogisticsNum() + "%");
        }
        parm.put("isEaluate", orderInfoPO.getIsEaluate());
        parm.put("orderType", orderInfoPO.getOrderType());
        parm.put("cancelType", orderInfoPO.getCancelType());

        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        parm.put("sorter", "CREATE_TIME desc");

        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.getOrderInfoListPage(parm);

        // 查询记录条数
        OrderInfoCriteria orderInfoCriteria = new OrderInfoCriteria();
        if (StringUtils.isNotEmpty(orderInfoPO.getOrderNum())) {
            orderInfoCriteria.createCriteria().andOrderNumLike("%" + orderInfoPO.getOrderNum() + "%");
        }
        if (orderInfoPO.getOrderState() != null) {
            orderInfoCriteria.createCriteria().andOrderStateEqualTo(orderInfoPO.getOrderState());
        }
        if (orderInfoPO.getIsDelete() != null) {
            orderInfoCriteria.createCriteria().andIsDeleteEqualTo(orderInfoPO.getIsDelete());
        }
        if (StringUtils.isNotEmpty(orderInfoPO.getLogisticsNum())) {
            orderInfoCriteria.createCriteria().andLogisticsNumLike("%" + orderInfoPO.getLogisticsNum() + "%");
        }
        if (orderInfoPO.getIsEaluate() != null) {
            orderInfoCriteria.createCriteria().andIsEaluateEqualTo(orderInfoPO.getIsEaluate());
        }
        if (orderInfoPO.getOrderType() != null) {
            orderInfoCriteria.createCriteria().andOrderTypeEqualTo(orderInfoPO.getOrderType());
        }
        if (orderInfoPO.getCancelType() != null) {
            orderInfoCriteria.createCriteria().andCancelTypeEqualTo(orderInfoPO.getCancelType());
        }
        int cnt = orderInfoDAO.countByCriteria(orderInfoCriteria);

        page.setRow(orderInfoPOs);
        page.setRecords(cnt);
        return page;
    }

    @Override
    public void updateOrderPrice(int orderId, String proAmount, BaseSessionUserDTO baseSessionUserDTO) {
        OrderInfoPO orderInfoPO = orderInfoDAO.selectByPrimaryKey(orderId);
        orderInfoPO.setActPayAmount(new BigDecimal(proAmount));
        orderInfoPO.setOrdDiscount(orderInfoPO.getActPayAmount().subtract(new BigDecimal(proAmount)));
        orderInfoPO.setUpdateName(baseSessionUserDTO.getNickName());
        orderInfoPO.setUpdateTime(new Date());
        orderInfoDAO.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public BigDecimal getAmountByParentOrderId(Integer parentOrderId) {
        BigDecimal totalAmount = new BigDecimal(0);
        OrderInfoCriteria orderInfoCriteria = new OrderInfoCriteria();
        orderInfoCriteria.createCriteria().andParentOrderIdEqualTo(parentOrderId);
        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.selectByCriteria(orderInfoCriteria);
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
            totalAmount = totalAmount.add(orderInfoPO.getActPayAmount());
        }
        return totalAmount;
    }

    @Override
    public OrderPayDTO getOrderInfo4Pay(Integer parentOrderId) {
        ParentOrderInfoPO parentOrderInfoPO = parentOrderInfoDAO.selectByPrimaryKey(parentOrderId);
        OrderPayDTO orderPayDTO = new OrderPayDTO();
        BigDecimal totalAmount = new BigDecimal(0);
        OrderInfoCriteria orderInfoCriteria = new OrderInfoCriteria();
        orderInfoCriteria.createCriteria().andParentOrderIdEqualTo(parentOrderId);
        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.selectByCriteria(orderInfoCriteria);
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
            totalAmount = totalAmount.add(orderInfoPO.getActPayAmount());
        }
        orderPayDTO.setTotalPrice(totalAmount);
        orderPayDTO.setOrderNumber(parentOrderInfoPO.getOrderNumber());
        return orderPayDTO;
    }

    @Override
    public boolean validateIsPay(String orderNumber) {
        boolean isPay = true;
        ParentOrderInfoCriteria parentOrderInfoCriteria = new ParentOrderInfoCriteria();
        parentOrderInfoCriteria.createCriteria().andOrderNumberEqualTo(orderNumber);
        List<ParentOrderInfoPO> parentOrderInfoPOs = parentOrderInfoDAO.selectByCriteria(parentOrderInfoCriteria);
        OrderInfoCriteria orderInfoCriteria = new OrderInfoCriteria();
        orderInfoCriteria.createCriteria().andParentOrderIdEqualTo(parentOrderInfoPOs.get(0).getId());
        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.selectByCriteria(orderInfoCriteria);
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
            if (orderInfoPO.getOrderState() != 1) {
                isPay = false;
                break;
            }
        }
        return isPay;
    }

    @Override
    @Transactional
    public void updateOrderPayFinish(String orderNumber, String transactionId) {
        ParentOrderInfoCriteria parentOrderInfoCriteria = new ParentOrderInfoCriteria();
        parentOrderInfoCriteria.createCriteria().andOrderNumberEqualTo(orderNumber);
        List<ParentOrderInfoPO> parentOrderInfoPOs = parentOrderInfoDAO.selectByCriteria(parentOrderInfoCriteria);
        OrderPaymentLogPO paymentLogInfo = new OrderPaymentLogPO();
        paymentLogInfo.setCreateTime(new Date());
        paymentLogInfo.setUpdateName(parentOrderInfoPOs.get(0).getCreateName());
        paymentLogInfo.setUpdateTime(new Date());
        paymentLogInfo.setCreateName(parentOrderInfoPOs.get(0).getCreateName());
        paymentLogInfo.setBankTransactionNumber(transactionId);
        paymentLogInfo.setPaymentStatus(1);
        paymentLogInfo.setPaymentType("微信支付");
        paymentLogInfo.setTransactionAmount(getAmountByParentOrderId(parentOrderInfoPOs.get(0).getId()));
        paymentLogInfo.setOrderId(parentOrderInfoPOs.get(0).getId());
        orderPaymentLogDAO.insert(paymentLogInfo);
        OrderInfoCriteria orderInfoCriteria = new OrderInfoCriteria();
        orderInfoCriteria.createCriteria().andParentOrderIdEqualTo(parentOrderInfoPOs.get(0).getId());
        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.selectByCriteria(orderInfoCriteria);
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
            orderInfoPO.setUpdateTime(new Date());
            orderInfoPO.setOrderState(1);
            orderInfoPO.setPayWay("微信支付");
            orderInfoPO.setPayTime(new Date());
            orderInfoDAO.updateByPrimaryKeySelective(orderInfoPO);
        }

    }

    @Override
    public void spiltOrder(String orderNumber) {
        // TODO Auto-generated method stub

    }
}
