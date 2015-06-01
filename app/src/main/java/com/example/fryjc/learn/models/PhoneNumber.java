package com.example.fryjc.learn.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fryjc on 5/29/2015.
 */
public class PhoneNumber {
    @SerializedName("work")
    private String mWork;
    @SerializedName("home")
    private String mHome;
    @SerializedName("mobile")
    private String mMobile;

    public String getmWork() {
        return mWork;
    }

    public String getmHome() {
        return mHome;
    }

    public String getmMobile() {
        return mMobile;
    }
}
