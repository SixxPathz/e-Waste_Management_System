package com.example.e_wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
ImageView profButton,catButton,arrButton,btnOrder,btnStats;
TextView profButton2,catButton2,arrButton2,lblOrder,lblStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        profButton = findViewById(R.id.profileButton);
        profButton2 = findViewById(R.id.profileButton2);
        catButton2 = findViewById(R.id.catButton2);
        catButton = findViewById(R.id.catButton);
        arrButton = findViewById(R.id.imgArrange);
        arrButton2 = findViewById(R.id.lblArrange);
        lblOrder = findViewById(R.id.lblOrder);
        btnOrder = findViewById(R.id.imgOrder);
        lblStats = findViewById(R.id.edtStats);
        btnStats = findViewById(R.id.imgStats);


        profButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,profile.class)));
        profButton2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,profile.class)));
        catButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,categories.class)));
        catButton2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,categories.class)));
        arrButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,collection.class)));
        arrButton2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,collection.class)));
        btnOrder.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,tasks.class)));
        lblOrder.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,tasks.class)));
        btnStats.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,userStats.class)));
        lblStats.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,userStats.class)));
    }


    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity((new Intent(getApplicationContext(),registration.class)));
        finish();
    }
}