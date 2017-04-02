package com.example.ayush.krishi_help.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ayush.krishi_help.R;
import com.example.ayush.krishi_help.utilities.NewsAdapter;
import com.example.ayush.krishi_help.utilities.priceAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ActivityPrices extends AppCompatActivity {

    ListView lv ;
    String server_ip,url;
    private JSONArray list;
//    ProgressDialog dialog ;
    Button pesticide,fertilizer,seed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("AgriMart");

        server_ip = getString(R.string.server_ip);
        url = server_ip.concat("/prices");
        pesticide = (Button) findViewById(R.id.b_p);
        fertilizer = (Button) findViewById(R.id.b_f);
        seed = (Button) findViewById(R.id.b_s);

//        dialog= new ProgressDialog(ActivityPrices.this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Getting Price");
        lv = (ListView) findViewById(R.id.lvPrice);

        pesticide.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        pesticide.setTextColor(Color.WHITE);
//        dialog.show();
        sendData("pesticide");

        pesticide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesticide.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                pesticide.setTextColor(Color.WHITE);

                fertilizer.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                fertilizer.setTextColor(Color.BLACK);
                seed.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                seed.setTextColor(Color.BLACK);

//                dialog.show();
                sendData("pesticide");

            }
        });
        fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fertilizer.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                fertilizer.setTextColor(Color.WHITE);

                pesticide.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                pesticide.setTextColor(Color.BLACK);
                seed.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                seed.setTextColor(Color.BLACK);
//                dialog.show();
                sendData("fertilizer");
            }
        });
        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seed.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                seed.setTextColor(Color.WHITE);

                fertilizer.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                fertilizer.setTextColor(Color.BLACK);
                pesticide.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                pesticide.setTextColor(Color.BLACK);
//                dialog.show();
                sendData("seed");
            }
        });

    }

    public void setList(JSONArray arr) {
        ArrayList<JSONObject> list = new ArrayList<>();



        for(int i =0 ; i < arr.length(); i++)
            try {
                list.add(arr.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        lv.setAdapter(new priceAdapter(ActivityPrices.this, list));
    }





    public void sendData(final String req) {
        Log.d("Check", "Sending data opened");
        RequestParams params = new RequestParams();
        params.put("request",req);
//        params.setUseJsonStreamer(true);

//        params.put("first_name", f_name.getText().toString());


        AsyncHttpClient client = new AsyncHttpClient();


        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
//                dialog.dismiss();
//                Log.d("Result", response.toString());
//                try {
//                    Log.d("Response", response.getString("1"));
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
                if (req.equals("pesticide")){
                    JSONArray arr ;
                    try {
                        arr = response.getJSONArray("data");
                        setList(arr);
//                        dialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else if (req.equals("fertilizer")){
                    JSONArray arr ;
                    try {
                        arr = response.getJSONArray("data");
                        setList(arr);
//                        dialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else if (req.equals("seed")){
                    JSONArray arr ;
                    try {
                        arr = response.getJSONArray("data");
                        setList(arr);
//                        dialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
//                dialog.dismiss();
                Toast.makeText(ActivityPrices.this, "Unable to retrieve data. Please try again", Toast.LENGTH_SHORT).show();
            }
        };


        client.post(url, params, responseHandler);
    }
}
