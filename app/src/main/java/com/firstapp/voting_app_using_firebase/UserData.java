package com.firstapp.voting_app_using_firebase;

public class UserData {
    String name,id,uid,key;

    public UserData() {
    }

    public UserData(String name, String id, String uid) {
        this.name = name;
        this.id = id;
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
