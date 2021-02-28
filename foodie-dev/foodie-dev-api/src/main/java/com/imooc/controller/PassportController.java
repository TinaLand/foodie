package com.imooc.controller;


import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // json object
// key value pair must be added, otherwise, will have error
@RequestMapping(value = "passport", method = RequestMethod.POST)
public class PassportController {

    @Autowired
    private UserService userService;

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

}
