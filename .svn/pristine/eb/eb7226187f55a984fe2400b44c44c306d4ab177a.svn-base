/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.web.view.bind;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.fcgo.weixin.common.log.LogCategory;

/**
 * CustomWebBindingInitializer
 * 
 * @author zhangxu
 */
public class CustomWebBindingInitializer implements WebBindingInitializer {

    protected Log logger = LogFactory.getLog(LogCategory.CONTROLLER.toString());

    private static final CustomDateEditor DATE_EDITOR = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, DATE_EDITOR);
    }
}
