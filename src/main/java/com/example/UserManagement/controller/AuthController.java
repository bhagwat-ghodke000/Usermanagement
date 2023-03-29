package com.example.UserManagement.controller;

import com.example.UserManagement.payloads.JwtAuthRequest;
import com.example.UserManagement.payloads.UserDto;
import com.example.UserManagement.security.JwtAuthResponse;
import com.example.UserManagement.security.JwtTokenHelper;
import com.example.UserManagement.serviceI.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserI userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{

        System.out.println("Enter in Login Controller");

        this.authenticate(request.getUsername(),request.getPassword());

        System.out.println("Auth");

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());


        System.out.println("User "+userDetails);
        String token = this.jwtTokenHelper.generateToken(userDetails);
         System.out.println("Token "+token);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);


        return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        // TODO Auto-generated method stub
          System.out.println("In Authenatication ");
          System.out.println("User name "+username);
          System.out.println("User password "+password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        System.out.println("After auth "+usernamePasswordAuthenticationToken);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            System.out.println("After Manager");
        }catch(BadCredentialsException e) {

            throw new RuntimeException("User name and password is invilid");
        }

    }

    //Register Api;

//    @PostMapping("/register")
//
//    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
//        UserDto registerUser = this.userService.registernewUser(userDto);
//        return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
//
//    }

}

