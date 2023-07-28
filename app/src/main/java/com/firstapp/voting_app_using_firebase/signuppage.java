package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class signuppage extends AppCompatActivity {
    EditText name,password;
    Button signup;
FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=name.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(signuppage.this, "Enter username ,pass", Toast.LENGTH_SHORT).show();
                }
                else{
                    signupp(user,pass);
                }
            }
        });
    }
    public void signupp(String username,String password){
        auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signuppage.this, "Sign up success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), loginpage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(signuppage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}