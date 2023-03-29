package com.example.UserManagement.payloads;

import lombok.Data;

@Data
public class UserDto {

    private long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String mob;

    private String DOB;

    private String gender;

    private String country;

    private String state;

    private String city;

    private String password;
}
