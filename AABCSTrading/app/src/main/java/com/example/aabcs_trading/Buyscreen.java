package com.example.aabcs_trading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class Buyscreen extends AppCompatActivity implements View.OnClickListener {
    private double Stockshares1;
    private double Stockshares2;
    private double Stockshares3;
//private static Depositscreen = new Depositscreen();
    double balance = Depositscreen.balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
        setTitle("Investment Screen");
        Button FordBuy = findViewById(R.id.Fordbuy);
        Button TwitterBuy = findViewById(R.id.Twitterbuy);
        Button AppleBuy = findViewById(R.id.Applebuy);

        FordBuy.setOnClickListener(this);
        TwitterBuy.setOnClickListener(this);
        AppleBuy.setOnClickListener(this);
    }

    public void buy() {
        //System.out.print(balance);

       // Toast.makeText(this,"The balance is : " + balance, Toast.LENGTH_SHORT).show();

        if(balance <= 0 ) {
            Toast.makeText(this,"You have no money. ", Toast.LENGTH_SHORT).show();
        }
        else if(balance >= 1 ) {
            Toast.makeText(this,"You bought a stock with this:  " + balance, Toast.LENGTH_SHORT).show();
        }

        Stockshares1 = balance / 19.87;
        Stockshares2 = balance / 53.36;
        Stockshares3 = balance / 150.04;

        balance = 0;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Fordbuy:
                buy();
                Toast.makeText(this,"The amount of shares you have for this company is: " + Stockshares1, Toast.LENGTH_SHORT).show();
                  break;
            case R.id.Twitterbuy:
                buy();
                Toast.makeText(this,"The amount of shares you have for this company is: " + Stockshares2, Toast.LENGTH_SHORT).show();
                break;
            case R.id.Applebuy:
                buy();
                Toast.makeText(this,"The amount of shares you have for this company is: " + Stockshares3, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}