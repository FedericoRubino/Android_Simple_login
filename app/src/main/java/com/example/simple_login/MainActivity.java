package com.example.simple_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView displayMessage;
    private String message = "Hello ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        message += intent.getStringExtra("INTENT_EXTRA").toString();


        displayMessage = findViewById(R.id.displayMessage_tv);
        displayMessage.setText(message);
    }

    // ends the activity
    public void returnToLogin (View view){
        finish();
    }


    // factory intent method
    public static Intent getIntent(Context context, String name){

        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("INTENT_EXTRA", name);

        return intent;
    }
}
