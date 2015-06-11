package com.example.fryjc.learn.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.fryjc.learn.LruBitmapCache;
import com.example.fryjc.learn.R;
import com.example.fryjc.learn.models.ContactCard;

import java.util.ArrayList;

/**
 * Created by fryjc on 6/1/2015.
 */
public class ListAdapter extends ArrayAdapter<ContactCard> {

    private ArrayList<ContactCard> mList;
    private Activity mContext;
    private RequestQueue mRequestQueue;
    private iCall mCallBack;

    public ListAdapter(Activity context, ArrayList<ContactCard> list, iCall callBack) {
        super(context, R.layout.list_fragment, list);
        mContext = context;
        mCallBack = callBack;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactcardview, parent, false);
        ContactCardViewHolder holder = new ContactCardViewHolder(view);
        mRequestQueue = Volley.newRequestQueue(holder.holderImage.getContext());
        ImageLoader imageLoad = new ImageLoader(mRequestQueue, new LruBitmapCache(8000));
        holder.holderImage.setImageUrl(getItem(position).getmSmallImageURL(), imageLoad);
        holder.userName.setText(getItem(position).getmName());
        String phone = getItem(position).getmPhone().getmHome();
        holder.phone.setText(phone == null ? "" : phone);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onClick(getItem(position));
            }
        });
        return view;
    }
    public static interface iCall{
        public void onClick(ContactCard card);
    }
}


