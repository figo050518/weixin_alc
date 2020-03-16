package com.fcgo.weixin.dada.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class OrderProcessReq {
    String	client_id;//是	返回达达运单号，默认为空
    String	order_id;//是	添加订单接口中的origin_id值
    int	    order_status; //是 订单状态(待接单＝1,待取货＝2,配送中＝3,已完成＝4,已取消＝5, 已过期＝7,指派单=8,妥投异常之物品返回中=9, 妥投异常之物品返回完成=10,骑士到店=100,创建达达运单失败=1000 可参考文末的状态说明）
    String	cancel_reason;//是	订单取消原因,其他状态下默认值为空字符串
    Integer	cancel_from	  ;//是	订单取消原因来源(1:达达配送员取消；2:商家主动取消；3:系统或客服取消；0:默认值)
    Integer	update_time	  ;//是	更新时间,时间戳
    String	signature	  ;//是	对client_id, order_id, update_time的值进行字符串升序排列，再连接字符串，取md5值
    Integer dm_id ;//否	达达配送员id，接单以后会传
    String	dm_name	      ;//否	配送员姓名，接单以后会传
    String	dm_mobile	  ;//否	配送员手机号，接单以后会传
}
