package com.example.e_wastemanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class profile extends AppCompatActivity {

    TextView name,surname,email,department,phone;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String userID;
ImageView profileImage,back;
Button edit;
    StorageReference storageReference;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();
        name = findViewById(R.id.profileName);
        surname = findViewById(R.id.profileSurname);
        email = findViewById(R.id.profileEmail);
        phone = findViewById(R.id.profilePhone);
        department = findViewById(R.id.profileDepartment);
        edit = findViewById(R.id.editBtn);
        back = findViewById(R.id.backbtn);


        back.setOnClickListener(v -> startActivity(new Intent(profile.this,MainActivity.class)));
       profileImage =findViewById(R.id.profileImage);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        userID= Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        storageReference = FirebaseStorage.getInstance().getReference();


//Retrieve user data and display

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, (documentSnapshot, e) -> {
            assert documentSnapshot != null;
            phone.setText(documentSnapshot.getString("Phone number"));
            surname.setText(documentSnapshot.getString("Surname"));
            name.setText(documentSnapshot.getString("First Name"));
            email.setText(documentSnapshot.getString("email"));
            department.setText(documentSnapshot.getString("Department"));

            //AFTER RETRIEVING DATA FROM DATA BASE ABOVE
            /*CREATE VARIABLES FOR THE DATA TO BE PASSED TO ANOTHER CLASS AND
            A NEW INTENT IS CREATED*/

            String Eemail = email.getText().toString().trim();
            String EName = name.getText().toString().trim();
            String ESurname = surname.getText().toString().trim();
            String EPhone = phone.getText().toString().trim();
            String EDepartment = department.getText().toString().trim();

            edit.setOnClickListener(v -> {
                Intent i  = new Intent(v.getContext(),Edit_profile.class);
                i.putExtra("name",EName);
                i.putExtra("Surname",ESurname);
                i.putExtra("email",Eemail);
                i.putExtra("Department",EDepartment);
                i.putExtra("Phone number",EPhone);
                startActivity(i);
            });

        });

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profileImage));

        profileImage.setOnClickListener(v -> {
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGalleryIntent,1000);
        });

    }
    //Change Image

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == Activity.RESULT_OK){

                Uri imageUri = Objects.requireNonNull(data).getData();
                uploadImageToFirebase(imageUri);
            }
        }

    //Upload the image to firebase database
    private void uploadImageToFirebase(Uri imageUri) {
        // upload image to firebase storage
        final StorageReference fileRef = storageReference.child("users/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profileImage))).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed.", Toast.LENGTH_SHORT).show());

    }


}
