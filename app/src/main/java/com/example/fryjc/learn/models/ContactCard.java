package com.example.fryjc.learn.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fryjc on 5/26/2015.
 */
public class ContactCard{

    @SerializedName("name")
private String mName;
    @SerializedName("employeeId")
    private String mEmployeeId;
    @SerializedName("company")
    private String mCompany;
    @SerializedName("detailsURL")
    private String mDetailsURL;
    @SerializedName("smallImageURL")
    private String mSmallImageURL;
    @SerializedName("birthdate")
    private String mBirthdate;
    @SerializedName("phone")
    private PhoneNumber mPhone;

    public String getmName() {
        return mName;
    }

    public String getmEmployeeId() {
        return mEmployeeId;
    }

    public String getmCompany() {
        return mCompany;
    }

    public String getmDetailsURL() {
        return mDetailsURL;
    }

    public String getmSmallImageURL() {
        return mSmallImageURL;
    }

    public String getmBirthdate() {
        return mBirthdate;
    }

    public PhoneNumber getmPhone() {
        return mPhone;
    }
}
