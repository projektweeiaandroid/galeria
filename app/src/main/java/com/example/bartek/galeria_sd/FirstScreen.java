package com.example.bartek.galeria_sd;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;


import com.example.bartek.galeria_sd.R;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {
    final Context context = this;
    Button b1;
    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        b1 = (Button) findViewById(R.id.button);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);

        b1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Intent start = new Intent(FirstScreen.this, MainActivity.class);
        startActivity(start);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // zareaguj na podstawie ID itemu
        switch (item.getItemId()) {

            case R.id.ustawienia_start:
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                break;

            case R.id.exit_start:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;

            default:
                break;
        }

        return true;
    }
}