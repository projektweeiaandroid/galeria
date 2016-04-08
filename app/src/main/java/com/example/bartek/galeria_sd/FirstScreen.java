package com.example.bartek.galeria_sd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bartek.galeria_sd.R;


public class FirstScreen extends AppCompatActivity implements View.OnClickListener {

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
}