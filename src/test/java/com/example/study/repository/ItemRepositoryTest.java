package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();
        item.setName("16형 MacBook Pro");
        item.setPrice(3690000);
        item.setContents("2.3GHz 8코어 프로세서 1TB 저장 용량 AMD Radeon Pro 5500M");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);

    }

    @Test
    public void read(){

        Optional<Item> item = itemRepository.findById(1L);

        Assertions.assertTrue(item.isPresent());
    }
}
