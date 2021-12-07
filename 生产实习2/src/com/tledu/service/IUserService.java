package com.tledu.service;

import com.tledu.model.User;

import java.util.List;

public interface IUserService {
    User checkUserNameUnique(String username);

    void add(User user);

    User grtUserByUserName(User user);

    List<User> getUserList();
}
