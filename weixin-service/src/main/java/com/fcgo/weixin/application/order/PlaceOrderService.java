package com.fcgo.weixin.application.order;

import java.math.BigDecimal;
import java.util.List;

import com.fcgo.weixin.application.dto.OrderConfirmDetailDTO;
import com.fcgo.weixin.application.dto.OrderConfirmProductDTO;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.persist.po.UserAddressPO;

public interface PlaceOrderService {
    public List<OrderConfirmProductDTO> resolveProductInfoJson(String tempProductInfoJson);

    public BigDecimal getOrderTotalMoney(List<OrderConfirmDetailDTO> orderConfirmDetailDTOs);

    public BigDecimal getOrderTotalFreight(List<OrderConfirmDetailDTO> orderConfirmDetailDTOs);

    public int placeOrder(List<OrderConfirmDetailDTO> orderConfirmDetailDTOList, BaseSessionUserDTO baseSessionUserDTO,
            UserAddressPO userAddressPO);

    public List<OrderConfirmDetailDTO> resolveOrderInfoJson(String tempProductInfoJson);

}
