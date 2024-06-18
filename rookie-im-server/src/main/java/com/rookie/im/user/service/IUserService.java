package com.rookie.im.user.service;

import com.rookie.im.user.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rookie.im.user.domain.req.ImportUserReq;

/**
 * <p>
 * 菜鸟 IM 用户表 服务类
 * </p>
 *
 * @author destinal
 * @since 2024-06-18
 */
public interface IUserService  {

    User getUserById(Integer id);

    void importUsers(ImportUserReq req);
}
