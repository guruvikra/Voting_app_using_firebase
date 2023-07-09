package com.firstapp.voting_app_using_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button admin,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin=findViewById(R.id.admin);
        user=findViewById(R.id.user);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), admin.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,user.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}