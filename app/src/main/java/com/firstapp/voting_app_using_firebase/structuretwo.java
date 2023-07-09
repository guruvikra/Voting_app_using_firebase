package com.firstapp.voting_app_using_firebase;

import android.net.Uri;

public class structuretwo {
    String imgStore;
    String name,id,key;
    structuretwo( String id,String imgStore,String name) {
        this.imgStore=imgStore;
        this.id=id;
        this.name=name;
    }
    structuretwo(String name,String id){
        this.name=name;
        this.id=id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImgStore() {
        return imgStore;
    }

    public void setImgStore(String imgStore) {
        this.imgStore = imgStore;
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

    public structuretwo() {
    }
}
