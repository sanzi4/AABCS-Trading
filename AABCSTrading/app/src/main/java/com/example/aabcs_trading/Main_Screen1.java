package com.example.aabcs_trading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_Screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_1);
        setTitle("Main Menu");
    }

    public void launchScreenBuy(View v) {
        //launch a new activity
        Intent i = new Intent(this, Buyscreen.class);
        startActivity(i);
    }

    public void launchScreenDeposit(View v) {
        //launch a new activity
        Intent i1 = new Intent(this, Depositscreen.class);
        startActivity(i1);
    }
}