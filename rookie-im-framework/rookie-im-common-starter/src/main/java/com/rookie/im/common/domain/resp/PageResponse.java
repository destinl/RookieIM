package com.rookie.im.common.domain.resp;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1815:39
 */
@Data
public class PageResponse<T> {

    private List<T> records;

    private Long total;

    private Long page;

    private Integer pageSize;

}
