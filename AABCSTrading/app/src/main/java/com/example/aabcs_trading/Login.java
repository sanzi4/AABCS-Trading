package com.example.aabcs_trading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    // Variable to store the email and passwords
    private EditText emailAddress;
    private EditText password;
    private Button logIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initalzie these variables to store the information the user inputs on the LogIn Screen based on the components on the UI screen
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.Passwords);
        logIn = findViewById(R.id.LogIn);

        // Initialzie Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // When the user clicks on the log in button call the logInExisitingUsers method
        logIn.setOnClickListener(view -> {
            logInExisitingUsers();
        });

    }

    // Method to Log in the user from Firebase Authefication
    private void logInExisitingUsers(){

        // Store all information in a String
        String email = emailAddress.getText().toString();
        String passwords = password.getText().toString();

        // Check if either the password or email box is empty and if it is display an error
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(passwords)){

            emailAddress.setError("All fields can not be empty");
            emailAddress.requestFocus();
        }
        // else all boxes are not empty and check if the user exisits within Firebase
        else{
            mAuth.signInWithEmailAndPassword(email, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        System.out.println("User logged in successfully");

                        // TODO Add the Home Screen to the following line of code so after the user registers it takes them to the home screen which is the investing page

                        // After loggin successfully take the user to the home screen
                        startActivity(new Intent(Login.this, Profile.class));
                    }
                    else{
                        System.out.println("Error with logging in users");
                        Toast.makeText(Login.this,"Logging in user error occured: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });

        }


    }
}