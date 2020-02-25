package com.fcgo.weixin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class PageRequestBO {

    private int page = 1;
    private int size = 10;
}
