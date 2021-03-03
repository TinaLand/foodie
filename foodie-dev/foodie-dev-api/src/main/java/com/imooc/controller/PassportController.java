package com.imooc.controller;


import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "register", tags = {"api for register and login"})
@RestController // json object
// key value pair must be added, otherwise, will have error
@RequestMapping(value = "passport", method = RequestMethod.POST)
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "check if user exist", notes = "check if user exist", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {

        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("user name should not be empty");
        }

        boolean isExist = userService.queryUsernameIsExist(username);

        if (isExist) {
            return IMOOCJSONResult.errorMsg("user name exist already");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "user register", notes = "user register", httpMethod = "POST")
    @PostMapping("/regist")
    //public IMOOCJSONResult regist(UserBo userBo) {
    public IMOOCJSONResult regist(@RequestBody UserBo userBo, HttpServletRequest request,
                                  HttpServletResponse response) {
        // did not check if user name exist or not

        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();

        // 0. check if user name and password are not empty
        //if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("user name should not be empty");
        }

        // 1. user name does not exist
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("user name already registered");
        }

        // 2. password should be at least len of 6
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("password should be len of at least 6");
        }

        // 3. check 2 password if same
        //if (password.equals(confirmPassword)) {
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("confirmed password does not match with password");
        }

        // 4. implement register
        Users user = userService.createUser(userBo);

//        if (StringUtils.isBlank(userBo.getUsername())) {
//            return IMOOCJSONResult.errorMsg("user name should not be empty");
//        }
//
//        if (StringUtils.isBlank(userBo.getPassword())) {
//            return IMOOCJSONResult.errorMsg("password should not be empty");
//        }
//
//        if (!userBo.getPassword().equals(userBo.getConfirmPassword())) {
//            return IMOOCJSONResult.errorMsg("password does not match");
//        }

        setNullProperty(user);
        // value 就会看到是加密的信息
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value = "user login", notes = "user login", httpMethod = "POST")
    @PostMapping("/login")
    //public IMOOCJSONResult regist(UserBo userBo) {
    public IMOOCJSONResult login(@RequestBody UserBo userBo,
                                HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        // did not check if user name exist or not

        String username = userBo.getUsername();
        String password = userBo.getPassword();

        // 0. check if user name and password are not empty
        //if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                ) {
            return IMOOCJSONResult.errorMsg("user name should not be empty");
        }

        //Users user = userService.queryUserForLogin(username, password);
        Users user = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (user == null) {
            return IMOOCJSONResult.errorMsg("user name and password does not match");
        }
//        return IMOOCJSONResult.ok();

        // jsonIgnore, 因为 users 是生成的
        // 所以最好保留初始的样子， 所以在这里进行设置
        // 因为数据库里面有了， 所以直接 设置就可以

        setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok(user);
    }


    @ApiOperation(value = "user logout", notes = "user logout", httpMethod = "POST")
    @PostMapping("/logout")
    private IMOOCJSONResult logout(@RequestParam String userId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        // cookie 是一个 key value pair 的形式， 通过删除 key 来删除 这个 session
        CookieUtils.deleteCookie(request, response, "user");
        return IMOOCJSONResult.ok();
    }


    private Users setNullProperty(Users user) {
        user.setPassword(null);
        user.setEmail(null);
        user.setMobile(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
        return user;
    }

}
