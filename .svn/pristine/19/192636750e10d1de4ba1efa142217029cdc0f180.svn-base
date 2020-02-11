package com.fcgo.weixin.application.impl.user;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.user.ValidateCodeLogService;
import com.fcgo.weixin.common.log.LogCategory;
import com.fcgo.weixin.persist.dao.IValidateCodeLogDAO;
import com.fcgo.weixin.persist.po.ValidateCodeLogPO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class ValidateCodeLogServiceImpl implements ValidateCodeLogService {

    @Autowired
    private IValidateCodeLogDAO validateCodeLogDAO;

    @Autowired
    private UserInfoService userRegisterService;

    private Log logger = LogFactory.getLog(LogCategory.SERVICE.toString());

    public String insertMsgCodeForRegister(String telephone, String message, String code) {
        String msg = null;
        String httpResponse = send(telephone, message);
        try {
            JSONObject jsonObj = new JSONObject(httpResponse);
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if (error_code == 0) {
                ValidateCodeLogPO validateCodeLogPO = new ValidateCodeLogPO();
                validateCodeLogPO.setCreateName(telephone);
                validateCodeLogPO.setUpdateTime(new Date());
                validateCodeLogPO.setCreateTime(new Date());
                validateCodeLogPO.setUpdateName(telephone);
                validateCodeLogPO.setValidateCode(Integer.valueOf(code).toString());
                validateCodeLogPO.setTelephone(telephone);
                validateCodeLogPO.setSendTime(new Date());
                validateCodeLogDAO.insert(validateCodeLogPO);
                msg = "success";
            }
            else {
                logger.info("发送失败；" + "错误码：" + error_code + "错误描述：" + error_msg);
                msg = "senderror";
            }
        }
        catch (Exception e) {
            e.getMessage();
        }
        return msg;

    }

    private String send(String telephone, String msgCode) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-21f1baa755a7dfac1e13eed82e7568d5"));
        WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", telephone);
        formData.add("message", msgCode);
        ClientResponse response =
                webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        return textEntity;
    }
}
