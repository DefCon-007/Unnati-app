package com.example.ayush.krishi_help.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ayush.krishi_help.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ayush on 14/01/17.
 */
public class ActivityLogin extends AppCompatActivity {
    ImageView logo;
    EditText email,pass ;
    EditText f_name,l_name,password,re_password,contact_no,email_id;
    Button login,register ;  //creating button variables
    ProgressDialog dialog;
    String server_ip,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        server_ip = getString(R.string.server_ip);
        email = (EditText) findViewById(R.id.et_login_email);
        pass = (EditText) findViewById(R.id.et_login_pass);
        login = (Button) findViewById(R.id.btn_login) ;


        dialog= new ProgressDialog(ActivityLogin.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        url = server_ip.concat("/login");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(ActivityLogin.this, getString(R.string.activity_login_noemail), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass.getText().toString())) {
                    Toast.makeText(ActivityLogin.this,getString(R.string.activity_login_nopass), Toast.LENGTH_SHORT).show();
                } else {
                    dialog.setMessage(getString(R.string.activity_login_login_dialog));
                    dialog.show();
                    sendData();
                }
            }
            });


    }
    public void sendData() {
        Log.d("Check", "Sending data opened");
        RequestParams params = new RequestParams();
        params.put("email", email.getText().toString());
        params.put("pass", pass.getText().toString());

//        params.setUseJsonStreamer(true);

//        params.put("first_name", f_name.getText().toString());


        AsyncHttpClient client = new AsyncHttpClient();


        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

//                String str = new String(bytes, "UTF-8");
                try {
                    String login_status;
                    login_status = response.getString("status");
                    if (login_status.equals("1")) {
                        dialog.dismiss();
                        Log.d("Login", "Success");
                        startActivity(new Intent(ActivityLogin.this, MainPage.class));
                        Toast.makeText(ActivityLogin.this, "Successfully logged in", Toast.LENGTH_SHORT).show();

                    } else if (login_status.equals("noemail")) {
                        dialog.dismiss();
                        Toast.makeText(ActivityLogin.this, getString(R.string.activity_login_wrongemail), Toast.LENGTH_SHORT).show();
                        Log.d("Login", "No email");
                    } else if (login_status.equals("0")) {
                        dialog.dismiss();
                        Toast.makeText(ActivityLogin.this, getString(R.string.activity_login_wrongpass), Toast.LENGTH_SHORT).show();
                        Log.d("Login", "Fail");
                    }
                    Log.d("JSON", response.getString("status"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                response.getInt("status");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dialog.dismiss();
                Toast.makeText(ActivityLogin.this, getString(R.string.activity_login_senddatafail), Toast.LENGTH_SHORT).show();
            }
        };


        client.post(url, params, responseHandler);
    }

}

