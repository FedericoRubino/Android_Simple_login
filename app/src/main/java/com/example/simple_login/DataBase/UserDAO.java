package com.example.simple_login.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.simple_login.ClassObjects.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    public void insert(User user);

    @Delete
    public void delete(User user);

    @Update
    public void update(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    public List<User> getAllUsers();

}
