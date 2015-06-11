package com.example.fryjc.learn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fryjc.learn.models.ContactCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ListFragment extends android.support.v4.app.Fragment {

    ListView listContacts;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
        View v = inf.inflate(R.layout.list_fragment, parent, false);
        listContacts = (ListView) v.findViewById(R.id.contactlist);
        String serializedValue = getArguments().getString(FragmentNavigationDrawer.contactBundleKey);
        Gson gson = new Gson();
        ArrayList<ContactCard> mContactList = gson.fromJson(serializedValue,new TypeToken<ArrayList<ContactCard>>(){}.getType());
        com.example.fryjc.learn.adapter.ListAdapter adapter = new com.example.fryjc.learn.adapter.ListAdapter(getActivity(), mContactList,
                (com.example.fryjc.learn.adapter.ListAdapter.iCall) getActivity());
        listContacts.setAdapter(adapter);
        return v;
    }



}