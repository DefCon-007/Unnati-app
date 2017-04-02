package com.example.ayush.krishi_help.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayush.krishi_help.R;
import com.example.ayush.krishi_help.utilities.DiseaseAdapter;
import com.example.ayush.krishi_help.utilities.NewsAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;

public class ActivityDisease extends AppCompatActivity {

    ListView lv ;
    private JSONArray list;
    ProgressDialog dialog ;
    ImageView iv;
    String server_ip ;
    TextView likelyDisease ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        server_ip = getString(R.string.server_ip);
        likelyDisease = (TextView) findViewById(R.id.tvLikelyDisease);
        iv = (ImageView) findViewById(R.id.ivThumb);
        dialog= new ProgressDialog(ActivityDisease.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Finding disease");
        dialog.show();
        lv = (ListView) findViewById(R.id.lvDisease);
        Bundle b = getIntent().getExtras();
        Bitmap bmp = BitmapFactory.decodeFile(b.getString("path"));
        JSONArray data = null;
        try {
            data = new JSONArray(b.getString("data"));
            Log.d("GotAray", data.toString());
            setList(data);
        } catch (JSONException e) {

            e.printStackTrace();
        }

        iv.setImageBitmap(bmp);
        dialog.dismiss();



    }

    public void setList(JSONArray arr) {
        ArrayList<JSONObject> list = new ArrayList<>();



        for(int i =0 ; i < arr.length(); i++)
            try {
                list.add(arr.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        Comparator<JSONObject> comparator = new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {

                try {
                    if(lhs.getDouble("score") > rhs.getDouble("score"))
                    {
                        return -1;
                    }
                    else if(lhs.getDouble("score") < rhs.getDouble("score"))
                    {
                        return 1;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        };


        Collections.sort(list, comparator);
        JSONObject probableDisease = list.get(0);
        try {
            Log.d("HighDisease" ,probableDisease.getString("disease") );
            if (probableDisease.getString("disease").contains("ealthy")){
                likelyDisease.setText("Your leaf is "+probableDisease.getString("score") +"% likely healthy.");
            }
            else {
            likelyDisease.setText("The most probable disease is " + probableDisease.getString("disease"));}
            lv.setAdapter(new DiseaseAdapter(ActivityDisease.this, list));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




//    String url = "http://ubuntu-server1463.cloudapp.net/news";
//    public void sendData() {
//        RequestParams params = new RequestParams();
//        params.put("news", "news");
////        params.setUseJsonStreamer(true);
//
////        params.put("first_name", f_name.getText().toString());
//
//
//        AsyncHttpClient client = new AsyncHttpClient();
//
//
//        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//
////                Log.d("news", response.toString());
//                JSONArray arr ;
//                try {
//                    arr = response.getJSONArray("data");
//                    setList(arr);
//                    dialog.dismiss();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//                dialog.dismiss();
//                Toast.makeText(ActivityDisease.this, "Please try again later", Toast.LENGTH_SHORT).show();
//            }
//        };
//
//
//        client.post(url, params, responseHandler);
//    }
}
