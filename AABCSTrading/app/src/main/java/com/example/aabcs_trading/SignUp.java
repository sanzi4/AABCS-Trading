package com.example.aabcs_trading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Hashtable;

public class SignUp extends AppCompatActivity {

    // Integrate Firebase to our Andorid App to use the email and password sign in feature
    private FirebaseAuth mAuth;



    // Variables to hold First Name, Last Name, University, Email and etc
    private EditText fullName;
    private EditText email;
    private EditText password;
    private EditText universityName;
    private EditText investmentMoney;
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
        investmentMoney = findViewById(R.id.investMoney);
        signUp = (Button)findViewById(R.id.registerButton);

        // Initialzie Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();

         */

        // When user clicks to register the button we create a new user in Firebase
        signUp.setOnClickListener(view -> {
            createNewUser();
        });
    }

    // Create a new user within Firebase
    private void createNewUser(){

        // Store all information in a String
        String name = fullName.getText().toString();
        String emails = email.getText().toString();
        String passwords = password.getText().toString();
        String university = universityName.getText().toString();
        String money = investmentMoney.getText().toString();


        if(TextUtils.isEmpty((name)) || TextUtils.isEmpty((emails)) || TextUtils.isEmpty((passwords)) ||
                TextUtils.isEmpty((university)) || TextUtils.isEmpty((money))){

            fullName.setError("Feilds can not be left empty");
            fullName.requestFocus();
        }
        else{
            // if fields are not empty
            mAuth.createUserWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    // If the user creation is succesfull
                    if(task.isSuccessful()){

                        //TODO Add the User infomation: Full Name, Email Address, Univeristy, How much they would like to invest to the Realtime Databas
                        /*
                        // Add information in the RealTime DataBase
                        DatabaseReference newUserRef = database.getReference("users");
                        userDict.put("Full Name ", name);
                        userDict.put("Email Address ", emails);
                        userDict.put("University ", university);
                        userDict.put("How much would they like to invest ", money);

                         */

                        System.out.println("User succesfully created ");

                        // TODO Add the Home Screen to the following line of code so after the user registers it takes them to the home screen which is the investing page

                        // Take the user to the home screen
                        startActivity(new Intent(SignUp.this, Login.class));
                    }

                    // else if there is an error creating the user display an error
                    else{
                        Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}