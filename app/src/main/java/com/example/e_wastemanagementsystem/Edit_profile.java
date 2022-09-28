package com.example.e_wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Edit_profile extends AppCompatActivity {
//Method for checking for digits
    public final boolean containsDigit(String s, int len) {
        boolean containsDigit = false;

    if(len == 0) {
        Toast.makeText(Edit_profile.this, "One or more field(s) is empty", Toast.LENGTH_SHORT).show();

    }
else
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    containsDigit = true;
                }

        }
        return containsDigit;
    }


    public static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static final String TAG = "TAG";
    //Variables

    EditText aName,aSurname,aPhone,aEmail,aDepartment;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();


        //get data from previous class

        Intent data = getIntent();
        String firstName = data.getStringExtra("name");
        String Surname = data.getStringExtra("Surname");
        String Email = data.getStringExtra("email");
        String Phone = data.getStringExtra("Phone number");
        String Department = data.getStringExtra("Department");

        Log.d(TAG, "onCreate" + firstName + " " + Surname + " " + Email + " " + Department + " " + Phone);
//Create Variables to store data retrieved

        //Get database instance
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        aName = findViewById(R.id.editName);
        aSurname = findViewById(R.id.editSurname);
        saveButton = findViewById(R.id.arrBtn);
        aEmail = findViewById(R.id.editMail);
        aPhone = findViewById(R.id.editPhone);
        aDepartment = findViewById(R.id.edtDepartment);

        //Set default text as the data retrieved from the previous class
        aName.setText(firstName);
        aSurname.setText(Surname);
        aEmail.setText(Email);
        aPhone.setText(Phone);
        aDepartment.setText(Department);


        saveButton.setOnClickListener(v -> {
            String email = aEmail.getText().toString().trim();
            String name1 = aName.getText().toString().trim();
            String Surname1 = aSurname.getText().toString().trim();
            String Phone1 = aPhone.getText().toString().trim();
            String department = aDepartment.getText().toString().trim();

            char Name1st = name1.charAt(0);
            char Surname1st = Surname1.charAt(0);
            int nameLength = name1.length();
            int SurnameLength = Surname1.length();

            if (Character.isLowerCase(Name1st) || containsDigit(name1,nameLength))
            {
                aName.setError("Your first name should not be empty, the First letter must be in Uppercase" + " and should not contain letters");

            } else if ( Character.isLowerCase(Surname1st) || containsDigit(Surname1,SurnameLength) )
            {

                aSurname.setError("Your Surname should not be empty and the First letter must be in Uppercase" + " and should not contain letters");
            }
            else if(Phone1.length() < 10 )
            {
                aPhone.setError("Phone number should be 10 digits and Phone number field should not be empty");
            }
            else if(email.isEmpty() || !isValid(email))
            {
                aEmail.setError(" Email is invalid or empty");
            }
            else if (department.isEmpty())
            {
                aDepartment.setError("Department field should not be empty");
            }
            else {

                user.updateEmail(email).addOnSuccessListener(aVoid -> {
                    DocumentReference docRef = fStore.collection("users").document(user.getUid());
                    Map<String, Object> edited = new HashMap<>();

                    edited.put("First Name", name1);
                    edited.put("Surname", Surname1);
                    edited.put("email", email);
                    edited.put("Phone number", Phone1);
                    edited.put("Department", department);
                    docRef.update(edited).addOnSuccessListener(aVoid1 -> {
                        Toast.makeText(Edit_profile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    });


                    Toast.makeText(Edit_profile.this, "Email updated", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> Toast.makeText(Edit_profile.this, "Email is not changed", Toast.LENGTH_SHORT).show());

            }


        });




    }
}