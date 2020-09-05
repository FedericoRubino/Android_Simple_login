package com.example.simple_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simple_login.ClassObjects.User;
import com.example.simple_login.DataBase.AppDatabase;
import com.example.simple_login.DataBase.UserDAO;

import java.util.List;

import static android.graphics.Color.rgb;

public class LoginActivity extends AppCompatActivity {

    // wiring up buttons and entry fields
    private EditText usernameEntry;
    private EditText passwordEntry;

    // database stuff
    private UserDAO userDAO;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing the wiring up
        passwordEntry = findViewById(R.id.password_entry);
        usernameEntry = findViewById(R.id.username_entry);

        initializeDAO();

        userList = userDAO.getAllUsers();
        if(userList.isEmpty()){
            // this is the predefined user
            userDAO.insert(new User("din_djarin","baby_yoda_ftw","din","yoda"));
            userDAO.insert(new User("a","a","din","yoda"));
        }
    }

    private void initializeDAO(){
        userDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbUserName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();
    }

    // method that checks username input and checks if that username exists
    public List<User> checkForValidUsername(String username){

        if(username.matches("")){
            Toast.makeText(this, "You need to enter a Username", Toast.LENGTH_SHORT).show();
            return null;
        } else {
//            initializeDAO();
            List<User> foundUsers = userDAO.getUsersByUsername(username);
            if (foundUsers.isEmpty()) {
                Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
                usernameEntry.setBackgroundColor(rgb(150, 0, 100));
                return null;
            }
            return foundUsers;
        }
    }

    // method that checks passwords
    public User checkForValidPassword(List<User> foundUsers, String password) {

        if (password.matches("")) {
            Toast.makeText(this, "You need to enter a Password", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            for (User user : foundUsers) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
            Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
            passwordEntry.setBackgroundColor(rgb(150, 0, 100));
            passwordEntry.setText("");
            return null;
        }
    }


    // basic login function that checks if the login credentials are correct or not
    public void login(View view){
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();

        usernameEntry.setBackgroundColor(rgb(255,255,255));
        passwordEntry.setBackgroundColor(rgb(255,255,255));
        List<User> userList = checkForValidUsername(username);
        if(userList == null){ return; }
        User user = checkForValidPassword(userList, password);
        if(user == null){ return;}
        Intent intent = MainActivity.getIntent(getApplicationContext(), user.getFirstName());
        startActivity(intent);
        passwordEntry.setText("");
    }
}
