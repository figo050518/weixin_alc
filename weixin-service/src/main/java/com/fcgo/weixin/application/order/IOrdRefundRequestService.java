package com.fcgo.weixin.application.order;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;

/**
 * 售后
 * 
 * @author Ww
 */
public interface IOrdRefundRequestService {
    /**
     * 分页查询售后信息
     * 
     * @param baseSessionUserDTO
     * @param refundStatus
     * @param pageIndex
     * @return
     */
    public Page findOrdRufundBypage(BaseSessionUserDTO baseSessionUserDTO, String refundStatus, int pageIndex);

    /**
     * 根据售后ID查询
     * 
     * @param id
     * @return
     */
    OrderRefundRequestPO findById(int id);

    /**
     * FCG商品将售后移交FCG处理
     * 
     * @param id
     * @param baseSessionUserDTO
     * @return
     */
    public String updateTransferFcg(int id, BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 卖家售后审核
     * 
     * @param id
     * @param auditState
     * @param redundsReason
     * @param baseSessionUserDTO
     * @return
     */
    public String updateAuditBySeller(int id, String refundStatus, String redundsReason,
            BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 完成售后
     * 
     * @param id
     * @param baseSessionUserDTO
     * @return
     */
    public String updateFinsh(int id, BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 申请售后
     * 
     * @param orderRefundRequestPO
     * @param baseSessionUserDTO
     */
    public void insertInfo(OrderRefundRequestPO orderRefundRequestPO, BaseSessionUserDTO baseSessionUserDTO,
            String itemId);

    /**
     * 条件查看售后
     * 
     * @param orderId
     * @param productId
     * @param productSpec
     * @return
     */
    public OrderRefundRequestPO getByOrdIdAndProdId(int orderId, int productId, String productSpec);

    public void updateTuihuiPro(int ordRefundId, String logisticsCompany, String logisticsNumber,
            BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 分页查询维权订单列表
     * 
     * @param orderRefundRequestUnionPO
     * @param pageIndex
     * @return
     */
    public Page findOrdRefundRequestUnionListByPage(OrderRefundRequestUnionPO orderRefundRequestUnionPO, int pageIndex);
}
