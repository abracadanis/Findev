package com.example.demo.Controllers;

import com.example.demo.Services.UserService;
import com.example.demo.Services.so.UserInputSo;
import com.example.demo.Services.so.UserSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/")
    public ResponseEntity<List<UserSo>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSo> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @PostMapping("/{user_id}/{project_id}")
    public ResponseEntity<UserSo> setProject(@PathVariable("user_id") Long userId, @PathVariable("project_id") Long projectId){
        return new ResponseEntity<>(userService.setProject(userId, projectId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{user_id}/{project_id}")
    public ResponseEntity<UserSo> removeProject(@PathVariable("user_id") Long userId, @PathVariable("project_id") Long projectId){
        return new ResponseEntity<>(userService.removeProject(userId, projectId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProject(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.deleteProject(id), HttpStatus.ACCEPTED);
    }

}
