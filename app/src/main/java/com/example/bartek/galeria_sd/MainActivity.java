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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


       GridView gv;
    ArrayList<File> list;
    Button b2;
    final Context context = this;
    int i = 1;
    int k = 1;
    private static final int RESULT_PERMS_INITIAL=1339;
    private static final int RESULT_PERMS_TAKE_PICTURE=1340;
    private static final String PREF_IS_FIRST_RUN="firstRun";
    private SharedPreferences prefs;
    private static final String[] PERMS_INITIAL={
            READ_EXTERNAL_STORAGE,
    };
    private static final String[] PERMS_TAKE_PICTURE={
            WRITE_EXTERNAL_STORAGE,
            CAMERA
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mTitle = mDrawerTitle = getTitle();
        mItemTitles = getResources().getStringArray(R.array.drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mItemTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
// enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);


            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,                  *//* host Activity *//*
                mDrawerLayout,
                my_toolbar, *//* DrawerLayout object *//*
                R.string.drawer_open,  *//* "open drawer" description for accessibility *//*
                R.string.drawer_close  *//* "close drawer" description for accessibility *//*
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

        if (savedInstanceState == null) {
            selectItem(0);
        }*/

        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        b2 = (Button) findViewById(R.id.button2);
        list = new ArrayList<File>();
        list = imageReader(Environment.getExternalStorageDirectory());


        gv = (GridView) findViewById(R.id.gridView);

        final GridAdapter adapter = new GridAdapter(this);
        gv.setAdapter(adapter);
     if (isFirstRun() && useRuntimePermissions()) {
            requestPermissions(PERMS_INITIAL, RESULT_PERMS_INITIAL);
        }

        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                final File myDir = new File(list.get(position).toString());
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                TextView text = (TextView) dialog.findViewById(R.id.textView3);
                text.setText("Usuń");

                Button dialogButtonNo = (Button) dialog.findViewById(R.id.dialogButtonNo);
                dialogButtonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button dialogButtonYes = (Button) dialog.findViewById(R.id.dialogButtonYes);
                dialogButtonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(myDir.exists())
                        {
                            myDir.delete();
                            Toast msg2 = Toast.makeText(getBaseContext(), "plik został usunięty", Toast.LENGTH_LONG);
                            msg2.show();
                            adapter.notifyDataSetChanged();
                            gv.setAdapter(adapter);

                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), ViewImage.class).putExtra("img", list.get(position).toString()));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //finish();
                //startActivity(getIntent());
                //adapter.notifyDataSetChanged();

                takePicture();
                adapter.notifyDataSetChanged();
                gv.setAdapter(adapter);
            }
        });

    }



    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode==RESULT_PERMS_TAKE_PICTURE) {
            if (canTakePicture()) {
                takePicture();
            }
        }
    }

    private boolean hasPermission(String perm) {
        if (useRuntimePermissions()) {
            return(checkSelfPermission(perm)== PackageManager.PERMISSION_GRANTED);
        }
        return(true);
    }

    private boolean canTakePicture() {
        return(hasPermission(CAMERA) &&
                hasPermission(WRITE_EXTERNAL_STORAGE));
    }

    private boolean useRuntimePermissions() {
        return(Build.VERSION.SDK_INT> Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private boolean isFirstRun() {
        boolean result=prefs.getBoolean(PREF_IS_FIRST_RUN, true);
        if (result) {
            prefs.edit().putBoolean(PREF_IS_FIRST_RUN, false).apply();
        }
        return(result);
    }
    private String[] netPermissions(String[] wanted) {
        ArrayList<String> result=new ArrayList<>();
        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return(result.toArray(new String[result.size()]));
    }
    public void takePicture() {
        if (canTakePicture()) {
            captureImage();
        }
        else if (shouldShowTakePictureRationale()) {
        }
        else {
            requestPermissions(netPermissions(PERMS_TAKE_PICTURE),
                    RESULT_PERMS_TAKE_PICTURE);;
           captureImage();
        }
    }

    private boolean shouldShowRationale(String perm) {
        if (useRuntimePermissions()) {
            return(!hasPermission(perm) &&
                    shouldShowRequestPermissionRationale(perm));
        }
        return(false);
    }

    private boolean shouldShowTakePictureRationale() {
        return(shouldShowRationale(CAMERA) ||
                shouldShowRationale(WRITE_EXTERNAL_STORAGE));
    }

    private void captureImage() {

        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
        imagesFolder.mkdirs();
        File image = new File(imagesFolder, "image_" + i + ".jpg");
        while (image.exists()){
            i = i + 1;
            image = new File(imagesFolder, "image_" + i + ".jpg");
        }
        Uri uriSavedImage = Uri.fromFile(image);//idendyfikator zasobów
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(imageIntent, 0);

    }


    public class GridAdapter extends BaseAdapter{
        private final Context mContext;


        public GridAdapter(Context c) {
            this.mContext = c;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        private class ViewHolder { //przechowuje obiekty typu View dla zarzadzania danymi
            ImageView img;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder view_holder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gridview_item, parent, false);
                view_holder = new ViewHolder();
                view_holder.img = (ImageView) convertView.findViewById(R.id.picture);

                convertView.setTag(view_holder);
            } else {
                view_holder = (ViewHolder) convertView.getTag();
            }

            view_holder.img.setImageURI(Uri.parse(getItem(position).toString()));

            return convertView;

        }
    }

    ArrayList<File> imageReader(File root){
        ArrayList<File> a = new ArrayList<>();

        File[] files = root.listFiles();
        if (files == null) {
            System.out.println("array is null");
        } else
        for(int i=0; i<files.length; i++){
            if(files[i].isDirectory()){
                a.addAll( imageReader(files[i]));
                Collections.reverse(a);
            }
            else {
                if (files[i].getName().endsWith(".jpg")){
                    a.add(files[i]);
                    Collections.reverse(a);
                }
            }
        }
        return a;
    }

}
