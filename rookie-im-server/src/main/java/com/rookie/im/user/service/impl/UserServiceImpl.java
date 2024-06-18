package com.rookie.im.user.service.impl;

import com.rookie.im.common.exception.BusinessException;
import com.rookie.im.common.exception.UserExceptionEnum;
import com.rookie.im.user.dao.UserDao;
import com.rookie.im.user.domain.entity.User;
import com.rookie.im.user.domain.req.ImportUserReq;
import com.rookie.im.user.domain.resp.ImportUserResp;
import com.rookie.im.user.service.IUserService;
import com.rookie.im.user.service.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/6/1810:17
 */
@Service
public class UserServiceImpl implements IUserService {
    public static final int USER_IMPORT_MAX_LIMIT = 1;
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer id){
        return userDao.lambdaQuery().eq(User::getId, id).one();
    }

    @Override
    public ImportUserResp importUsers(ImportUserReq req) {

        if(req.getUserList().size() > USER_IMPORT_MAX_LIMIT){
            throw new BusinessException(UserExceptionEnum.OUT_BOUND_IMPORT_LIMIT);
        }
        ImportUserResp resp = new ImportUserResp();
        List<String> errUserName = new ArrayList<>();
        req.getUserList().forEach(e -> {
            User user = UserAdapter.importUserSave(e);
            boolean save = userDao.save(user);
            if(!save){
                errUserName.add(e.getUserName());
            }
        });
        resp.setErrorImportUserNames(errUserName);
        return resp;
    }
}
