package com.rookie.im.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1810:39
 */
@Data
@ApiModel("用户资料实体")
public class ModifyUserEntity {

    @ApiModelProperty("用户ID")
    private String userId;


    @ApiModelProperty("用户名")
    private String userName;
    /**
     * 用户手机号
     */
    @ApiModelProperty("用户手机号")
    private String mobile;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String email;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 用户性别（0：未知，1：男，2：女））
     */
    @ApiModelProperty("用户性别")
    private Integer sex;

    /**
     * 个性签名
     */
    @ApiModelProperty("用户个性签名")
    private String selfSignature;

//    private Integer disableAddFriend;

    @ApiModelProperty("加好友验证类型（Friend_AllowType） 1无需验证 2需要验证")
    private Integer friendAllowType;
}
