package com.example.ayush.krishi_help.utilities;

/**
 * Created by ayush on 13/01/17.
 */
//import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ayush.krishi_help.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Tab3Seeds extends Fragment {
//    ProgressDialog dialog;
    String server_ip, url;
    ListView lv;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prices_tab3_seeds, container, false);
        server_ip = getString(R.string.server_ip);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.activity_price_swipe_refresh_layout);
        url = server_ip.concat("/prices");
        lv = (ListView) rootView.findViewById(R.id.lvPrice);
//        dialog = new ProgressDialog(getActivity());
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage(getString(R.string.priceSeed));
//        dialog.show();
//        dialog= new ProgressDialog(getActivity());
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage(getString(R.string.priceSeed));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
        sendData("seed");
        return rootView;
    }

    private void refreshContent() {
        sendData("seed");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void setList(JSONArray arr) {
        ArrayList<JSONObject> list = new ArrayList<>();



        for(int i =0 ; i < arr.length(); i++)
            try {
                list.add(arr.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        lv.setAdapter(new priceAdapter(getActivity(), list));
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

                    JSONArray arr ;
                    try {
                        arr = response.getJSONArray("data");
                        setList(arr);
//                        dialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
//                dialog.dismiss();
                Toast.makeText(getActivity(), "Unable to retrieve data. Please try again", Toast.LENGTH_SHORT).show();
            }
        };


        client.post(url, params, responseHandler);
    }
}
