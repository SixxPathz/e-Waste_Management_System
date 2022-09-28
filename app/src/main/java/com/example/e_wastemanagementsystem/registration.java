package com.example.e_wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Objects.requireNonNull(getSupportActionBar()).hide();
        TextView regLink = findViewById(R.id.registerLink);
        regLink.setOnClickListener(v -> startActivity((new Intent(getApplicationContext(), register.class))));
        EditText sMail = findViewById(R.id.email);
        EditText sPass = findViewById(R.id.edtPassword);
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        FirebaseAuth sAuth = FirebaseAuth.getInstance();
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView edtForgot = findViewById(R.id.forgotPass);

        //Reset password
        edtForgot.setOnClickListener(v -> {
            EditText resetMail = new EditText(v.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset password?");
            passwordResetDialog.setMessage("Enter your Email to receive reset link");
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
                //extract email and send
                String mail = resetMail.getText().toString();
                sAuth.sendPasswordResetEmail(mail).addOnSuccessListener(aVoid -> Toast.makeText(registration.this,"Reset Link Sent To Your Mail", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(registration.this,"Failed to send the rest link to your email" +e.getMessage(), Toast.LENGTH_SHORT).show());

            });
            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                //direct to login

            });

            passwordResetDialog.create().show();
        });


//Login
        btnLogin.setOnClickListener(v -> {
            String email = sMail.getText().toString().trim();
            String password = sPass.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                sMail.setError("Email is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                sPass.setError("Password required");
                return;
            }
            if (password.length() < 6) {
                sPass.setError("Password must be 6 characters long");
            }
            //Show progress
            progressBar.setVisibility(View.VISIBLE);

            //Authenticate User
            sAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(registration.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity((new Intent(getApplicationContext(), MainActivity.class)));
                } else {
                    Toast.makeText(registration.this, "Error encountered" + task.getException(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }


            });
        });


    }
}