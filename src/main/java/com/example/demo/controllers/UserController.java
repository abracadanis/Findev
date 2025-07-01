package com.example.demo.controllers;

import com.example.demo.services.UserService;
import com.example.demo.services.so.user.UserInputSo;
import com.example.demo.services.so.user.UserSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserSo> registerUser (@RequestBody UserInputSo userInputSo){
        return new ResponseEntity<>(userService.createUser(userInputSo), HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasAuthority('user:readAll')")
    public ResponseEntity<List<UserSo>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<UserSo> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/{user_id}/{project_id}")
    public ResponseEntity<UserSo> attachUserToProject(@PathVariable("user_id") Long userId, @PathVariable("project_id") Long projectId) throws IllegalAccessException {
        return new ResponseEntity<>(userService.attachUserToProject(userId, projectId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{user_id}/{project_id}")
    public ResponseEntity<UserSo> detachUserFromProject(@PathVariable("user_id") Long userId, @PathVariable("project_id") Long projectId) throws IllegalAccessException {
        return new ResponseEntity<>(userService.detachUserFromProject(userId, projectId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.ACCEPTED);
    }

}
