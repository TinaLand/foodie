package com.imooc.service;


import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;

public interface UserService {

    /*
    * check if username exists
    * */
    public boolean queryUsernameIsExist(String username);

    // 前端传过来的
    public Users createUser(UserBo userBo);

    public Users queryUserForLogin(String username, String password);

}
