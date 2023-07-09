package com.firstapp.voting_app_using_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customadaptor extends RecyclerView.Adapter<customadaptor.ViewHolder> {
Context context;
ArrayList<Structure> arr;
customadaptor(Context context,ArrayList<Structure> arr){
    this.context=context;
    this.arr=arr;

}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycle,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.name.setText(arr.get(position).name);
holder.id.setText(arr.get(position).id);
holder.img.setImageURI(arr.get(position).img);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,id;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.edit1);
            id=itemView.findViewById(R.id.edit2);
            img=itemView.findViewById(R.id.img);
        }
    }
}
