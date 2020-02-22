package com.fcgo.weixin.application.impl.interfaces;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.dto.LogiticsTrackDTO;
import com.fcgo.weixin.application.interfaces.LogisticsTrackService;
import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.common.util.LogisticsMD5;
import com.fcgo.weixin.common.util.logisticsTrack.HttpRequest;

@Service
public class LogisticsTrackServiceImpl implements LogisticsTrackService {

    @Autowired
    private IOrderInfoService orderInfoService;

    @Override
    public LogiticsTrackDTO getLogisticInfo(String logisticsCompany, String logisticsNumber) {
        String param =
                "{\"com\":\"abc\",\"num\":\"def\"}".replace("abc", logisticsCompany).replace("def", logisticsNumber);
        String customer = "01B738FC5D11ED1496D4059E31AC1576";
        String key = "uBGaNiye3033";
        String sign = LogisticsMD5.encode(param + key + customer);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("param", param);
        params.put("sign", sign);
        params.put("customer", customer);
        String resp;
        try {
            resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            LogiticsTrackDTO logiticsTrackDTO = JacksonHelper.fromJSON(resp, LogiticsTrackDTO.class);
            return logiticsTrackDTO;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
