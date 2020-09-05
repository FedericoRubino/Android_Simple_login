package com.example.simple_login;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simple_login.ClassObjects.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.simple_login", appContext.getPackageName());
    }


    @Test
    public void getIntentTest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = MainActivity.getIntent(appContext,"foobar");

        assertTrue(intent.getExtras() != null);
    }

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

    @Test
    public void checkForValidPasswordTest(){
        List<User> users = new ArrayList<>();
        users.add(new User("din_djarin","baby_yoda_ftw","din","yoda"));
        LoginActivity loginActivity = new LoginActivity();
        User user = loginActivity.checkForValidPassword(users,"baby_yoda_ftw");
        assertTrue(user != null);
    }


    @Test
    public void checkForInValidPasswordTest(){
        List<User> users = new ArrayList<>();
        users.add(new User("din_djarin","baby_yoda_ftw","din","yoda"));
        LoginActivity loginActivity = new LoginActivity();
        User user = loginActivity.checkForValidPassword(users,"fooBar");
        assertTrue(user == null);
    }

}

