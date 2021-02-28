package com.imooc.controller;


import com.imooc.service.StuService;
import com.imooc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController // json object
@RequestMapping(value = "passport",  method = RequestMethod.POST)
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public HttpStatus usernameIsExist(@RequestParam String username) {

        if (StringUtils.isBlank(username)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        boolean isExist = userService.queryUsernameIsExist(username);

        if (isExist) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

}
