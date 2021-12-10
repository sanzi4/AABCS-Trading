package com.example.aabcs_trading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class SignUp extends AppCompatActivity {

    // Integrate Firebase to our Andorid App to use the email and password sign in feature
    private FirebaseAuth mAuth;
    private DatabaseReference dbReference;
    private FirebaseDatabase firebaseDatabase;

    // Variables to hold First Name, Last Name, University, Email and etc
    private EditText fullName;
    private EditText email;
    private EditText password;
    private EditText universityName;
    private CheckBox firstCheck, secondCheck, thirdCheck;
    private ArrayList<Integer> checkbox_Results;
    private Button signUp;
    Hashtable<String, String> userDict = new Hashtable<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initalize variables to ID on the UI screen
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        universityName = findViewById(R.id.college);
        signUp = (Button)findViewById(R.id.registerButton);
        firstCheck = findViewById(R.id.choice1);
        secondCheck = findViewById(R.id.choice2);
        thirdCheck = findViewById(R.id.choice3);
        checkbox_Results = new ArrayList<>();

        // Initialzie Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference();

        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();

         */

        // When user clicks to register the button we create a new user in Firebase
        signUp.setOnClickListener(view -> {
            createNewUser();
        });

        // When the user clicks any of the checkbox
        firstCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstCheck.isChecked()){
                    checkbox_Results.add(50);
                }
                else{
                    checkbox_Results.remove(50);
                }

            }
        });


        secondCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secondCheck.isChecked()){
                    checkbox_Results.add(100);
                }
                else{
                    checkbox_Results.remove(100);
                }

            }
        });


        thirdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thirdCheck.isChecked()){
                    checkbox_Results.add(200);
                }
                else{
                    checkbox_Results.remove(200);
                }

            }
        });

    }

    // Create a new user within Firebase
    private void createNewUser(){

        // Store all information in a String
        String name = fullName.getText().toString();
        String emails = email.getText().toString();
        String passwords = password.getText().toString();
        String university = universityName.getText().toString();
        int investMoney = checkbox_Results.get(0);

        if(TextUtils.isEmpty((name)) || TextUtils.isEmpty((emails)) || TextUtils.isEmpty((passwords)) ||
                TextUtils.isEmpty((university))){

            fullName.setError("Fields can not be left empty");
            fullName.requestFocus();
        }
        else{
            // if fields are not empty
            mAuth.createUserWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    // If the user creation is succesfull
                    if(task.isSuccessful()){

                        addToDatabase();

                        System.out.println("User succesfully created ");

                        // Take the user to the home screen
                        startActivity(new Intent(SignUp.this, Profile.class));
                    }

                    // else if there is an error creating the user display an error
                    else{
                        Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // Method to store the user's information to the databas after creating an account succesfully
    public void addToDatabase(){

        /*   Add information in the RealTime DataBase
        Full Name, Email Address, Password, The Unviersity the user goes to, How much money they would like to invest
        */

        String name = fullName.getText().toString();
        String emails = email.getText().toString();
        String university = universityName.getText().toString();
        int investMoney = checkbox_Results.get(0);

        // Store user infomation into a dictionary
        HashMap<String, Object> userDictionary = new HashMap<>();
        userDictionary.put("Full Name" , name);
        userDictionary.put("Email Address" , emails);
        userDictionary.put("University" , university);
        userDictionary.put("Money to Invest" , investMoney);
        userDictionary.put("Deposit" , 0);

        dbReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userDictionary);

    }
}