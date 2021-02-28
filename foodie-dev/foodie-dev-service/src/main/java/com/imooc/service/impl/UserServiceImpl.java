package com.imooc.service.impl;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        // Example 是要通过条件进行查询的
        //Example example = new Example(Users.class);
        Example userexample = new Example(Users.class);
        Example.Criteria userCriteria = userexample.createCriteria();
        userCriteria.andEqualTo("username", username);

        //Users result = usersMapper.selectOneByExample(userCriteria);
        Users result = usersMapper.selectOneByExample(userexample);
        return result == null ? false : true;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        Users user = new Users();
        user.setUsername(userBo.getUsername());
        user.setPassword(user.getPassword());


        return null;
    }
}
