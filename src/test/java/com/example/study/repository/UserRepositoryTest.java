package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("Test03");
        user.setEmail("Test03@naver.com");
        user.setPhoneNumber("010-3456-7890");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Test0");

        User newUser = userRepository.save(user);
        System.out.println("newUser = " + newUser);

    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            System.out.println("user = " + selectUser);
        });

    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setCreatedBy("clean02");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("Test02");

            userRepository.save(selectUser);
            System.out.println("selectUser = " + selectUser);
        });


    }

    public void delete(){

    }
}
