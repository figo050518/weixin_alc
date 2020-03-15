package com.fcgo.weixin.dada.domain.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * {@code https://newopen.imdada.cn/#/development/file/order}
 * <p>
 * 参数说明
 * 参数名	类型	是否必传	描述
 * client_id	String	是	返回达达运单号，默认为空
 * order_id	String	是	添加订单接口中的origin_id值
 * order_status	int	是	订单状态(待接单＝1,待取货＝2,配送中＝3,已完成＝4,已取消＝5, 已过期＝7,指派单=8,妥投异常之物品返回中=9, 妥投异常之物品返回完成=10,骑士到店=100,创建达达运单失败=1000 可参考文末的状态说明）
 * cancel_reason	String	是	订单取消原因,其他状态下默认值为空字符串
 * cancel_from	Int	是	订单取消原因来源(1:达达配送员取消；2:商家主动取消；3:系统或客服取消；0:默认值)
 * update_time	Int	是	更新时间,时间戳
 * signature	String	是	对client_id, order_id, update_time的值进行字符串升序排列，再连接字符串，取md5值
 * dm_id	Int	否	达达配送员id，接单以后会传
 * dm_name	String	否	配送员姓名，接单以后会传
 * dm_mobile	String	否	配送员手机号，接单以后会传
 */
@Data
public class OrderCallBackReq {
    /**
     * 达达运单号，默认为空
     */
    @JSONField(name = "client_id")
    private String clientId;
    /**
     * 添加订单接口中的origin_id值
     */
    @JSONField(name = "order_id")
    private String orderId;
    /**
     * 订单状态
     * (待接单＝1,
     * 待取货＝2,
     * 配送中＝3,
     * 已完成＝4,
     * 已取消＝5,
     * 已过期＝7,
     * 指派单=8,
     * 妥投异常之物品返回中=9,
     * 妥投异常之物品返回完成=10,
     * 骑士到店=100,
     * 创建达达运单失败=1000 可参考文末的状态说明）
     */
    @JSONField(name = "order_status")
    private int orderStatus;
    /**
     * 订单取消原因,其他状态下默认值为空字符串
     */
    @JSONField(name = "cancel_reason")
    private String cancelReason;
    /**
     * Int	是	订单取消原因来源(1:达达配送员取消；2:商家主动取消；3:系统或客服取消；0:默认值)
     */
    @JSONField(name = "cancel_from")
    private Integer cancelFrom;

    /**
     * 更新时间,时间戳
     */
    @JSONField(name = "update_time")
    private Integer updateTime;
    /**
     * 是	对client_id, order_id, update_time的值进行字符串升序排列，再连接字符串，取md5值
     */
    private String signature;
    /**
     * 达达配送员id，接单以后会传
     */
    @JSONField(name = "dm_id")
    private Integer dmId;
    /**
     * 否	配送员姓名，接单以后会传
     */
    @JSONField(name = "dm_name")
    private String dmName;
    /**
     * String	否	配送员手机号，接单以后会传
     */
    @JSONField(name = "dm_mobile")
    private String dmMobile;
}
