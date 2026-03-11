package com.spring7mvc.lombokpractice;

import com.spring7mvc.lombokpractice.controller.BeerController;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LombokPracticeApplicationTests {

    @Autowired
    private  BeerController beerController;

    @Test
    void contextLoads() {

    }
    @Test
    void getBeerById(){
        System.out.println(beerController.getBeerById(UUID.randomUUID()));
    }

}
