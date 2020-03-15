import com.fcgo.weixin.dada.client.DadaApiResponse;
import com.fcgo.weixin.dada.client.DadaRequestClient;
import com.fcgo.weixin.dada.config.AppConfig;
import com.fcgo.weixin.dada.domain.merchant.ShopModel;
import com.fcgo.weixin.dada.domain.order.OrderAddModel;
import com.fcgo.weixin.dada.service.CityCodeContext;
import com.fcgo.weixin.dada.service.merchant.ShopAddContext;
import com.fcgo.weixin.dada.service.order.OrderAddContext;
import com.fcgo.weixin.dada.utils.JSONUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DATE: 18/9/4
 *
 * @author: wan
 */
public class TestExample {

    private static DadaApiResponse addOrder() {
        // 1.初始化配置(isOnline表示是否测试环境)
        AppConfig appConfig = new AppConfig(false);

        // 2.初始化model
        OrderAddModel orderAddModel = new OrderAddModel();
        orderAddModel.setShopNo("11664071");
        orderAddModel.setOriginId(String.valueOf(System.currentTimeMillis()));
        orderAddModel.setCityCode("021");
        orderAddModel.setCargoPrice(BigDecimal.valueOf(111));
        orderAddModel.setIsPrepay(0);
        // 填写收货人信息
        orderAddModel.setReceiverName("测试收货人");
        orderAddModel.setReceiverAddress("测试地址");
        orderAddModel.setReceiverLat(BigDecimal.valueOf(11.11111228623));
        orderAddModel.setReceiverLng(BigDecimal.valueOf(121.587172));
        orderAddModel.setReceiverPhone("xxxxxxxxxxx");
        // 设置回调url, 订单状态每次变更就会往该url发送通知(参见回调接口)
        orderAddModel.setCallback("http://xxxxxxxxxxxxxxxxxxxxxxxx");

        // 3.初始化service
        OrderAddContext orderService = new OrderAddContext(orderAddModel.toJson());

        // 4.初始化客户端
        DadaRequestClient dadaClient = new DadaRequestClient(orderService, appConfig);
        return dadaClient.callRpc();


    }

    private static DadaApiResponse addShop() {

        // 1.初始化配置(isOnline表示是否测试环境)
        AppConfig appConfig = new AppConfig(false);

        // 2.初始化model
        ShopModel shopAddModel = new ShopModel();
        // 根据实际信息来填写门店地址
        shopAddModel.setOriginShopId("xxxxxxxxxxxxxxxx");
        shopAddModel.setStationName("xxxxxxxxxxxxxxxx");
        shopAddModel.setBusiness(2);
        shopAddModel.setCityName("上海");
        shopAddModel.setAreaName("xxxxxxxxxxxxxxxx");
        shopAddModel.setStationAddress("xxxxxxxxxxxxxxxx");
        shopAddModel.setLng(BigDecimal.valueOf(121.587173));
        shopAddModel.setLat(BigDecimal.valueOf(31.228624));
        shopAddModel.setContactName("xxxxxxxxxxxxxxxx");
        shopAddModel.setPhone("xxxxxxxxxxxxxxxx");

        // 3.初始化service (门店新增比较特殊,是一个批量新增接口)
        List<ShopModel> shopAddList = new ArrayList<ShopModel>();
        shopAddList.add(shopAddModel);
        ShopAddContext shopAddService = new ShopAddContext(JSONUtil.toJson(shopAddList));


        // 4.初始化客户端
        DadaRequestClient dadaClient = new DadaRequestClient(shopAddService, appConfig);
        return dadaClient.callRpc();
    }


    private static DadaApiResponse queryCityCode(){
        // 1.初始化配置(isOnline表示是否测试环境)
        AppConfig appConfig = new AppConfig(false);

        // 2.初始化service
        CityCodeContext cityCodeService = new CityCodeContext("");

        // 3.初始化客户端
        DadaRequestClient dadaClient = new DadaRequestClient(cityCodeService, appConfig);
        return dadaClient.callRpc();
    }

    public static void main(String[] args) {
        DadaApiResponse resp = queryCityCode();
        System.out.println(JSONUtil.toJson(resp));
    }
}
