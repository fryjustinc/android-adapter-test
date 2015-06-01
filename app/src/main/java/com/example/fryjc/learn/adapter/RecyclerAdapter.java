package com.example.fryjc.learn.adapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.fryjc.learn.LruBitmapCache;
import com.example.fryjc.learn.R;
import com.example.fryjc.learn.models.ContactCard;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by fryjc on 5/26/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ContactCardViewHolder>{

    private ArrayList<ContactCard> mList;
    private RequestQueue mRequestQueue;

    public RecyclerAdapter(ArrayList<ContactCard> list) {
        mList = list;
    }

    @Override
    public ContactCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(
                ((viewType == 0) ? R.layout.contactcardview
                        : R.layout.secondarycontactcardview), parent, false);

        ContactCardViewHolder holder = new ContactCardViewHolder(view);



        return holder;
    }



    @Override
    public void onBindViewHolder(ContactCardViewHolder holder, int position) {
        mRequestQueue = Volley.newRequestQueue(holder.holderImage.getContext());
        ImageLoader imageLoad = new ImageLoader(mRequestQueue,new LruBitmapCache(4000));
        holder.holderImage.setImageUrl(mList.get(position).getmSmallImageURL(),imageLoad);
        holder.userName.setText(mList.get(position).getmName());
        String phone = mList.get(position).getmPhone().getmHome();
        holder.phone.setText(phone == null ? "" : phone);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getmName().contains("t")
                ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
