package com.example.fryjc.learn.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fryjc on 5/26/2015.
 */
public class ContactCard extends RecyclerView.ViewHolder {


    @SerializedName("contact_id")
    public String mkldsfjlsdkfj;
    public ContactCard(View itemView) {
        super(itemView);

    }
}
