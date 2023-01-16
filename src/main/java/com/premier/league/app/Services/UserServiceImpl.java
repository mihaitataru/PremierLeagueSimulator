package com.premier.league.app.Services;

import com.premier.league.app.Repositories.UserRepository;
import com.premier.league.app.entities.User;
import com.premier.league.app.misc.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password, String username){
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(PasswordValidator.encodePassword(password));

        userRepository.save(user);
        return user;
    }

    public boolean checkCredentials(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new NullPointerException();
        }
        return PasswordValidator.validatePassword(user.getPassword(), password);
    }
}
