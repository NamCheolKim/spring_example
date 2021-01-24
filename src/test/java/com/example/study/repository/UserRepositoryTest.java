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

        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@naver.com";
        String phoneNumber = "010-3455-7896";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder().account(account).password(password).status(status).email(email).build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-3456-7890");

        if(user != null){
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-------------- 주문내역 --------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수랑 : " + orderGroup.getTotalQuantity());

                System.out.println("-------------- 주문상세 --------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 = " + orderDetail.getItem().getPartner().getName());
                    System.out.println("카테고리 = " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문상태 : " + orderDetail.getStatus());
                    System.out.println("배송예정일 : " + orderDetail.getArrivalDate());

                });
            });
        }

        Assertions.assertNotNull(user);


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
