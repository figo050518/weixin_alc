package com.fcgo.weixin.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 *
 *  HTTPClient的工厂类。 可以接收https请求
 *
 * @author  chenchao
 *  http clients support http
 */
public class HttpClientsFactory implements FactoryBean<CloseableHttpClient>,InitializingBean{

	private int connectionRequestTimeout;
	private int connectionTimeout;
	private int socketTimeout;
	
	private CloseableHttpClient closeableHttpClient;
	
	/**
	 * 最大总数
	 */
	private int maxTotal=200;
	
	/**
	 * 默认并发数
	 */
	private int defaultMaxPerRoute=100;
	
	/**
	 * 检查http 连接池中连接一定时间内的有效性
	 * 单位:毫秒
	 * 默认:0
	 * 默认是不做验证
	 */
	private int validateAfterInactivity=0;
	
	@Override
	public CloseableHttpClient getObject() throws Exception {

		return closeableHttpClient;
	}

	@Override
	public Class<CloseableHttpClient> getObjectType() {
		return CloseableHttpClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public void setValidateAfterInactivity(int validateAfterInactivity) {
		this.validateAfterInactivity = validateAfterInactivity;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		HttpClientBuilder b = HttpClientBuilder.create();

		// setup a Trust Strategy that allows all certificates.
		//
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
				new TrustStrategy() {
					@Override
					public boolean isTrusted(X509Certificate[] arg0, String arg1)
							throws CertificateException {
						return true;
					}
				}).build();
		b.setSSLContext(sslContext);

		// don't check Hostnames, either.
		// -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if
		// you don't want to weaken
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

		// here's the special part:
		// -- need to create an SSL Socket Factory, to use our weakened
		// "trust strategy";
		// -- and create a Registry, to register it.
		//
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
				sslContext, hostnameVerifier);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
				.<ConnectionSocketFactory> create()
				.register("http",
						PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslSocketFactory).build();

		// now, we create connection-manager using our Registry.
		// -- allows multi-threaded use
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);
		connMgr.setMaxTotal(maxTotal);
		connMgr.setDefaultMaxPerRoute(defaultMaxPerRoute);
		//在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证
		if(validateAfterInactivity>0){
		  connMgr.setValidateAfterInactivity(validateAfterInactivity);
		}
		b.setConnectionManager(connMgr);

		//request config
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout)
				.build();
		b.setDefaultRequestConfig(requestConfig);

		// finally, build the HttpClient;
		// -- done!
		closeableHttpClient = b.build();
	}



}