package com.spring7mvc.lombokpractice.service;

import com.spring7mvc.lombokpractice.model.Beer;
import com.spring7mvc.lombokpractice.model.BeerStyle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeerServiceImpl implements BeerService{
    private Map<UUID, Beer> beerMap;


    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Cosmic Haze")
                .beerStyle(BeerStyle.IPA)
                .upc("789012")
                .price(new BigDecimal("14.99"))
                .quantityOnHand(85)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Dark Matter")
                .beerStyle(BeerStyle.STOUT)
                .upc("345678")
                .price(new BigDecimal("11.49"))
                .quantityOnHand(60)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);


    }

    @Override
    public List<Beer> getAllBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer saveBeer(Beer beer) {
        Beer savedBear = Beer.builder()
                .id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .version(beer.getVersion())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        beerMap.put(savedBear.getId(), savedBear);
        return savedBear;
    }

    @Override
    public Beer getBeerByID(UUID id) {
         return  beerMap.get(id);
    }

    @Override
    public void updateBeerByID(UUID id, Beer beer){
        Beer existingBeer = beerMap.get(id);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setUpdatedDate(LocalDateTime.now());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        beerMap.put(existingBeer.getId(),existingBeer);
    }

    @Override
    public void deleteBeerByID(UUID id){
        beerMap.remove(id);
    }

}
