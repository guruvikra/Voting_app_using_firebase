package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class ResultPage extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    HashMap<String, Integer> frequencymap;
    List<String> id;
    List<UserData> data;
    TextView t1,v1,t2,v2,t3,v3,t4,v4;
    LinearLayout l1,l2,l3,l4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        v1=findViewById(R.id.v1);
        v2=findViewById(R.id.v2);
        v3=findViewById(R.id.v3);
        v4=findViewById(R.id.v4);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);

        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l4=findViewById(R.id.l4);

        databaseReference = FirebaseDatabase.getInstance().getReference("votinglist");
        id = new ArrayList<>();
        data = new ArrayList<>();
        frequencymap  = new HashMap<String, Integer>();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                frequencymap.clear();
                for (DataSnapshot userSnapshot: snapshot.getChildren()){
                    UserData userData = userSnapshot.getValue(UserData.class);
                    String candidate = userData.getName();
                        if(frequencymap.containsKey(candidate)){
                            int voteCount = frequencymap.get(candidate);
                            frequencymap.put(candidate, voteCount+1);
                        }
                        else {
                            frequencymap.put(candidate,1);
                        }

                }

                displayResult();
//                for(String a : id) {
//                    if(frequencymap.containsKey(a)) {
//                        frequencymap.put(a, frequencymap.get(a)+1);
//                    }
//                    else{ frequencymap.put(a, 1); }
//                }
//                Log.d("get id",frequencymap.toString());
//                textView1.setText("");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void displayResult() {
        final int[] count = {1};
        frequencymap.forEach((key, value) -> {
            if(count[0] == 1){
                t1.setText(key+"");
                v1.setText(value+"");
            }
            if(count[0] == 2){
                t2.setText(key+"");
                v2.setText(value+"");
            }
            if(count[0] == 3){
                t3.setText(key+"");
                v3.setText(value+"");
            }
            if(count[0] == 4){
                t4.setText(key+"");
                v4.setText(value+"");
            }
            count[0] += 1;
        });
        Log.d("count", String.valueOf(count[0]));
        for(int i = count[0]; i <= 4; i++){
            Log.d("count", String.valueOf(i));
            if(i == 4){
                l4.setVisibility(View.INVISIBLE);
            }
            if(i == 3){
                l3.setVisibility(View.INVISIBLE);
            }
            if(i == 2){
                l2.setVisibility(View.INVISIBLE);
            }
            if(i == 1){
                l1.setVisibility(View.INVISIBLE);
            }
        }

    }
}