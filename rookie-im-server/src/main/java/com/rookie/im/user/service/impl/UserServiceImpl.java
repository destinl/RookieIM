package com.rookie.im.user.service.impl;

import com.rookie.im.user.dao.UserDao;
import com.rookie.im.user.domain.entity.User;
import com.rookie.im.user.domain.req.ImportUserReq;
import com.rookie.im.user.service.IUserService;
import com.rookie.im.user.service.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/6/1810:17
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer id){
        return userDao.lambdaQuery().eq(User::getId, id).one();
    }

    @Override
    public void importUsers(ImportUserReq req) {
        req.getUserList().forEach(e -> {
            User user = UserAdapter.importUserSave(e);
            userDao.save(user);
        });
    }
}
