package com.spring7mvc.lombokpractice.service;

import com.spring7mvc.lombokpractice.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    Beer getBeerByID(UUID id);
    List<Beer> getAllBeers();
    Beer saveBeer(Beer beer);
    void updateBeerByID(UUID id, Beer beer);
    void deleteBeerByID(UUID id);
}
