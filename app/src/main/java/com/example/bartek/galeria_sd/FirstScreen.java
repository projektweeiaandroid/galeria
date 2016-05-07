package com.example.bartek.galeria_sd;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import com.example.bartek.galeria_sd.R;


import com.example.bartek.galeria_sd.R;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {
    final Context context = this;
    Button b1;
    TextView tv1;
    TextView tv2;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mItemTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        b1 = (Button) findViewById(R.id.button);
        //tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);

        b1.setOnClickListener(this);
        mTitle = mDrawerTitle = getTitle();
        mItemTitles = getResources().getStringArray(R.array.drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mItemTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
// enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(my_toolbar);


            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                my_toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        /*if (savedInstanceState == null) {
            selectItem(0);
        }
*/
    }
 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.ustawienia).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {

            case R.id.siatka:
                Intent start_jedna = new Intent(FirstScreen.this, Jedna.class);
                startActivity(start_jedna);
                break;
            case R.id.ustawienia:
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
            break;
            case R.id.exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch(position) {
            case 0:
                Intent start_jedna = new Intent(FirstScreen.this, Jedna.class);
                startActivity(start_jedna);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 1:
                Intent start_trzy = new Intent(FirstScreen.this, Trzy.class);
                startActivity(start_trzy);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 2:
                Intent start_def = new Intent(FirstScreen.this, MainActivity.class);
                startActivity(start_def);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 3:
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 4:
                android.os.Process.killProcess(android.os.Process.myPid());
            break;

            default:
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {

        Intent start = new Intent(FirstScreen.this, MainActivity.class);
        startActivity(start);


    }



}