package com.spring7mvc.lombokpractice.service;

import com.spring7mvc.lombokpractice.model.Beer;
import com.spring7mvc.lombokpractice.model.BeerStyle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public Beer getBeerByID(UUID id) {
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
