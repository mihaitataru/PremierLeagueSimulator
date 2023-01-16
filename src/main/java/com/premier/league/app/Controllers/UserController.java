package com.premier.league.app.Controllers;

import com.premier.league.app.Services.UserService;
import com.premier.league.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "api/v1/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> request){
        try{
            User user = userService.createUser(request.get("email"), request.get("password"), request.get("username"));
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "api/v1/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> request){
        try{
            if(userService.checkCredentials(request.get("email"), request.get("password")))
                return new ResponseEntity<>(request.get("email"), HttpStatus.OK);

            return new ResponseEntity<>(request.get("email"), HttpStatus.UNAUTHORIZED);
        } catch(NullPointerException e){
            return new ResponseEntity<>(request.get("email"), HttpStatus.NOT_FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
