package com.spring7mvc.lombokpractice.controller;

import com.spring7mvc.lombokpractice.model.Beer;
import com.spring7mvc.lombokpractice.service.BeerService;
import com.spring7mvc.lombokpractice.service.BeerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();

    @Test
    void saveBeer() throws Exception {
        Beer beer = beerServiceImpl.getAllBeers().getFirst();
        beer.setVersion(null);
        beer.setId(null);
        String json = objectMapper.writeValueAsString(beer);

        given(beerService.saveBeer(any(Beer.class))).willReturn(beerServiceImpl.getAllBeers().get(1));
        mockMvc.perform(
                post("/api/beers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("Location"));
    }

    @Test
    public void getAllBeers() throws Exception {

        given(beerService.getAllBeers()).willReturn(beerServiceImpl.getAllBeers());

        mockMvc.perform(
                get("/api/beers")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    public void getBeerByID() throws Exception {
        Beer testBeer = beerServiceImpl.getAllBeers().getFirst();

        given(beerService.getBeerByID(testBeer.getId())).willReturn(testBeer);

        mockMvc.perform(get("/api/beers/"+ testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())));
    }

    @Test
    public void updateBeerByID() throws Exception {
        Beer testBeer = beerServiceImpl.getAllBeers().getFirst();
        String json = objectMapper.writeValueAsString(testBeer);

        mockMvc.perform(
                put("/api/beers/"+testBeer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isNoContent());
        verify(beerService).updateBeerByID(any(UUID.class),any(Beer.class));
    }

    @Test
    public void deleteBeerByID() throws Exception {
        Beer testBeer = beerServiceImpl.getAllBeers().getFirst();

        mockMvc.perform(
                delete("/api/beers/"+testBeer.getId())

        )
                .andExpect(status().isNoContent());
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(beerService).deleteBeerByID(argumentCaptor.capture());
        assertThat(testBeer.getId()).isEqualTo(argumentCaptor.getValue());
    }

}