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

import java.util.ArrayList;
import java.util.Hashtable;

public class SignUp extends AppCompatActivity {

    // Integrate Firebase to our Andorid App to use the email and password sign in feature
    private FirebaseAuth mAuth;

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
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();

         */

        // When user clicks to register the button we create a new user in Firebase
        signUp.setOnClickListener(view -> {
            createNewUser();
        });

        /*
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

         */
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