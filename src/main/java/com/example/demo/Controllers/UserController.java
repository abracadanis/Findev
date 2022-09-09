package com.example.demo.Controllers;

import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.UserService;
import com.example.demo.Services.so.UserInputSo;
import com.example.demo.Services.so.UserSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "application")
@RestController
@RequestMapping(value = "/userapi")
public class UserController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserSo> createUser (@RequestBody UserInputSo userInputSo){
        return new ResponseEntity<>(userService.createUser(userInputSo), HttpStatus.CREATED);
    }

}
