package com.example.demo.Services;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.UserInputSo;
import com.example.demo.Services.so.UserSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    private UserMapper userMapper;

    @Autowired
    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public UserSo createUser(UserInputSo userInputSo){
        UserEntity userEntity = userMapper.mapToEntity(userInputSo);
        userRepo.save(userEntity);
        return userMapper.mapToSo(userEntity);
    }

//    public UserSo setProject(){
//
//    }
}
