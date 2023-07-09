package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class ResultPage extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    HashMap<String, Integer> frequencymap;
    List<String> id;
    List<UserData> data;
    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        databaseReference = FirebaseDatabase.getInstance().getReference("votinglist");
        id = new ArrayList<>();
        data = new ArrayList<>();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    UserData dataClass = itemSnapshot.getValue(UserData.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    data.add(dataClass);
                    id.add(dataClass.getId());
                }
                frequencymap  = new HashMap<String, Integer>();
                for(String a : id) {
                    if(frequencymap.containsKey(a)) {
                        frequencymap.put(a, frequencymap.get(a)+1);
                    }
                    else{ frequencymap.put(a, 1); }
                }
                Log.d("get id",frequencymap.toString());
                textView1.setText("");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}