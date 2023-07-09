package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import kotlinx.coroutines.flow.LintKt;

public class admin extends AppCompatActivity {
    EditText admin,adminpass;
    Button btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin=findViewById(R.id.admin);
        adminpass=findViewById(R.id.adminpass);
        btn=findViewById(R.id.btn);
        auth=FirebaseAuth.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adminuser=admin.getText().toString();
                String adminpassword=adminpass.getText().toString();
                if(TextUtils.isEmpty(adminuser)||TextUtils.isEmpty(adminpassword)){
                    Toast.makeText(admin.this, "Please enter admin name and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(adminuser,adminpassword);
                }
            }
        });
    }
    public void login(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
           startActivity(new Intent(admin.this,addcontestent2.class));
            }
        });
        auth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(admin.this, "Wrong user id or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}