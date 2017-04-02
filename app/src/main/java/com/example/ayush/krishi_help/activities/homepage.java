package com.example.ayush.krishi_help.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ayush.krishi_help.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class homepage extends AppCompatActivity {
    ImageView logo;
    EditText email,pass ;
    EditText f_name,l_name,password,re_password,contact_no,email_id;
    Button login,register ;  //creating button variables
    ProgressDialog dialog;
    String server_ip;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

//    VideoView vv;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        server_ip = getString(R.string.server_ip);
        email = (EditText) findViewById(R.id.et_login_email);
        pass = (EditText) findViewById(R.id.et_login_pass);
        f_name =(EditText) findViewById(R.id.et_f_name) ;
        l_name = (EditText) findViewById(R.id.et_l_name) ;
        email_id = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password) ;
        re_password = (EditText) findViewById(R.id.et_re_password) ;
        contact_no = (EditText) findViewById(R.id.et_contact_no) ;
        login = (Button) findViewById(R.id.homepage_b_login) ;
        register = (Button) findViewById(R.id.homepage_b_register) ;
//        vv = (VideoView) findViewById(R.id.vv);
        logo = (ImageView) findViewById(R.id.iv_logo) ;

        logo.setImageResource(R.drawable.logo);

//        String path = "android.resource://" + getPackageName() + "/" + R.raw.krishi;
//        vv.setVideoURI(Uri.parse(path));
//        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                vv.start();
//            }
//
//        });
//        vv.start();

        dialog= new ProgressDialog(homepage.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fIntent = new Intent(homepage.this, ActivityLogin.class);
                startActivity(fIntent);



            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = server_ip.concat("/register");


                Intent fIntent = new Intent(homepage.this, ActivityRegister.class);
                startActivity(fIntent);
            }
        });



        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

    }



    public void sendData(Integer flag) {
        Log.d("Check", "Sending data opened");
        RequestParams params = new RequestParams();
        if (flag == 1){
        params.put("email", email.getText().toString());
        params.put("pass", pass.getText().toString());}
        else {
            params.put("first_name", f_name.getText().toString());
            params.put("last_name", l_name.getText().toString());
            params.put("contact_no", contact_no.getText().toString());
            params.put("password", password.getText().toString());
            params.put("email",email_id.getText().toString());
        }
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
                        startActivity(new Intent(homepage.this, MainPage.class));
                        Toast.makeText(homepage.this, "Successfully logged in", Toast.LENGTH_SHORT).show();

                    } else if (login_status.equals("noemail")) {
                        dialog.dismiss();
                        Toast.makeText(homepage.this, "Wrong E-mail entered", Toast.LENGTH_SHORT).show();
//                        register.setVisibility(View.VISIBLE);
                        Log.d("Login", "No email");
                    } else if (login_status.equals("0")) {
                        dialog.dismiss();
                        Toast.makeText(homepage.this, "Wrong password entered", Toast.LENGTH_SHORT).show();
                        Log.d("Login", "Fail");
                    }
                    else if (response.getString("status").equals("dup_user")){
                        dialog.dismiss();
                        f_name.setVisibility(View.GONE);
                        l_name.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        re_password.setVisibility(View.GONE);
                        contact_no.setVisibility(View.GONE);
                        email_id.setVisibility(View.GONE);

                        email.setVisibility(View.VISIBLE);
                        pass.setVisibility(View.VISIBLE);
                        Toast.makeText(homepage.this, "Hi "+f_name.getText().toString()+". You are already registered. Please log in to continue.", Toast.LENGTH_LONG).show();
//                        Intent login_page = new Intent("com.example.ayush.krishi_help.activities.homepage") ;
//                        startActivity(login_page);
                    }
                    else if (response.getString("status").equals("ok")) {
                        dialog.dismiss();
                        f_name.setVisibility(View.GONE);
                        l_name.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        re_password.setVisibility(View.GONE);
                        contact_no.setVisibility(View.GONE);
                        email_id.setVisibility(View.GONE);

                        email.setVisibility(View.VISIBLE);
                        pass.setVisibility(View.VISIBLE);
                        Toast.makeText(homepage.this, "Hi "+f_name.getText().toString()+". You have been successfully registered", Toast.LENGTH_SHORT).show();

//                      Intent login_page = new Intent("com.example.ayush.krishi_help.activities.homepage") ;
//                        startActivity(login_page);
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
                Toast.makeText(homepage.this, "Please try again later", Toast.LENGTH_SHORT).show();
            }
        };


        client.post(url, params, responseHandler);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        vv.start();
//    }
    @Override
    public void onBackPressed() {
        if ((f_name.getVisibility() == View.VISIBLE) || (email.getVisibility() == View.VISIBLE)) {
            f_name.setVisibility(View.GONE);
            l_name.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            re_password.setVisibility(View.GONE);
            contact_no.setVisibility(View.GONE);
            email_id.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            pass.setVisibility(View.GONE);
        }
        else{
            super.onBackPressed();
        }
    }



    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        if (requestCode == 1)
        {
            for(int i =0; i < grantResults.length; i++)
            {
                if (grantResults[i]!= PackageManager.PERMISSION_GRANTED)
                {
                    if (permissions[i].equals("Manifest.permission.INTERNET")) {
                    Toast.makeText(homepage.this,"App needs Internet permission to work.",Toast.LENGTH_SHORT).show();}
                    else if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        Toast.makeText(homepage.this,"App needs Storage permission to work.",Toast.LENGTH_SHORT).show();}
                    else if (permissions[i].equals(Manifest.permission.CAMERA)) {
                        Toast.makeText(homepage.this,"App needs Camera permission to work.",Toast.LENGTH_SHORT).show();}
                    finish();
                }

                }
            }
        }
    }

