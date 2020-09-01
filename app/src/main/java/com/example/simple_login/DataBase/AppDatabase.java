package com.example.simple_login.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.simple_login.ClassObjects.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // the name of the table
    public static final String USER_TABLE = "user";

    // abstract method that returns the DAO
    public abstract UserDAO getUserDAO();

}
