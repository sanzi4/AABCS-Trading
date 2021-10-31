package com.example.aabcs_trading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// Sanzida Sultana

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // The two buttons in the main activites UI screen to sign up or log in
    private Button signUp;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initalize the sign up button using the button id, signup
        signUp = (Button)findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

        // initlaize the sign up button using the button id, logIn
        logIn = (Button)findViewById(R.id.logIn);
        logIn.setOnClickListener(this);
    }

    // Switch to the sign up view controller
    @Override
    public void onClick(View v) {
        // When clicking a button we check their id button
        switch (v.getId()){
            case R.id.signUp:
                // change to the correct view controller based on class
                startActivity(new Intent(this, SignUp.class));
                break;
                // when the user clicks on the log in button switch the view controller to the Login Class
            case R.id.logIn:
                startActivity(new Intent(this, Login.class));
                break;
        }

    }
}