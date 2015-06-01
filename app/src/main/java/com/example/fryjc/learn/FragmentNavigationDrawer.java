package com.example.fryjc.learn;

/**
 * Created by fryjc on 5/27/2015.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fryjc.learn.adapter.NavDrawerListAdapter;
import com.example.fryjc.learn.models.ContactCard;
import com.example.fryjc.learn.models.NavDrawerItem;
import com.example.fryjc.learn.viewmodel.FragmentDrawerViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FragmentNavigationDrawer extends DrawerLayout{
    private ActionBarDrawerToggle drawerToggle;
    private ListView lvDrawer;
    private Toolbar toolbar;
    private NavDrawerListAdapter drawerAdapter;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private ArrayList<FragmentNavItem> drawerNavItems;
    private int drawerContainerRes;
    private FragmentDrawerViewModel mViewModel;
    public static final String contactBundleKey = "123";

    public FragmentNavigationDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FragmentNavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentNavigationDrawer(Context context) {
        super(context);
    }

    public void setupDrawerConfiguration(ListView drawerListView, Toolbar drawerToolbar,
                                         int drawerItemRes, int drawerContainerResId) {
        drawerNavItems = new ArrayList<FragmentNavigationDrawer.FragmentNavItem>();
        navDrawerItems = new ArrayList<NavDrawerItem>();
        drawerContainerRes = drawerContainerResId;
        mViewModel = new FragmentDrawerViewModel(getActivity());
        lvDrawer = drawerListView;
        toolbar = drawerToolbar;
        lvDrawer.setOnItemClickListener(new FragmentDrawerItemListener());
        drawerToggle = setupDrawerToggle();
        setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    public void addNavItem(String navTitle, int icon, String windowTitle, Class<? extends Fragment> fragmentClass) {
        navDrawerItems.add(new NavDrawerItem(navTitle, icon));
        drawerAdapter = new NavDrawerListAdapter(getActivity(), navDrawerItems);
        lvDrawer.setAdapter(drawerAdapter);
        drawerNavItems.add(new FragmentNavItem(windowTitle, fragmentClass));
    }

    /**
     * Swaps fragments in the main content view
     */
    public void selectDrawerItem(final int position) {
        final FragmentNavItem navItem = drawerNavItems.get(position);
        Fragment fragment = null;
        try {
            fragment = navItem.getFragmentClass().newInstance();

            final Fragment finalFragment = fragment;
            mViewModel.getContacts(new FragmentDrawerViewModel.IReturnListener() {
                @Override
                public void onReturn(List<ContactCard> contacts) {
                    Bundle args = navItem.getFragmentArgs();
                    if (args != null) {
                        finalFragment.setArguments(args);
                    }

                    if (args == null) {
                        args = new Bundle();
                    }
                    Gson gson = new Gson();
                    String serialized = gson.toJson(contacts);
                    args.putString(contactBundleKey, serialized);
                    finalFragment.setArguments(args);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(drawerContainerRes, finalFragment).commit();


                    lvDrawer.setItemChecked(position, true);
                    setTitle(navItem.getTitle());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        closeDrawer(lvDrawer);
    }


    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }


    private FragmentActivity getActivity() {
        return (FragmentActivity) getContext();
    }

    private ActionBar getSupportActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    private void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    private class FragmentDrawerItemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectDrawerItem(position);
        }
    }

    private class FragmentNavItem {
        private Class<? extends Fragment> fragmentClass;
        private String title;
        private Bundle fragmentArgs;

        public FragmentNavItem(String title, Class<? extends Fragment> fragmentClass) {
            this(title, fragmentClass, null);
        }

        public FragmentNavItem(String title, Class<? extends Fragment> fragmentClass, Bundle args) {
            this.fragmentClass = fragmentClass;
            this.fragmentArgs = args;
            this.title = title;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public String getTitle() {
            return title;
        }

        public Bundle getFragmentArgs() {
            return fragmentArgs;
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(getActivity(), this, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public boolean isDrawerOpen() {
        return isDrawerOpen(lvDrawer);
    }

}
