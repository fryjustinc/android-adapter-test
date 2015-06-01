package com.example.fryjc.learn;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.fryjc.learn.adapter.ContactCardViewHolder;
import com.example.fryjc.learn.adapter.ListAdapter;
import com.example.fryjc.learn.models.ContactCard;

/**
 * Created by fryjc on 5/27/2015.
 */
public class DrawerMainActivity extends ActionBarActivity implements ListAdapter.iCall{
    private FragmentNavigationDrawer dlDrawer;
    private FrameLayout mFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        mFrame = (FrameLayout) this.findViewById(R.id.popup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        dlDrawer = (FragmentNavigationDrawer) findViewById(R.id.drawer_layout);
        dlDrawer.setupDrawerConfiguration((ListView) findViewById(R.id.lvDrawer), toolbar,
                R.layout.drawer_nav_item, R.id.flContent);
        dlDrawer.addNavItem("List", R.mipmap.ic_1, "List Fragment", ListFragment.class);
        dlDrawer.addNavItem("Scroll",R.mipmap.ic_2, "Scroll Fragment", ScrollFragment.class);
        dlDrawer.addNavItem("Recycler", R.mipmap.ic_3, "Recycler Fragment", RecyclerFragment.class);
        dlDrawer.addNavItem("picture",R.mipmap.ic_launcher, "Picture Fragment", PictureFragment.class);
        if (savedInstanceState == null) {
            dlDrawer.selectDrawerItem(0);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (dlDrawer.isDrawerOpen()) {
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dlDrawer.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dlDrawer.getDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dlDrawer.getDrawerToggle().onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(ContactCard card) {
        View cardView = LayoutInflater.from(this).inflate(R.layout.contactcardview, mFrame, true);
        ContactCardViewHolder holder = new ContactCardViewHolder(cardView);
        RequestQueue requestQueue = Volley.newRequestQueue(holder.holderImage.getContext());
        ImageLoader imageLoad = new ImageLoader(requestQueue, new LruBitmapCache(4000));
        holder.holderImage.setImageUrl(card.getmSmallImageURL(), imageLoad);
        holder.userName.setText(card.getmName());
        String phone = card.getmPhone().getmHome();
        mFrame.setVisibility(View.VISIBLE);
    }
}