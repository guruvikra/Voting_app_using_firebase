package com.firstapp.voting_app_using_firebase;

import android.net.Uri;

import java.net.URI;

public class Structure {
    String name;
    String id;
    Uri img;

    String imgStore ;
    Structure(String name,String id,Uri img){
        this.name=name;
        this.id=id;
        this.img=img;
    }
    Structure(String name,String id){
        this.name=name;
        this.id=id;

    }
    Structure(String name,String id,String imgStore){
        this.name=name;
        this.id=id;
        this.imgStore=imgStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public String getImgStore() {
        return imgStore;
    }

    public void setImgStore(String imgStore) {
        this.imgStore = imgStore;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", imgStore='" + imgStore + '\'' +
                '}';
    }
}
