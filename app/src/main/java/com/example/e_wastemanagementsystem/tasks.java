package com.example.e_wastemanagementsystem;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class tasks extends AppCompatActivity {
    FirebaseFirestore db;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    FirebaseAuth fAuth;
    List<User> list;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseFirestore.getInstance();

        CollectionReference documentReference = db.collection("Orders");

        recyclerView = findViewById(R.id.firestore_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        myAdapter = new MyAdapter(tasks.this, list);


        recyclerView.setAdapter(myAdapter);
ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(myAdapter));
touchHelper.attachToRecyclerView(recyclerView);

        showData();

    }

    public void showData() {
        db.collection("Orders").whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        for (DocumentSnapshot snapshot : task.getResult()) {
                            User model = new User(snapshot.getString("item"), snapshot.getString("category"), snapshot.getString("quantity"), snapshot.getString("weight"), snapshot.getString("userID"), snapshot.getString("customer"), snapshot.getString("docID"));
                            list.add(model);


                        }
                        myAdapter.notifyDataSetChanged();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(tasks.this, "Oops...Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


