package com.zwx.blog.service;

import com.zwx.blog.dao.UserMapper;
import com.zwx.blog.pojo.User;
import com.zwx.blog.pojo.UserExample;
import com.zwx.blog.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> checkUser(String username, String password) {
        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();
        //where后面的条件
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Util.code(password));
        List<User> list  =  userMapper.selectByExample(userExample);
        return list;
        //return null;
    }

}
