package com.fcgo.weixin.web.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.fcgo.weixin.common.log.LogCategory;

public class SystemConfigListener extends org.springframework.web.util.Log4jConfigListener {

    protected Log logger = LogFactory.getLog(LogCategory.CONTROLLER.toString());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ResourcePatternResolver res = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = res.getResources("classpath*:/context/properties/*.properties");
            for (Resource resource : resources) {
                initCofig(resource);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        super.contextInitialized(event);
    }

    private void initCofig(Resource resource) {
        Properties conf = new Properties();
        InputStream is = null;
        try {
            is = resource.getInputStream();
            conf.load(is);
            conf.putAll(System.getProperties());
            System.setProperties(conf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (null != is) {
                try {
                    is.close();
                }
                catch (IOException e) {
                    // ingore
                }
            }
        }
    }

}
