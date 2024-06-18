package com.rookie.im.user.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rookie.im.common.enums.YesOrNoEnum;
import com.rookie.im.user.domain.entity.User;
import com.rookie.im.user.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜鸟 IM 用户表 服务实现类
 * </p>
 *
 * @author destinal
 * @since 2024-06-18
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    public Page<User> getAllUser(Long appId, Page<User> userPage) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAppId, appId);
        lambdaQueryWrapper.eq(User::getForbiddenFlag, YesOrNoEnum.NO.getStatus());

        return userMapper.selectPage(userPage, lambdaQueryWrapper);
    }
}
