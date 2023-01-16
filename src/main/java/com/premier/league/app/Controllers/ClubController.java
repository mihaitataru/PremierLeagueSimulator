package com.premier.league.app.Controllers;

import com.premier.league.app.Services.ClubService;
import com.premier.league.app.entities.Club;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/clubs")
public class ClubController {
    @Autowired
    private ClubService clubService;

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Map<String, String> request){
        try{
            Club club = clubService.addClub(request.get("name"), Long.parseLong(request.get("stadiumId")), request.get("officialWebsite"));
            return new ResponseEntity<>(club, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/list/{name}")
    public ResponseEntity<Map<String,String>> getClub(@PathVariable String name){
        try{
            Map<String,String> response = clubService.getClub(name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/list")
    public ResponseEntity<Map<Integer,Map<String, String>>> getClubs(){
        try{
            Map<Integer,Map<String,String>> response = clubService.getClubs();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/reset", method = RequestMethod.PUT)
    public ResponseEntity<String> resetClubs(){
        try{
            clubService.resetGames();
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{name}/players")
    public ResponseEntity<Map<Integer,String>> getPlayers(@PathVariable String name){
        try{
            Map<Integer, String> response= clubService.getPlayerNames(name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
