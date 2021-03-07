package com.imooc.controller;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // json object
@Api(value = "Shopping Cart Controller", tags = {"APIs for shopping cart"})
@RequestMapping("shopcart")
public class ShopCartController {

    @ApiOperation(value = "add item to shopping cart", notes = "add item to shopping cart", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response ) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        // TODO: when use login in front end, add item to shopping cart, update data in redis in backend

        return IMOOCJSONResult.ok();

//
//        HttpSession session = request.getSession();
//        session.setAttribute("userInfo", "new user");
//        session.setMaxInactiveInterval(3600);
//        session.getAttribute("userInfo");
//        //session.removeAttribute("userInfo");
//        return IMOOCJSONResult.ok();
    }
}
