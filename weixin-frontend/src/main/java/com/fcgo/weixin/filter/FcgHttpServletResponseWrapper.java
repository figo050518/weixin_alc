package com.fcgo.weixin.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author xiahan
 */
public class FcgHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private int httpStatus;

    public FcgHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        this.httpStatus = sc;
        super.sendError(sc, msg);
    }

    @Override
    public void sendError(int sc) throws IOException {
        this.httpStatus = sc;
        super.sendError(sc);
    }

    @Override
    public void setStatus(int sc) {
        this.httpStatus = sc;
        super.setStatus(sc);
    }

    @Override
    public void setStatus(int sc, String sm) {
        this.httpStatus = sc;
        super.setStatus(sc, sm);
    }

    public int getHttpStatus() {
        return httpStatus;
    }

}
