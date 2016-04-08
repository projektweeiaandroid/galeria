package com.example.bartek.galeria_sd;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bartek.galeria_sd.R;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    ArrayList<File> list;
    Button b2;
    final Context context = this;
    int i = 1;
    int k = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b2 = (Button) findViewById(R.id.button2);

        list = imageReader(Environment.getExternalStorageDirectory());

        gv = (GridView) findViewById(R.id.gridView);

        final GridAdapter adapter = new GridAdapter(this);
        gv.setAdapter(adapter);


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
                captureImage();
                adapter.notifyDataSetChanged();
                gv.setAdapter(adapter);
            }
        });

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

        private class ViewHolder { //przechowuje obiekty typu View dla zarzÄ…dzania danymi
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
        for(int i=0; i<files.length; i++){
            if(files[i].isDirectory()){
                a.addAll( imageReader(files[i]));
            }
            else {
                if (files[i].getName().endsWith(".jpg")){
                    a.add(files[i]);
                }
            }
        }
        return a;
    }

}
