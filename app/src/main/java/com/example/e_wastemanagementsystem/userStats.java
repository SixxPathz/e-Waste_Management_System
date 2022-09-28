package com.example.e_wastemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class userStats extends AppCompatActivity {
TextView majorCount,smallCount,ictCount,lightCount,sportsCount,medicalCount,totalItems,totalWeight;
TextView majorWeight,smallWeight,ictWeight,lightWeight,sportsWeight,medicalWeight;
   FirebaseFirestore db;
   FirebaseAuth fAuth;
   String userID;
   Integer result,result7;
   Double result1,result2,result3,result4,result5,result6,result8;

    double majorCount1=0,smallCount1=0,ictCount1=0,lightCount1=0,sportsCount1=0,medicalCount1 = 0;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats);
        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseFirestore.getInstance();
        majorCount = findViewById(R.id.majorCount);
        smallCount = findViewById(R.id.smallCount);
        ictCount = findViewById(R.id.ictCount);
        lightCount = findViewById(R.id.lightCount);
        sportsCount = findViewById(R.id.sportsCount);
        medicalCount = findViewById(R.id.medicalCount);
        majorWeight = findViewById(R.id.majorWeight);
        smallWeight = findViewById(R.id.smallWeight);
        ictWeight = findViewById(R.id.ictWeight);
        lightWeight = findViewById(R.id.lightWeight);
        sportsWeight = findViewById(R.id.sportsWeight);
        medicalWeight = findViewById(R.id.medicalWeight);
        totalItems = findViewById(R.id.totalItems);
        totalWeight = findViewById(R.id.totalWeight);

        majorCount.setText("0");
        smallCount.setText("0");
        ictCount.setText("0");
        lightCount.setText("0");
        sportsCount.setText("0");
        medicalCount.setText("0");
        medicalWeight.setText("0.0");
        majorWeight.setText("0.0");
        smallWeight.setText("0.0");
        ictWeight.setText("0.0");
        lightWeight.setText("0.0");
        sportsWeight.setText("0.0");


//For major

        db.collection("Orders")
                .whereEqualTo("category","Major Appliances" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result = 0;
                                } else

                                {
                                    result = result + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));

                                majorCount.setText(String.valueOf(result));

                            }
                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




        db.collection("Orders")
                .whereEqualTo("category","Major Appliances" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result2 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result2 = 0.0;
                                } else

                                {
                                    result2 = result2 + Double.parseDouble(document.getString("weight"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            majorWeight.setText(String.valueOf(result2));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        //For ICT


        db.collection("Orders")
                .whereEqualTo("category","ICT Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                             result = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result = 0;
                                } else

                                {
                                    result = result + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));


                            }
                            ictCount.setText(String.valueOf(result));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });






        db.collection("Orders")
                .whereEqualTo("category","ICT Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result1 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result1 = 0.0;
                                } else

                                {
                                    result1 = result1 + Double.parseDouble(document.getString("weight"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            ictWeight.setText(String.valueOf(result1));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });








        //for Small

        db.collection("Orders")
                .whereEqualTo("category","Small Appliances" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result = 0;
                                } else

                                {
                                    result = result + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));


                            }
                            smallCount.setText(String.valueOf(result));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        db.collection("Orders")
                .whereEqualTo("category","Small Appliances" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result3 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result3 = 0.0;
                                } else

                                {
                                    result3 = result3 + Double.parseDouble(document.getString("weight"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            smallWeight.setText(String.valueOf(result3));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        //For sports
        db.collection("Orders")
                .whereEqualTo("category","Sports Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result = 0;
                                } else

                                {
                                    result = result + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));


                            }
                            sportsCount.setText(String.valueOf(result));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        db.collection("Orders")
                .whereEqualTo("category","Sports Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result4 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result4 = 0.0;
                                } else

                                {
                                    result4 = result4 + Double.parseDouble(document.getString("weight"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            sportsWeight.setText(String.valueOf(result4));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        //For lights

        db.collection("Orders")
                .whereEqualTo("category","Light Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result = 0;
                                } else

                                {
                                    result = result + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));


                            }
                            lightCount.setText(String.valueOf(result));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        db.collection("Orders")
                .whereEqualTo("category","Light Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result5 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result5 = 0.0;
                                } else

                                {
                                    result5 = result5 + Double.parseDouble(document.getString("weight"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            lightWeight.setText(String.valueOf(result1));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
//For medical
        db.collection("Orders")
                .whereEqualTo("category","Medical Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result = 0;
                                } else

                                {
                                    result = result + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));


                            }
                            medicalCount.setText(String.valueOf(result));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        db.collection("Orders")
                .whereEqualTo("category","ICT Equipment" )
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result6 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result6 = 0.0;
                                } else

                                {
                                    result6 = result6 + Double.parseDouble(document.getString("weight"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            medicalWeight.setText(String.valueOf(result6));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


//for totals
        db.collection("Orders")
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result7 = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("quantity") == null) {
                                    result7 = 0;
                                } else

                                {
                                    result7 = result7 + Integer.parseInt(document.getString("quantity"));

                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("quantity"));


                            }
                            totalItems.setText(String.valueOf(result7));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        db.collection("Orders")
                .whereEqualTo("userID",userID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            result8 = 0.0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("weight") == null) {
                                    result8 = 0.0;
                                } else

                                {
                                    result8 = result8 + Double.parseDouble(document.getString("weight"));


                                }



                                Log.d(TAG, document.getId() + " => " + document.getString("weight"));


                            }
                            totalWeight.setText(String.valueOf(result8));

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}