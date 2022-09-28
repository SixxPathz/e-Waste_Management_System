package com.example.e_wastemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Button btnStats = findViewById(R.id.arrBtn);

        btnStats.setOnClickListener(v -> startActivity(new Intent(stats.this,userStats.class)));
    }
}