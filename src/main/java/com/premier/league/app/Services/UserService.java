package com.premier.league.app.Services;

import com.premier.league.app.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(String email, String password, String username);

    boolean checkCredentials(String email, String password);
}
