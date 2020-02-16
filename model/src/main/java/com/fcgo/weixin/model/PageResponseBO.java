package com.fcgo.weixin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseBO<T> {

    /**
     * 总记录数
     */
    private int total;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 数据
     */
    private List<T> list;


}
