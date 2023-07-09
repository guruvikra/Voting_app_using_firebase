package com.firstapp.voting_app_using_firebase;

public class Singleton {
    static String uid = "-1" ;

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        Singleton.uid = uid;
    }
}
