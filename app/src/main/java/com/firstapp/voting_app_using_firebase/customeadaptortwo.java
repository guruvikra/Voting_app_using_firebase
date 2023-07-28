package com.firstapp.voting_app_using_firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class customeadaptortwo extends RecyclerView.Adapter<customeadaptortwo.ViewHolder> {
    Context context;
    List<structuretwo> arr;

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    customeadaptortwo(Context context, List<structuretwo> arr){
        this.arr=arr;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycletwo,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (Singleton.getUid().equals("voted")){
            holder.vote.setEnabled(false);
        }

        UserData userData = new UserData(arr.get(position).getName(),arr.get(position).getId(),arr.get(position).getKey());
         holder.name.setText(arr.get(position).getName());
         holder.id.setText(arr.get(position).getId());
        Glide.with(context).load(arr.get(position).getImgStore()).into(holder.img);
        holder.user = userData;
    }

    @Override
    public int getItemCount() {

        return  arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,id;
        Button vote;
        UserData user;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            id=itemView.findViewById(R.id.id);
            vote = itemView.findViewById(R.id.vote);

            vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = user.getName();
                    String id = user.getId();
                    String uid = user.getUid();
                    UserData userData = new UserData(name,id,uid);
                    String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    FirebaseDatabase.getInstance().getReference("votinglist").child(currentDate)
                            .setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(context, "voted", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(context,MainActivity.class);
                                        context.startActivity(intent);
                                        Singleton.setUid("voted");
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }
}
