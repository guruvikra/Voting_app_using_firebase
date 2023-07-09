package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class user extends AppCompatActivity {
EditText username,number;
Button btnotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        number=findViewById(R.id.number);
        btnotp=findViewById(R.id.btnotp);

        btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!number.getText().toString().trim().isEmpty()){
                    if(number.getText().toString().length()==10){
                        Toast.makeText(user.this, "sending", Toast.LENGTH_SHORT).show();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91"+number.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                user.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        Toast.makeText(user.this, "sending", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(user.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);
                                        Intent intent=new Intent(getApplicationContext(),votingcandidate.class);
                                        intent.putExtra("mobile",number.getText().toString());
                                        intent.putExtra("otp",s);
                                        startActivity(intent);

                                    }
                                }
                        );
                    }
                    else{
                        Toast.makeText(user.this, "mobile number is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(user.this, "enter the mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}