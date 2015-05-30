package com.example.fryjc.learn;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
       final Intent listIntent = new Intent(getActivity(), listActivity.class);
        final Intent scrollIntent = new Intent(getActivity(), scrollActivity.class);
        final Intent recyclerIntent = new Intent(getActivity(), recyclerActivity.class);
        final Button listButton = (Button) rootView.findViewById(R.id.listButton);
        final Button scrollButton = (Button) rootView.findViewById(R.id.scrollButton);
        final Button recyclerButton = (Button) rootView.findViewById(R.id.recyclerButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(listIntent);
            }
        });
        scrollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(scrollIntent);
            }
        });
        recyclerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(recyclerIntent);
            }
        });
    return rootView;
    }
}
