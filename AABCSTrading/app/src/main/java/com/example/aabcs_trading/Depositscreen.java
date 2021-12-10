package com.example.aabcs_trading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class Depositscreen extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText EditText;
    private FirebaseAuth mAuth;
    private DatabaseReference dbReference;
    private FirebaseDatabase firebaseDatabase;
    private Calendar FirebaseAuth;
    private double balance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        setTitle("Deposit Screen");
        Button Submitbutton = findViewById(R.id.submit);
        Submitbutton.setOnClickListener(this);
        EditText = findViewById(R.id.textInputEditText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference();
        addData();
    }

    public void addData() {
        String balance = EditText.getText().toString();
        dbReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Deposit").setValue(balance);
    }

    public void deposit(int input) {
        if (input <= 0) {
            Toast.makeText(this, "Error: Deposit amount is negative." + input, Toast.LENGTH_SHORT).show();
        } else if (input >= 1 && input <= 1000) {
            Toast.makeText(this, "The amount that has been inputted is: " + input, Toast.LENGTH_SHORT).show();
        } else {
            balance += input;
            Toast.makeText(this, "Your balance is now: " + balance, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                Toast.makeText(this, "The amount that has been deposited is: " + balance, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}