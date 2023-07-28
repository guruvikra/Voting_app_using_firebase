package com.firstapp.voting_app_using_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class addcontestent2 extends AppCompatActivity {
Button add,clear,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontestent2);
        add=findViewById(R.id.add);
        result=findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ResultPage.class);
                startActivity(intent);
                Toast.makeText(addcontestent2.this, "Result pagee", Toast.LENGTH_SHORT).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(addcontestent2.this,addcontestent.class);
                startActivity(intent);
                Toast.makeText(addcontestent2.this, "Add candidate page", Toast.LENGTH_SHORT).show();
            }
        });

    }
}