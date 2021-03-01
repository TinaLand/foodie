package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "user BO", description = "from client side, encapsulate customer's data in this entity")
public class UserBo {

    @ApiModelProperty(value = "username", name = "username", example = "imooc", required = true)
    private String username;

    @ApiModelProperty(value = "password", name = "password", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "confirmPassword", name = "confirmPassword", example = "123456", required = true)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
