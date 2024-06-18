package com.rookie.im.user.domain.req;

import com.rookie.im.common.domain.req.BaseRequest;
import com.rookie.im.user.domain.dto.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1810:35
 */
@Data
public class ImportUserReq extends BaseRequest {

    @ApiModelProperty("导入用户资料列表")
    private List<@Valid UserEntity> userList;
}
