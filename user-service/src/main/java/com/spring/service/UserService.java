package com.spring.service;

import com.spring.entity.User;

import java.util.List;

public interface UserService
{
    User create(User user);
    User getUserById(Integer id) throws  Exception;
    List<User> getAllUsers();
    User updateUserById(Integer id) throws Exception;
    String deleteUserById(Integer id) throws Exception;
}
