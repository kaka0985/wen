package com.tledu.service.impl;

import com.tledu.mapper.UserMapper;
import com.tledu.model.User;
import com.tledu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private  UserMapper userMapper;

    @Override
    public User checkUserNameUnique(String username) {
        return userMapper.checkUserNameUniqu(username);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public User grtUserByUserName(User user) {
        return userMapper.grtUserByUserName(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
