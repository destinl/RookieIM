package com.rookie.im.user.domain.resp;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1811:48
 */
@Data
public class ImportUserResp {
    private List<String> errorImportUserNames;
}
