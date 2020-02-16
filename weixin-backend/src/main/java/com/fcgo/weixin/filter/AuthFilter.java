package com.fcgo.weixin.filter;

import com.fcgo.weixin.uitl.BackendUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author yanguang
 *
 */
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res = (HttpServletResponse)response;
			//基于http协议的servlet
			
			//如果没有登录.
			String requestURI = req.getRequestURI();
			String method = req.getMethod().toUpperCase();
			logger.info("requestURI----------------->"+requestURI+"  method------------->"+method);
			
			 if(requestURI.indexOf("/oper/login.do")>=0 && method.equals("GET"))
			{
				 
			}else if(requestURI.indexOf("/oper/")<0)
			{
				if(!BackendUtils.isLogin(req)){
					res.sendRedirect(req.getContextPath() + "/oper/login.do");
					return;
				} 
			}else if(requestURI.indexOf("/oper/login.do")>=0 )
			{
				
			}else if(requestURI.indexOf("/oper/")>=0)
			{
				if(!BackendUtils.isLogin(req)){
					res.sendRedirect(req.getContextPath() + "/oper/login.do");
					return;
				} 
			}
			//session中的内容等于登录页面, 则可以继续访问其他区资源.
			chain.doFilter(req, res);
	}
}