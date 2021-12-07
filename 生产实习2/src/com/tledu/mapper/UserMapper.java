package com.tledu.mapper;


import com.tledu.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    User checkUserNameUniqu(String username);

    void add(User user);

    User grtUserByUserName(User user);

    @Select(" select * from t_user where face_token != '' ")
    List<User> getUserList();
}
