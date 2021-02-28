package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    //private Sid sid;
    public Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

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

        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set default nickname same as user name
        user.setNickname(userBo.getUsername());
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));

        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        return null;
    }
}
