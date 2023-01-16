package com.premier.league.app.Controllers;

import com.premier.league.app.Services.PlayerService;
import com.premier.league.app.entities.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping(path = "api/v1/player/add")
    public ResponseEntity<Map<String,String>> addPlayer(@RequestBody Map<String, String> request){
        try{
            Player player = playerService.addPlayer(request.get("name"), Integer.parseInt(request.get("age")), request.get("clubName"), request.get("link"));
            Map<String, String> response = playerService.getPlayer(player.getName());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "api/v1/player/{name}")
    public ResponseEntity<Map<String,String>> getPlayer(@PathVariable String name){
        try{
            Map<String, String> playerStats = playerService.getPlayer(name);
            if(playerStats == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(playerStats, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "api/v1/player/remove")
    public ResponseEntity<String> deletePLayer(@RequestBody Map<String, String> request){
        try{
            playerService.deletePlayer(request.get("name"));
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
