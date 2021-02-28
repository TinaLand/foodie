package com.imooc.service;

import com.imooc.pojo.Stu;

public interface UserService {

    /*
    * check if username exists
    * */
    public boolean queryUsernameIsExist(String username);

}
