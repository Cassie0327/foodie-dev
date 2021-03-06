package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAIQIAN04
 */
@Api(value="注册登录",tags={"用于注册登录的接口"})
@RestController
@RequestMapping("passport")
public class PassPortController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="用户名是否存在", notes="用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult udernameIsExist(@RequestParam  String username) {
        // 1. 判断用户名不为空
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        // 2. 查找注册的用户名是否存在
        boolean isExist =userService.qureyUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 3. 请求成功，用户名没有重复
        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value="用户注册", notes="用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult rigist(@RequestBody UserBO userBO) throws Exception {
        String username= userBO.getUsername();
        String password= userBO.getPassword();
        String confirmPasswaord=userBO.getConfirmPassword();
        // 0. 判断用户名和密码必须不为空
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)||StringUtils.isBlank(confirmPasswaord)){
            return IMOOCJSONResult.errorMsg("输入不得为空");
        }

        // 1. 查询用户名是否存在
        boolean isExist =userService.qureyUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 2. 密码长度不能少于6位
        if(password.length()<6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6位");
        }

        // 3. 判断两次密码是否一致
        if(!StringUtils.equals(password,confirmPasswaord))
        {
            return IMOOCJSONResult.errorMsg("两次密码不一致");
        }
        // 4. 实现注册

        userService.createUser(userBO);
        return IMOOCJSONResult.ok();
    }


}
