package com.rookie.im.user.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import com.rookie.im.common.enums.YesOrNoEnum;
import com.rookie.im.user.domain.dto.ModifyUserEntity;
import com.rookie.im.user.domain.dto.UserEntity;
import com.rookie.im.user.domain.entity.User;

import java.util.UUID;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1810:58
 */
public class UserAdapter {

    public static User importUserSave(ModifyUserEntity entity){

        User user = new User();
        BeanUtil.copyProperties(entity, user);

        user.setUserId(UUID.randomUUID().toString());
        user.setForbiddenFlag(YesOrNoEnum.NO.getStatus());

        return user;
    }

    public static UserEntity buildUserInfo(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtil.copyProperties(user, userEntity);
        return userEntity;
    }
}
