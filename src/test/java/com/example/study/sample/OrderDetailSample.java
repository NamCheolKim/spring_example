package com.example.study.sample;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.OrderStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class OrderDetailSample extends StudyApplicationTests {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void createOrder(){

        List<User> userList = userRepository.findAll();

        for(int j = 0; j < 1; j++){
            User user = userList.get(j);
            item(user);

        }


        userList.forEach(user -> {

            int orderCount = random.nextInt(10) + 1;
            for (int i = 0; i < orderCount; i++) {
                item(user);
            }
        });


    }


    private void item(User user){
        double totalAmount = 0;

        List<Item> items = new ArrayList<>();
        List<OrderDetail> orderHistoryDetails = new ArrayList<>();


        int itemCount = random.nextInt(10)+1;
        for(int i = 0 ; i < itemCount; i ++){
            // db에 아이템 갯수가 총 500개 ( * 자신의 샘플에 맞추세요 )
            int itemNumber = random.nextInt(405)+1;

            Item item = itemRepository.findById((long)itemNumber).get();
            totalAmount += item.getPrice().doubleValue();
            items.add(item);
        }


        int s = random.nextInt(3)+1;
        OrderStatus status = OrderStatus.WAITING;
        PaymentType paymentType = PaymentType.CREDIT_CARD;
        switch (s){
            case 1 :
                status = OrderStatus.WAITING;
                paymentType = PaymentType.CREDIT_CARD;
                break;

            case 2 :
                status = OrderStatus.SENDING;
                paymentType = PaymentType.ACCOUNT_TRANSFER;
                break;

            case 3 :
                status = OrderStatus.COMPLETE;
                paymentType = PaymentType.CREDIT_CARD;
                break;
        }

        int t = random.nextInt(2)+1;
        OrderType type = t==1? OrderType.ALL:OrderType.INDIVIDUAL;


        OrderGroup orderGroup = OrderGroup.builder()
                .user(user)
                .status(status)
                .orderType(type)
                .revAddress("경기도 분당구 판교역로")
                .revName(user.getEmail())
                .paymentType(paymentType)
                .totalPrice(new BigDecimal(totalAmount))
                .orderAt(getRandomDate())
                .totalQuantity(itemCount)
                .arrivalDate(getRandomDate().plusDays(3))
                .orderDetailList(orderHistoryDetails)
                .build();

        orderGroupRepository.save(orderGroup);



        for(Item item : items){

            OrderStatus orderDetailStatus = OrderStatus.WAITING;
            switch (random.nextInt(3)+1){
                case 1 :
                    orderDetailStatus = OrderStatus.WAITING;
                    break;

                case 2 :
                    orderDetailStatus = OrderStatus.SENDING;
                    break;

                case 3 :
                    orderDetailStatus = OrderStatus.COMPLETE;
                    break;
            }


            OrderDetail orderDetail = OrderDetail.builder()
                    .orderGroup(orderGroup)
                    .item(item)
                    .quantity(itemCount)
                    .totalPrice(new BigDecimal(totalAmount))
                    .arrivalDate(type.equals(OrderType.ALL) ? orderGroup.getArrivalDate() : getRandomDate())
                    .status(type.equals(OrderType.ALL) ? status : orderDetailStatus)
                    .build();
            orderDetailRepository.save(orderDetail);
            orderHistoryDetails.add(orderDetail);
        }


    }


    private LocalDate getRandomDate(){
        return LocalDate.of(2019,getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}