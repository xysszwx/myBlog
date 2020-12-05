package com.zwx.blog.service;

import com.zwx.blog.pojo.User;

import java.util.List;

public interface UserService {
    List<User> checkUser(String username , String password);
    User selectByBlogId(Long id);
}
