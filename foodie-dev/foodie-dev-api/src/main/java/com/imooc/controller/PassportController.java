package com.imooc.controller;


import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public IMOOCJSONResult regist(@RequestBody UserBo userBo) {
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
        userService.createUser(userBo);

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



        return IMOOCJSONResult.ok();
    }

}
