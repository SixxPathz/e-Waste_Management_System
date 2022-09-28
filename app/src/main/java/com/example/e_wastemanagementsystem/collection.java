package com.example.e_wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class collection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "TAG";
    boolean valid = false;
    EditText item,weight,quantity;
    Button arrangeBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String catSelected;
    FirebaseFirestore fireStore;
    String userID,docID;
    String name,surname,customer;
    String uItem,uCategory,uQuantity,uWeight,uUserID,uCustomer,uDocID;
    int Count1 = 0,Count2 = 0,Count3 = 0,Count4 = 0,Count5 = 0,Count = 0;


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
        setContentView(R.layout.activity_collection);

        arrangeBtn = findViewById(R.id.arrBtn);
        item = findViewById(R.id.edtItem);
        weight = findViewById(R.id.edtWeight);
        quantity = findViewById(R.id.edtQuantity);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID= Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);

        documentReference.addSnapshotListener(this, (documentSnapshot, e) -> {
            assert documentSnapshot != null;


            name = documentSnapshot.getString("Surname");
            surname = documentSnapshot.getString("First Name");
            customer = name+" "+surname;

        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            arrangeBtn.setText("Update");

            uItem =  bundle.getString("uItem");
            uQuantity = bundle.getString("uQuantity");
            uWeight = bundle.getString("uWeight");
            uDocID = bundle.getString("uDocID");
            uCategory= bundle.getString("uCategory");
            uCustomer = bundle.getString("uCustomer");
            uUserID = bundle.getString("uUserID");



            item.setText(uItem);
            quantity.setText(uQuantity);
            weight.setText(uWeight);



        }else{
            arrangeBtn.setText("Arrange Collection");

        }

        arrangeBtn.setOnClickListener(v -> {




            checkText(item);
            checkText(weight);
            checkText(quantity);

            if (valid) {
                String itemWeight = weight.getText().toString().trim();
                String itemName = item.getText().toString().trim();
                String itemQuantity = quantity.getText().toString().trim();
                userID= Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

                Bundle bundle1 = getIntent().getExtras();

                if(bundle1 != null)
                {
                    String id = uDocID;
                    updateToFireStore( catSelected, itemName, itemWeight, itemQuantity, userID, customer, id);
                }
                else {

                    docID = UUID.randomUUID().toString();
                    add_order(catSelected, itemName, itemWeight, itemQuantity, userID, customer, docID);
                }

            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        catSelected = parent.getItemAtPosition(pos).toString().trim();
        Toast.makeText(this, catSelected + " " + pos, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void updateToFireStore(String cat,String itm,String w,String qty,String id,String c,String docid)
    {
fStore.collection("Orders").document(docid).update("item",itm,"weight",w,"category",cat,"quantity",qty,"userID",id,"customer",c).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful())
        {
            Toast.makeText(collection.this,"Data updated", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),tasks.class);
            startActivity(i);
        }else {
            Toast.makeText(collection.this,"Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(collection.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
    }


    public void add_order(String cat,String itm,String w,String qty,String id,String c,String docid)
    {

        HashMap<String,Object> map = new HashMap<>();
        map.put("item",itm);
        map.put("category",cat);
        map.put("quantity",qty);
        map.put("weight",w);
        map.put("userID",id);
        map.put("customer",c);
        map.put("docID",docid);

        fStore.collection("Orders").document(docid).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(collection.this,"Data Saved", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(collection.this,"Failed to save data", Toast.LENGTH_SHORT).show();
            }
        });

        }

}



