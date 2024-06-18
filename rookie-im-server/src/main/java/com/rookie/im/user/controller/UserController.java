package com.rookie.im.user.controller;


import com.rookie.im.common.domain.resp.ApiResult;
import com.rookie.im.common.domain.resp.PageResponse;
import com.rookie.im.user.domain.dto.UserEntity;
import com.rookie.im.user.domain.req.ImportUserReq;
import com.rookie.im.user.domain.resp.ImportUserResp;
import com.rookie.im.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 菜鸟 IM 用户表 前端控制器
 * </p>
 *
 * @author destinal
 * @since 2024-06-18
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户资料相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    public String getUser(){
        return userService.getUserById(5).getUserName();
    }

    @PutMapping("/import")
    @ApiOperation(value = "用户资料导入")
    public ApiResult<ImportUserResp> importUser(@RequestBody @Valid ImportUserReq req){
        ImportUserResp resp = userService.importUsers(req);
        return ApiResult.success(resp);
    }

    @GetMapping("/getAllUserInfo")
    @ApiOperation(value = "获取所有用户信息列表")
    public ApiResult<PageResponse<UserEntity>> getAllUserInfo(Long page, Integer pageSize, Long appId){
        PageResponse<UserEntity> allUser = userService.getAllUser(appId, page, pageSize);
        return ApiResult.success(allUser);
    }

}

