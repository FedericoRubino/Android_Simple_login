package com.example.simple_login;

import android.os.Bundle;

import com.example.simple_login.ClassObjects.User;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;


public class LoginActivityUnitTest {

    @Test
    public void checkForValidUsernameTest(){
        String uName = "din_djarin";
        LoginActivity loginActivity = new LoginActivity();
        List<User> users = loginActivity.checkForValidUsername(uName);
        assertTrue(users.size() > 0);
    }


    @Test
    public void checkForInValidUsernameTest(){
        String uName = "FooBar";
        LoginActivity loginActivity = new LoginActivity();
        List<User> users = loginActivity.checkForValidUsername(uName);
        assertTrue(users == null);
    }



}
