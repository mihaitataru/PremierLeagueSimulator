package com.premier.league.app.misc;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class PasswordValidator {
    public static byte[] encodePassword(String password){
        return password.getBytes(StandardCharsets.UTF_16);
    }

    public static boolean validatePassword(byte[] encodedPassword, String rawPassword){
        byte[] password = encodePassword(rawPassword);
        return Arrays.equals(password, encodedPassword);
    }
}
