package com.premier.league.app.Controllers;

import com.premier.league.app.Repositories.StadiumRepository;
import com.premier.league.app.entities.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/stadium")
public class StadiumController {
    @Autowired
    private StadiumRepository stadiumRepository;

    @PostMapping
    public ResponseEntity<Stadium> addStadium(@RequestBody Stadium stadium){
        try{
            stadiumRepository.save(stadium);
            return new ResponseEntity<>(stadium, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(stadium, HttpStatus.BAD_REQUEST);
        }
    }
}
