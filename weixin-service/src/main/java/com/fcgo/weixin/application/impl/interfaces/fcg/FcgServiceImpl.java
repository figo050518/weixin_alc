package com.fcgo.weixin.application.impl.interfaces.fcg;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fcgo.weixin.application.interfaces.fcg.IFcgService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.logisticsTrack.HttpRequest;
import com.fcgo.weixin.persist.po.fcg.FcgCate;
import com.fcgo.weixin.persist.po.fcg.FcgMerchant;
import com.fcgo.weixin.persist.po.fcg.FcgPostage;
import com.fcgo.weixin.persist.po.fcg.FcgProduct;
import com.fcgo.weixin.persist.po.fcg.FcgProductFullInfo;
import com.fcgo.weixin.persist.po.fcg.FcgProp;
import com.fcgo.weixin.persist.po.fcg.FcgSkuFull;
import com.fcgo.weixin.persist.po.fcg.FcgSkuRet;

/**
 * 菲常购接口实现类
 * 
 * @ClassName: FcgServiceImpl
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月10日 下午8:58:49
 */
/**
 * 菲常购接口实现类
 * 
 * @ClassName: FcgServiceImpl
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月10日 下午8:58:49
 */
@Service
public class FcgServiceImpl implements IFcgService {
    private Logger logger = Logger.getLogger(FcgServiceImpl.class);

    private static final String URL_MERCHANTCATELIST = "http://106.14.76.20/fcgo_pro/v1/api/fcg/merchantCateList.html";
    private static final String URL_GETPOSTAGE = "http://106.14.76.20/fcgo_pro/v1/api/fcg/getPostage.html";
    private static final String URL_LOGIN = "http://106.14.76.20/fcgo_pro/v1/api/fcg/login.html";
    private static final String URL_PRODUCT = "http://106.14.76.20/fcgo_pro/v1/api/fcg/product.html";
    private static final String URL_SKUS = "http://106.14.76.20/fcgo_pro/v1/api/fcg/skus.html";
    private static final String URL_ORDERADD = "http://106.14.76.20/fcgo_pro/v1/api/fcg/orderAdd.html";

    @SuppressWarnings("unchecked")
    @Override
    public List<FcgCate> findFcgCateList(Integer merchantId, String token) {
        List<FcgCate> result = null;
        if (merchantId == null || StringUtils.isEmpty(token)) {

        }

        // 定义请求参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("param", merchantId.toString());
        params.put("sign", token);

        // 发送post请求
        try {
            String resp = HttpRequest.postData(URL_MERCHANTCATELIST, params, "utf-8");
            if (!StringUtils.isEmpty(resp)) {
                Object obj = this.paserHttpResult(resp, FcgCate.class, true);
                if (obj != null) {
                    result = (List<FcgCate>) obj;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Page findFcgProductListByPage(Integer merchantId, String token, Integer fcgCateId, Integer pageNo,
            Integer pageSize, String order) {
    	// TODO Auto-generated method stub
        Page page = null;
        if (merchantId == null || StringUtils.isEmpty(token)) {

        }

        // 定义接口参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("merchantId", merchantId.toString());
        params.put("token", token);
        if (fcgCateId != null && fcgCateId.intValue() > 0) {
            params.put("cate", fcgCateId.toString());
        }
        if (pageNo == null || pageNo.intValue() < 1) {
            pageNo = 1;
        }
        params.put("page", pageNo.toString());

        if (pageSize == null || pageSize.intValue() < 1) {
            pageSize = 10;
        }
        params.put("rows", pageSize.toString());

        if (!StringUtils.isEmpty(order)) {
            params.put("groupBy", order);
        }

        // 发送post请求
        try {
            String resp = HttpRequest.postData(URL_PRODUCT, params, "utf-8");
            if (!StringUtils.isEmpty(resp)) {
                page = this.paserHttpResutl4Page(resp, pageNo, pageSize, FcgProduct.class);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage());
        }

        return page;
    }

    @Override
    public FcgPostage findFcgPostage(Integer merchantId, String token, 
    		String areaCode, Integer skuId, Integer count) {
    	// TODO Auto-generated method stub
        FcgPostage postage = new FcgPostage();
        if (merchantId ==null || StringUtils.isEmpty(token) || 
        		StringUtils.isEmpty(areaCode) || skuId == null || count == null) {

        }
        // 定义查询参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("merchantId", merchantId.toString());
        params.put("token", token);
        params.put("areaCode", areaCode);
        params.put("suppliersSkuId", skuId.toString());
        params.put("count", count.toString());
        try {
            String resp = HttpRequest.postData(URL_GETPOSTAGE, params, "utf-8");
            if (!StringUtils.isEmpty(resp)) {
                Object obj = this.paserHttpResult(resp, FcgProduct.class, false);
                if (obj != null) {
                    postage = (FcgPostage) obj;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return postage;
    }

    @Override
    public FcgProductFullInfo findFcgProductFullInfo(Integer merchantId, String token, Integer goodsId) {
        // TODO Auto-generated method stub
        FcgProductFullInfo fcgProFullInfo = null;
        if (merchantId ==null || StringUtils.isEmpty(token) || goodsId==null) {

        }

        // 定义查询参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("merchantId", merchantId.toString());
        params.put("token", token);
        params.put("goodsId", goodsId.toString());

        try {
            String resp = HttpRequest.postData(URL_GETPOSTAGE, params, "utf-8");
            if (!StringUtils.isEmpty(resp)) {
                Object obj = this.paserHttpResult(resp, FcgProductFullInfo.class, false);
                if (obj != null) {
                    fcgProFullInfo = (FcgProductFullInfo) obj;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return fcgProFullInfo;
    }

    @Override
	public FcgSkuFull findFcgSkuFull(Integer merchantId, String token,
			Integer goodsId, String skustr, String areaCode, String count) {
    	// 定义查询参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("merchantId", merchantId.toString());
        params.put("token", token);
        params.put("goodsId", goodsId.toString());
        
        try {
            String resp = HttpRequest.postData("", params, "utf-8");
            return this.paserSkuFull(resp);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        
		return null;
	}
    
	@Override
    public FcgMerchant doFcgLogin(String mobile, String password) {
        // TODO Auto-generated method stub
        FcgMerchant merchant = null;
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {

        }

        // 定义查询参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mobile", mobile);
        params.put("password", password);

        try {
            String resp = HttpRequest.postData(URL_LOGIN, params, "utf-8");
            if (!StringUtils.isEmpty(resp)) {
                Object obj = this.paserHttpResult(resp, FcgMerchant.class, false);
                if (obj != null) {
                    merchant = (FcgMerchant) obj;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return merchant;
    }
    
    @Override
    public boolean doFcgAddOrder() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 解析SKU信息
     * @param resp
     * @return
     */
    private FcgSkuFull paserSkuFull(String resp) {
		// TODO Auto-generated method stub
    	if (!StringUtils.isEmpty(resp)) {
    		JSONObject rootJson = JSON.parseObject(resp);
    		if (rootJson != null && rootJson.getIntValue("errCode") == 0) {
    			FcgSkuFull skuFull = new FcgSkuFull();
    			skuFull.setChoose(rootJson.getIntValue("choose"));
    			
    			//解析sku
    			JSONArray skuJson = rootJson.getJSONArray("sku");
    			if(skuJson == null || skuJson.size()<1){
    				skuFull.setSku(null);
    			}else{
    				skuFull.setSku(JSON.parseArray(skuJson.toJSONString(), FcgProp.class));
    			}
    			
    			//解析ret
    			JSONObject retJson = rootJson.getJSONObject("ret");
    			if(retJson !=null && retJson.getIntValue("errCode")==0){
    				skuFull.setRet(JSON.parseObject(retJson.getJSONObject("data").toJSONString(), FcgSkuRet.class));
    			}
    			return skuFull;
    		}
    	}
		return null;
	}
    
    /**
     * 解析HTTP请求返回的参数
     * @param resp		返回参数
     * @param clazz		实体对象class
     * @param isList	是否返回列表标识
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object paserHttpResult(String resp, Class clazz, boolean isList) {
        if (!StringUtils.isEmpty(resp)) {
            JSONObject jsonObj = JSON.parseObject(resp);
            if (jsonObj != null && jsonObj.getIntValue("errCode") == 0) {
                if (isList) {
                    return JSON.parseArray(jsonObj.getJSONArray("data").toJSONString(), clazz);
                }
                else {
                    return JSON.parseObject(jsonObj.getJSONObject("data").toJSONString(), clazz);
                }
            }
        }
        return null;
    }

    /**
     * 解析分页信息
     * @param resp
     * @param pageNo
     * @param pageSize
     * @param clazz
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Page paserHttpResutl4Page(String resp, Integer pageNo, Integer pageSize, Class clazz) {
        Page page = null;
        if (!StringUtils.isEmpty(resp)) {
            JSONObject jsonObj = JSON.parseObject(resp);
            if (jsonObj != null && jsonObj.getIntValue("errCode") == 0) {

                JSONObject dataJson = jsonObj.getJSONObject("data");
                if (dataJson != null) {
                    page = new Page();

                    page.setPageIndex(pageNo);
                    page.setPageSize(pageSize);
                    page.setRecords(dataJson.getIntValue("totalrecord"));
                    List<Class> rs =
                            (List<Class>) JSON.parseArray(dataJson.getJSONArray("resultlist").toJSONString(), clazz);
                    page.setRow(rs);
                }
            }
        }
        return page;
    }

    @Test
    public void testLogin() {
        FcgServiceImpl fcg = new FcgServiceImpl();
        FcgMerchant merchant = fcg.doFcgLogin("18656203643", "123456");
        System.out.println(JSON.toJSONString(merchant));
    }

    @Test
    public void testCate() {
        FcgServiceImpl fcg = new FcgServiceImpl();
        List<FcgCate> list = fcg.findFcgCateList(32, "123456789");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testProductList() {
        FcgServiceImpl fcg = new FcgServiceImpl();
        Page page = fcg.findFcgProductListByPage(32, "123456789", null, 1, 2, null);
        System.out.println(JSON.toJSONString(page));
    }

}
