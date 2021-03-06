package com.example.aabcs_trading;
// Sanzida Sultana

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    // Profile Screen class
    private Button logout;
    private TextView name;
    private TextView uni;
    private TextView email;
    private TextView how_much_investing;
    private FirebaseUser mAuth;
    private DatabaseReference ref;
    private DatabaseReference userRef;
    private String userID;
    private String first_name;
    private String college_;
    private String email_Address;
    private String investment_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initalize and Assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // Perform Selection Item Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Home_page.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        return true;

                    case R.id.blog:
                        startActivity(new Intent(getApplicationContext(), Blog.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Initalzie these variables to store the information the user inputs on the LogIn Screen based on the components on the UI screen
        logout = (Button) findViewById(R.id.sign_out);
        name = (TextView) findViewById(R.id.names);
        uni = (TextView) findViewById(R.id.uni);
        email = (TextView) findViewById(R.id.emails);
        how_much_investing = (TextView) findViewById(R.id.investing);
        retrieveData();

        // when you click on the log out button it takes user to the main screen
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Sign out users
                FirebaseAuth.getInstance().signOut();

                // Take it the main screen
                startActivity(new Intent(Profile.this, MainActivity.class));

            }

        });
    }
    public void retrieveData(){

        // Get user ID information to display it to the screen
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        userID = mAuth.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child(userID);

        System.out.println("User Uid:  " + userID);

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                // if retrieving the datasnapshot is successful
                if(task.isSuccessful()){

                    // Retrieve the info for that userID
                    first_name = task.getResult().child("Full Name").getValue(String.class);
                    college_ = task.getResult().child("University").getValue(String.class);
                    email_Address = task.getResult().child("Email Address").getValue(String.class);
                    investment_money = String.valueOf(task.getResult().child("Money to Invest").getValue());

                    System.out.println(first_name);
                    System.out.println(college_);
                    System.out.println(email_Address);
                    System.out.println(investment_money);


                    // Place these information into the appropriate text field
                    name.setText(first_name);
                    uni.setText(college_);
                    email.setText(email_Address);
                    how_much_investing.setText(investment_money);
                    System.out.println("First Name is: " + first_name);
                }
                else{
                    System.out.println("What  is going on");
                }
            }
        });

    }
}
