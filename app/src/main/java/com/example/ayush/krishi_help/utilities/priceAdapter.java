package com.example.ayush.krishi_help.utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ayush.krishi_help.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ayush on 03/11/16.
 */
public class priceAdapter extends ArrayAdapter<JSONObject> {
    ArrayList<JSONObject> list;
    Context context;


    public priceAdapter(Context c, ArrayList<JSONObject> list)
    {
        super(c, R.layout.listitem_price, R.id.tvNewsHeading, list);
        this.list = list;
        this.context = c;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.listitem_price, null);
        }


        Log.d("LIST",list.toString());

        try {
            ((TextView) convertView.findViewById(R.id.tvNewsHeading)).setText(list.get(position).getString("productname"));
            ((TextView) convertView.findViewById(R.id.tvNewsDesc)).setText(list.get(position).getString("pricet"));
            ((Button) convertView.findViewById(R.id.btn_buy_now_prices)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    try {
                        i.setData(Uri.parse(list.get(position).getString("link")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(i);
                }
            });
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    try {
//                        i.setData(Uri.parse(list.get(position).getString("link")));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    context.startActivity(i);
//                }
//            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return convertView;



    }
}
