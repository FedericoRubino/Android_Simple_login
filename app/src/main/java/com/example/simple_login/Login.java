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

public class Login extends AppCompatActivity {

    // wiring up buttons and entry fields
    private Button loginButton;
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
        loginButton = findViewById(R.id.login_button);
        userDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbUserName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();

        userList = userDAO.getAllUsers();
        if(userList.isEmpty()){
            // this is the predefined user
            userDAO.insert(new User("din_djarin","baby_yoda_ftw","din","yoda"));
            userDAO.insert(new User("a","a","din","yoda"));
        }
//        userDAO.insert(new User("a","a","din","yoda"));

    }

    // basic login function that checks if the login credentials are correct or not
    public void login(View view){
        String username = usernameEntry.getText().toString();
        if(username.matches("")){
            Toast.makeText(this, "You need to enter a Username", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = passwordEntry.getText().toString();
        if(password.matches("")){
            Toast.makeText(this, "You need to enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }

        List<User> foundUsers = userDAO.getUsersByUsername(username);
        if(foundUsers.isEmpty()){
            Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
            usernameEntry.setBackgroundColor(rgb(150,0,100));
            return;
        } else {
            usernameEntry.setBackgroundColor(rgb(255,255,255));
            passwordEntry.setBackgroundColor(rgb(255,255,255));

            for(User user: foundUsers){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("USERNAME",user.getUsername());
                    startActivity(intent);
                    passwordEntry.setText("");
//                    usernameEntry.setBackgroundColor(rgb(255,255,255));
//                    passwordEntry.setBackgroundColor(rgb(255,255,255));
                    return;
                }
            }
            Toast.makeText(this,"Wrong password",Toast.LENGTH_SHORT).show();
            passwordEntry.setBackgroundColor(rgb(150,0,100));
            passwordEntry.setText("");
            return;
        }
    }
}
