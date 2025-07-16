package com.example.demo.configs.security;

import com.example.demo.model.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findUserByUsernameAndDeletedIsFalse(username);
        if(user.isPresent()){
            UserEntity userEntity = user.get();
            return User.builder()
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .authorities(userEntity.getRole().getGrantedAuthorities())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
