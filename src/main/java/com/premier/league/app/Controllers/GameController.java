package com.premier.league.app.Controllers;

import com.premier.league.app.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(path = "api/v1/games/play")
    public ResponseEntity<String> playGame(@RequestBody Map<String,String> request){
        try{
            String response = gameService.playGame(request.get("homeTeam"), request.get("awayTeam"));
            if(response == "SUCCESS")
                return new ResponseEntity<>(response, HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "api/v1/games/playAll")
    public ResponseEntity<String> playAllGames(){
        try{
            gameService.playAllGames();
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
