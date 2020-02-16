package com.fcgo.weixin.uitl;

import com.fcgo.weixin.common.util.HttpSessionProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 管理端 辅助类
 * 
 * @author yanguang
 */
public class BackendUtils {


    /**
     * 用户信息存放在session中key
     */
    public static final String OPER_INFO_SESSION_NAME = "OPER_INFO_SESSION_NAME";

   /**
    * 是否登录
    * @param request
    * @return
    */
    public static boolean isLogin(HttpServletRequest request) {
    	Object operInfo = HttpSessionProvider.getAttribute(request, OPER_INFO_SESSION_NAME);
    	if(operInfo == null){
    		return false;
    	}
    	return true;
    }

   /**
    * 登录信息存入session
    * @param request
    * @param name
    * @return
    */
    public static void addLoginSession(HttpServletRequest request,Serializable info) {
    	addSessionAttr(request, OPER_INFO_SESSION_NAME,info);
    }
    /**
     *  存入session
     * @param request
     * @param name
     * @return
     */
     public static void addSessionAttr(HttpServletRequest request,String name,Serializable obj) {
     	HttpSessionProvider.setAttribute(request,null, name,obj);
     }
     
    /**
     * 取存放session 属性值
     * @param request
     * @param name
     * @return
     */
     public static Object getSessionAttr(HttpServletRequest request,String name) {
     	Object obj = (OperInfoDTO)HttpSessionProvider.getAttribute(request, name);
     	return obj;
     }
     
     /**
      * 取登录用户
      * @param request
      * @param name
      * @return
      */
      public static OperInfoDTO getLoginUser(HttpServletRequest request) {
      	OperInfoDTO operInfoDTO = (OperInfoDTO)getSessionAttr(request,OPER_INFO_SESSION_NAME);
      	return operInfoDTO;
      }
      /**
       * 取登录ID
       * @param request
       * @param name
       * @return
       */
       public static String getLoginUserName(HttpServletRequest request) {
       	OperInfoDTO operInfoDTO = getLoginUser(request);
       	return operInfoDTO.getOperName();
       }
       /**
        * 取登录用户名
        * @param request
        * @param name
        * @return
        */
        public static String getLoginUserId(HttpServletRequest request) {
        	OperInfoDTO operInfoDTO = getLoginUser(request);
        	return operInfoDTO.getOperId();
        }
       /**
        * 退出
        * @param request
        * @param name
        * @return
        */
        public static void logout(HttpServletRequest request) {
        	HttpSessionProvider.logout(request, null);
        }
}
