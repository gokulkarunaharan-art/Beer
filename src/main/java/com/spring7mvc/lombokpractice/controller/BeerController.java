package com.spring7mvc.lombokpractice.controller;


import com.spring7mvc.lombokpractice.model.Beer;
import com.spring7mvc.lombokpractice.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/beers")
public class BeerController {
    private final BeerService beerService;

    @GetMapping
    public List<Beer> getAllBears(){
        return beerService.getAllBeers();
    }

    @GetMapping("/{UUID}")
    public Beer getBeerById(@PathVariable("UUID") UUID id) {
        log.debug("get beer by id -- in controller");
        return beerService.getBeerByID(id);
    }

    @PostMapping
    public ResponseEntity<Beer> saveBeer(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/beers/"+savedBeer.getId().toString());
        return new ResponseEntity<>(savedBeer,headers, HttpStatus.CREATED);
    }

    @PutMapping("/{UUID}")

    public ResponseEntity updateBeerById(@PathVariable("UUID")  UUID id,@RequestBody Beer beer){
        beerService.updateBeerByID(id,beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{UUID}")
    public ResponseEntity deleteBeerByID(@PathVariable("UUID") UUID id){
        beerService.deleteBeerByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
