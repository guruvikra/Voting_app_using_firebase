package com.firstapp.voting_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class addcontestent extends AppCompatActivity {
    Button btn,imgbtn;
    EditText edit1,edit2;
    RecyclerView recyclerView;
    ImageView img;
    Uri imgSrc;
    String imageURL;
    String name="";
    String id="";
ArrayList<Structure> arr=new ArrayList<>();
private  final int Gallery_img=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontestent);
        btn=findViewById(R.id.btn);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customadaptor adaptor=new customadaptor(getApplicationContext(),arr);
        recyclerView.setAdapter(adaptor);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(addcontestent.this);
                dialog.setContentView(R.layout.addnew);
                EditText edit1=dialog.findViewById(R.id.edit1);
                EditText edit2=dialog.findViewById(R.id.edit2);
                imgbtn=dialog.findViewById(R.id.imgbtn);
                Button btn=dialog.findViewById(R.id.btn);
//                img= findViewById(R.id.img);
                Toast.makeText(addcontestent.this, "adding", Toast.LENGTH_SHORT).show();
                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if(!edit1.getText().toString().equals("")){
                            name=edit1.getText().toString();
                        }
                        else {
                            Toast.makeText(addcontestent.this, "enter name", Toast.LENGTH_SHORT).show();
                        }
                        if(!edit2.getText().toString().equals("")){
                            id=edit2.getText().toString();
                        }
                        else {
                            Toast.makeText(addcontestent.this, "enter id", Toast.LENGTH_SHORT).show();
                        }
                        arr.add(new Structure(name,id,imgSrc));
                        adaptor.notifyItemChanged(arr.size());
                        recyclerView.scrollToPosition(arr.size());
                        Toast.makeText(addcontestent.this, "added", Toast.LENGTH_SHORT).show();

                        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("vote")
                                .child(imgSrc.getLastPathSegment());

                        storageReference.putFile(imgSrc).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                while (!uriTask.isComplete());
                                Uri urlImage = uriTask.getResult();
                                imageURL = urlImage.toString();
                                uploadData();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent img=new Intent(Intent.ACTION_PICK);
                    img.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(img,Gallery_img);
                    Toast.makeText(addcontestent.this, "clicked", Toast.LENGTH_SHORT).show();
                }
            });
                dialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==Gallery_img){
                 imgSrc = data.getData();
            }
        }
    }

    public void uploadData(){

        Structure dataClass = new Structure(name,id,imageURL);
        Log.d("dataclass", dataClass.toString());
        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Android").child(currentDate)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(addcontestent.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addcontestent.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}