package com.example.UserManagement.serviceI;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.payloads.UserDto;

import java.util.List;

public interface UserI {



    UserDto addNewUser(UserDto user);

    UserDto getSingleUser(long userId);

    List<UserDto> allUser();

    void deletUser(long userId);
}
