package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class otpverfication extends AppCompatActivity {
RecyclerView recycle;
customeadaptortwo adaptor;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
List<structuretwo> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverfication);
        recycle=findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        arr=new ArrayList<>();
        adaptor  =new customeadaptortwo( otpverfication.this, arr);
        recycle.setAdapter(adaptor);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arr.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    structuretwo dataClass = itemSnapshot.getValue(structuretwo.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    arr.add(dataClass);
                }
                adaptor.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        adaptor.notifyDataSetChanged();
    }
}