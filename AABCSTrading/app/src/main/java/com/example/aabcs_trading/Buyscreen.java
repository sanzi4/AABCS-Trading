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

    public void buy(double balance) {

        if(balance <= 0 ) {
            System.out.println("You can't buy anything!");
        }
        else if(balance >= 1 && balance <= 1000 ) {
            System.out.println("You bought this stock:");
        }

        //create a hashmap
        HashMap<String,Double> StockList = new HashMap<String, Double>();

        //add keys and values
        StockList.put("Ford Price", 19.87);
        StockList.put("Twitter Price", 53.36);
        StockList.put("Apple Price", 150.04);

        //Formula works
        Stockshares1 = balance / StockList.get("Ford Price ");
        Stockshares2 = balance / StockList.get("Twitter Price ");
        Stockshares3 = balance / StockList.get("Apple Price ");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Fordbuy:
                Toast.makeText(this,"The amount of shares you have for this company is: " + Stockshares1, Toast.LENGTH_SHORT).show();
            break;
            case R.id.Twitterbuy:
                Toast.makeText(this,"The amount of shares you have for this company is: " + Stockshares2, Toast.LENGTH_SHORT).show();
                break;
            case R.id.Applebuy:
                Toast.makeText(this,"The amount of shares you have for this company is: " + Stockshares3, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}