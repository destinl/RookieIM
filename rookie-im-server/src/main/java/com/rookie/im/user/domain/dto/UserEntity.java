package com.rookie.im.user.domain.dto;

import com.rookie.im.common.annotation.IsMobile;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1810:39
 */
@Data
public class UserEntity {

    private long appId;

    @NotNull
    private String userName;


    /**
     * 用户手机号
     */
    @NotNull
    @IsMobile
    private String mobile;


    /**
     * 用户邮箱
     */
    private String email;


    /**
     * 用户头像
     */

    private String avatar;

    /**
     * 用户性别（0：未知，1：男，2：女））
     */

    private Integer sex;

    /**
     * 个性签名
     */
    private String selfSignature;

//    private Integer disableAddFriend;


    private Integer friendAllowType;
}
