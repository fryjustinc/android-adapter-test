package com.example.fryjc.learn.viewmodel;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fryjc.learn.DrawerMain;
import com.example.fryjc.learn.models.ContactCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by fryjc on 5/29/2015.
 */
public class FragmentDrawerViewModel {
    private Context mContext;
    //I think a StringRequest is more convenient in your case cause you can
    //make a new JSONArray straight from the String

    public FragmentDrawerViewModel(Context myContext){
        mContext = myContext;
    }


    RequestQueue queue = Volley.newRequestQueue(mContext);
    //This will send the request


    public void getContacts(final IReturnListener listener) {
        StringRequest request = new StringRequest("https://solstice.applauncher.com/external/contacts.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Here is where you process your data, you could call a method to parse your data
                Gson gson = new Gson();
                List<ContactCard> list = gson.fromJson(response,new TypeToken<List<ContactCard>>(){}.getType());

                listener.onReturn(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Here is where you handle the errors
            }
        });

        queue.add(request);
    }

    public static interface IReturnListener{
        public void onReturn(List<ContactCard> contacts);
    }
}
