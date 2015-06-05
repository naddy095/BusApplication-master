package com.example.busapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerlayout;
    private ListView listview;
    private String[] ListItem;
    private ActionBarDrawerToggle drawerListener;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        ListItem = getResources().getStringArray(R.array.fragment_drawer_items);
        listview = (ListView)findViewById(R.id.drawerList);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listview.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1,ListItem));

        listview.setOnItemClickListener(this);


        drawerListener = new ActionBarDrawerToggle(this,drawerlayout,R.string.drawer_open,R.string.drawer_close)
        {

            @Override
            public void onDrawerOpened(View drawerView) {
//                Toast.makeText(MainActivity.this, "Drawer Opened",
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
//                Toast.makeText(MainActivity.this, "Drawer Closed",
//                        Toast.LENGTH_SHORT).show();


            }
        };


        drawerlayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentManager = getFragmentManager();

        LoadSelection(0);
    }

    private void LoadSelection(int i)
    {



        switch (i)
        {
            case 0 :
               DailyPackageFragment home = new DailyPackageFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,home);
                fragmentTransaction.commit();
                break;

            case 1 :
                RidesFragment rides = new RidesFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.frameLayout,rides);
                fragmentTransaction.commit();
               break;

            case 2:
                LoginFragment login = new LoginFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,login);
                fragmentTransaction.commit();
                break;

            case 3 :

                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerListener.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        //Toast.makeText(this,ListItem[position],Toast.LENGTH_SHORT).show();

        drawerlayout.closeDrawers();
        LoadSelection(position);

        selectItem(position);
    }


    public void selectItem(int position)
    {
        listview.setItemChecked(position,true);
        setTitle(ListItem[position]);
    }

    public void setTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }
}
