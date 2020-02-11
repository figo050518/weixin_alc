package com.fcgo.weixin.persist.mybatis.generator;

import java.io.InputStream;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorHelper {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        InputStream is = GeneratorHelper.class.getResourceAsStream("/context/mybatis/generate/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(null, null);
        Configuration config = cp.parseConfiguration(is);

        DefaultShellCallback callback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, null);

        myBatisGenerator.generate(null);
    }
}
