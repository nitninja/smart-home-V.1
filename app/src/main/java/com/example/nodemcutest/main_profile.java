package com.example.nodemcutest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class main_profile extends AppCompatActivity {

    FloatingActionButton floatout;
    TextView Name,Email;
    Button ON;
    Button OFF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_profile);
       // Switch aSwitch =findViewById(R.id.switch1);
//        aSwitch.setTextOff("off");
//        aSwitch.setTextOn("on");
        OFF=(Button) findViewById(R.id.offbutton);
        ON=(Button) findViewById(R.id.onbutton);
        floatout =(FloatingActionButton) findViewById(R.id.logout);
        Name=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            Name.setText(signInAccount.getDisplayName());
            Email.setText(signInAccount.getEmail());


        }

        ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STAT");

                myRef.setValue(1);
                Toast.makeText(main_profile.this, "LED is ON", Toast.LENGTH_SHORT).show();
            }
        });

        OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STAT");

                myRef.setValue(0);
                Toast.makeText(main_profile.this, "LED is OFF", Toast.LENGTH_SHORT).show();
            }
        });


        floatout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}