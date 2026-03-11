package com.spring7mvc.lombokpractice.service;

import com.spring7mvc.lombokpractice.model.Beer;

import java.util.UUID;

public interface BeerService {
    public Beer getBeerByID(UUID id);
}
