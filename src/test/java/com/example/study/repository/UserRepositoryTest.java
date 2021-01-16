package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("Test01");
        user.setEmail("Test01@naver.com");
        user.setPhoneNumber("010-3456-7890");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Test01");

        User newUser = userRepository.save(user);
        System.out.println("newUser = " + newUser);

    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findById(5L);

        user.ifPresent(selectUser ->{

            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });

    }

    @Test
    @Transactional
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

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent());

    }
}
