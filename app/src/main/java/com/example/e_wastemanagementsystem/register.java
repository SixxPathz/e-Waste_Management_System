package com.example.e_wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class register extends AppCompatActivity {



    public static final String TAG = "TAG";
    //variables
    EditText mName,mDepartment;
    EditText mSurname;
    EditText mPass;
    EditText mEmail;
    EditText mPhone;
    Button mButton;
    ProgressBar mProgress;
    FirebaseAuth fAuth;
    FirebaseFirestore fireStore;
    String userID;
    boolean valid = false;


    public boolean checkText(EditText textField)
    {
        if(textField.getText().toString().isEmpty())
        {
            textField.setError("Field is required");
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mName = findViewById(R.id.edtName);
        mSurname = findViewById(R.id.edtSurname);
        mPass = findViewById(R.id.pass);
        mEmail = findViewById(R.id.edtMail);
        mPhone = findViewById(R.id.edtPhone);
        mButton = findViewById(R.id.btnRegister);
        mDepartment = findViewById(R.id.edtDepartment);




        mProgress = findViewById(R.id.progressBar);
        TextView loginlinker = findViewById(R.id.loginLink);




        ///Unregistered accounts
        loginlinker.setOnClickListener(v -> startActivity(new Intent(register.this,registration.class)));

        //get current database instance
    fAuth = FirebaseAuth.getInstance();
    fireStore = FirebaseFirestore.getInstance();

    //Check if the user is already registered
        if (fAuth.getCurrentUser() != null)
        {
            startActivity((new Intent(getApplicationContext(),MainActivity.class)));
            finish();
        }

    //Register user & validate info given
        mButton.setOnClickListener(v -> {


checkText(mName);
checkText(mDepartment);
checkText(mPhone);
checkText(mSurname);
checkText(mPass);
checkText(mEmail);


            String email = mEmail.getText().toString().trim();
            String password = mPass.getText().toString().trim();
            String name = mName.getText().toString().trim();
            String Surname = mSurname.getText().toString().trim();
            String Phone = mPhone.getText().toString().trim();
            String department = mDepartment.getText().toString().trim();


            //Show progress
            mProgress.setVisibility(View.VISIBLE);
if (valid) {
    //Register users in firebase
    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
            //Store User data
            FirebaseUser users = fAuth.getCurrentUser();
            DocumentReference documentReference = fireStore.collection("users").document(users.getUid());

            Map<String, Object> user = new HashMap<>();
            user.put("First Name", name);
            user.put("Surname", Surname);
            user.put("email", email);
            user.put("Phone number", Phone);
            user.put("Department", department);
            documentReference.set(user).addOnSuccessListener(bVoid -> Log.d(TAG, "onSuccess: User profile is created for " + userID));

            //direct to main activity
            startActivity((new Intent(getApplicationContext(), MainActivity.class)));
            finish();


        }


    }).addOnFailureListener(e -> {

            Toast.makeText(register.this, "Failed to create account" , Toast.LENGTH_SHORT).show();
            mProgress.setVisibility(View.GONE);

    });

}
        });






    }


}