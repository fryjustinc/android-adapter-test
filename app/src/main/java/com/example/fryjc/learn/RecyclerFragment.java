package com.example.fryjc.learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fryjc.learn.adapter.RecyclerAdapter;
import com.example.fryjc.learn.models.ContactCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by fryjc on 5/30/2015.
 */
public class RecyclerFragment extends Fragment {
    RecyclerView mRecycleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycle, container, false);
        mRecycleView = (RecyclerView) root.findViewById(R.id.recycleView);
        String serializedValue = getArguments().getString(FragmentNavigationDrawer.contactBundleKey);
        Gson gson = new Gson();
        ArrayList<ContactCard> mContactList = gson.fromJson(serializedValue,new TypeToken<ArrayList<ContactCard>>(){}.getType());
        RecyclerAdapter adapter = new RecyclerAdapter(mContactList);
        mRecycleView.setAdapter(adapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;

    }
}
