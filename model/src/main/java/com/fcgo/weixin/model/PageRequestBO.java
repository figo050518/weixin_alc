package com.fcgo.weixin.model;

import lombok.Data;

@Data
public class PageRequestBO {

    private int page = 1;
    private int size = 10;
}
