package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class votingcandidate extends AppCompatActivity {
EditText num1,num2,num3,num4,num5,num6;
String getotp;
Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votingcandidate);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        num3=findViewById(R.id.num3);
        num4=findViewById(R.id.num4);
        num5=findViewById(R.id.num5);
        num6=findViewById(R.id.num6);
        verify=findViewById(R.id.verify);

        TextView textView=findViewById(R.id.num);
        textView.setText(String.format(getIntent().getStringExtra("mobile")));
        getotp=getIntent().getStringExtra("otp");
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!num1.getText().toString().trim().isEmpty() && !num2.getText().toString().trim().isEmpty() &&!num3.getText().toString().trim().isEmpty() && !num4.getText().toString().trim().isEmpty() && !num5.getText().toString().trim().isEmpty() && !num6.getText().toString().trim().isEmpty()){
                    String entercode=num1.getText().toString()+num2.getText().toString()+num3.getText().toString()+num4.getText().toString()+num5.getText().toString()+num6.getText().toString();
                   if(getotp!=null){
                       PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                               getotp,entercode
                       );
                       FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if(task.isSuccessful()){
                                           Intent intent=new Intent(votingcandidate.this,otpverfication.class);
                                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(intent);
                                       }
                                       else{
                                           Toast.makeText(votingcandidate.this, "enter the correct  otp", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                   }
                   else{
                       Toast.makeText(votingcandidate.this, "check internet connection", Toast.LENGTH_SHORT).show();

                   }
                    Toast.makeText(votingcandidate.this, "otp verify", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(votingcandidate.this, "enter otp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}