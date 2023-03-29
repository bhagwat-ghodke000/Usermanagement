package com.example.UserManagement.serviceImpl;

import com.example.UserManagement.Constant.AppConstant;
import com.example.UserManagement.entity.User;
import com.example.UserManagement.exception.ResourceNotFoundException;
import com.example.UserManagement.payloads.UserDto;
import com.example.UserManagement.repository.userRepo;
import com.example.UserManagement.serviceI.UserI;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImp implements UserI {
    @Autowired
    private userRepo userRepo;
   @Autowired
    private ModelMapper modelMapper;

    private final String ACCOUNT_SID ="AC3d88b6c1f5e3eb6feefd9b354a4c7000";

    private final String AUTH_TOKEN = "ad716ae2d61913792383c99132316c72";

    private final String FROM_NUMBER = "+15074355502";



    @Override
    public UserDto addNewUser(UserDto user) {
        System.out.println(user);

        User user1 = this.modelMapper.map(user, User.class);

        User save = this.userRepo.save(user1);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(save.getMob()), new PhoneNumber(FROM_NUMBER), AppConstant.USER_DELETE_MESSAGE)
                .create();



        return this.modelMapper.map(save,UserDto.class);


    }

    @Override
    public UserDto getSingleUser(long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));

        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> allUser() {

        List<User> userList = this.userRepo.findAll();
        List<UserDto> list = userList.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void deletUser(long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        this.userRepo.delete(user);

    }
}
