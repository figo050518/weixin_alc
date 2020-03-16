package com.fcgo.weixin.dada.domain.order;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderDetail {
    String	orderId	;//	第三方订单编号
    Integer	statusCode	;//	订单状态(待接单＝1 待取货＝2 配送中＝3 已完成＝4 已取消＝5 已过期＝7 指派单=8 妥投异常之物品返回中=9 妥投异常之物品返回完成=10 系统故障订单发布失败=1000 可参考文末的状态说明）
    String	statusMsg	;//	订单状态
    String	transporterName	;//	骑手姓名
    String	transporterPhone	;//	骑手电话
    String	transporterLng	;//	骑手经度
    String	transporterLat	;//	骑手纬度
    Double	deliveryFee	;//	配送费,单位为元
    Double	tips	;//	小费,单位为元
    Double	couponFee	;//	优惠券费用,单位为元
    Double	insuranceFee	;//	保价费,单位为元
    Double	actualFee	;//	实际支付费用,单位为元
    Double	distance	;//	配送距离,单位为米
    String	createTime	;//	发单时间
    String	acceptTime	;//	接单时间,若未接单,则为空
    String	fetchTime	;//	取货时间,若未取货,则为空
    String	finishTime	;//	送达时间,若未送达,则为空
    String	cancelTime	;//	取消时间,若未取消,则为空
    String	orderFinishCode	;//	收货码
    BigDecimal deductFee	;//	违约金
}
