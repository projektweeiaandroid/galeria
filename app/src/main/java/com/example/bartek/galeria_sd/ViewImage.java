package com.example.bartek.galeria_sd;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.bartek.galeria_sd.R;

public class ViewImage extends AppCompatActivity {

    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        String f = getIntent().getStringExtra("img");
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv2.setImageURI(Uri.parse(f));


    }
}
