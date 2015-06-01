package com.example.fryjc.learn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fryjc.learn.R;
import com.example.fryjc.learn.models.ContactCard;

/**
 * Created by fryjc on 5/30/2015.
 */
public class ContactCardViewHolder extends RecyclerView.ViewHolder {
    public final com.android.volley.toolbox.NetworkImageView holderImage;
    public final TextView userName;
    public final TextView phone;
    public ContactCard mCard;
    public ContactCardViewHolder(View itemView) {
        super(itemView);
         holderImage = (com.android.volley.toolbox.NetworkImageView) itemView.findViewById(R.id.image);
        userName = (TextView)itemView.findViewById(R.id.username);
        phone = (TextView) itemView.findViewById(R.id.phone);
    }

}
