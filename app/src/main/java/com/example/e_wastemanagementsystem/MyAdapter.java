package com.example.e_wastemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    List<User> mList;
    tasks activity;
    FirebaseFirestore db =FirebaseFirestore.getInstance();


    public MyAdapter(tasks activity, List<User> mList) {
        this.activity = activity;
        this.mList = mList;
    }
    public  void updateData(int position) {
        User item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("uItem",item.getItem());
        bundle.putString("uCategory",item.getCategory());
        bundle.putString("uQuantity",item.getQuantity());
        bundle.putString("uWeight",item.getWeight());
        bundle.putString("uUserID",item.getUserID());
        bundle.putString("uCustomer",item.getCustomer());
        bundle.putString("uDocID",item.getDocID());

        Intent intent = new Intent(activity,collection.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    public void deleteData(int position)
    {
        User item = mList.get(position);
        db.collection("Orders").document(item.getDocID()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    notifyRemoved(position);
                    Toast.makeText(activity,"Data Deleted",Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity,"Error deleting data" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void notifyRemoved(int position)
    {
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);


        return new MyViewHolder(v);

    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {


        User user = mList.get(position);

        {

            holder.item.setText(user.item);
            holder.quantity.setText(user.quantity);
            holder.weight.setText(user.weight);
            holder.quantity.setText(user.quantity);
            holder.customer.setText(user.customer);
            holder.userID.setText(user.userID);
            holder.category.setText(user.category);



        }



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item, category, quantity, weight, userID, customer;
        ImageView dltBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            category = itemView.findViewById(R.id.category);
            quantity = itemView.findViewById(R.id.quantity);
            weight = itemView.findViewById(R.id.weight);
            userID = itemView.findViewById(R.id.userID);
            customer = itemView.findViewById(R.id.employee);
            dltBtn = itemView.findViewById(R.id.deleteDataBTN);


        }

    }


}

