package com.example.UserManagement.controller;

import com.example.UserManagement.Constant.AppConstant;
import com.example.UserManagement.entity.User;
import com.example.UserManagement.payloads.UserDto;
import com.example.UserManagement.serviceImpl.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
private UserImp userImp;
@PostMapping("/")
   public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto user){

    UserDto userDto = this.userImp.addNewUser(user);

    return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);

   }
@GetMapping("/{userId}")
   public ResponseEntity<UserDto> getSingleUser(@PathVariable long userId){

       UserDto user = this.userImp.getSingleUser(userId);

       return new ResponseEntity<>(user,HttpStatus.OK);
   }
   @GetMapping
   public ResponseEntity<List<UserDto>> getAllUser(){

       List<UserDto> userDtos = this.userImp.allUser();

       return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);

   }

   public ResponseEntity<?> deletUser(@PathVariable long userId){

    this.userImp.deletUser(userId);

    return new ResponseEntity<>(AppConstant.USER_DELETE_MESSAGE,HttpStatus.OK);

   }
}
