package com.example.ayush.krishi_help.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayush.krishi_help.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ayush on 15/01/17.
 */
public class Activitycropcheck extends AppCompatActivity {
    ImageView img;
    TextView crop_name;
    String probableDisease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropcheck);
        img = (ImageView) findViewById(R.id.cropCheckImageView);
        crop_name = (TextView) findViewById(R.id.cropCheckcrop);
        Bundle b = getIntent().getExtras();
        Bitmap bmp = BitmapFactory.decodeFile(b.getString("path"));
        JSONObject data = null;
        try {
            data = new JSONObject(b.getString("data"));
//            probableDisease = new JSONObject(data.getString("disease"));
            Log.d("GotAray", data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
            img.setImageBitmap(bmp);
        try {
            probableDisease = data.getString("disease");


        } catch (JSONException e) {
            e.printStackTrace();
        }

//        probableDisease = new JSONObject(data.get("data")) ;



            crop_name.setText(getString(R.string.probable_crop_name)+probableDisease.toString());

    }
}
