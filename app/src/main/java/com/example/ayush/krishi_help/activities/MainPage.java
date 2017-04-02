package com.example.ayush.krishi_help.activities;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayush.krishi_help.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class MainPage extends AppCompatActivity {
    int SELECT_PHOTO = 12;
    ProgressDialog dialog_spinner;
    RequestHandle client_reponse;
    String server_ip ;
    String upload_url,crop_upload_url,disease_upload_url ;
    public ImageView img, about, news, helpline, BuynSell, agriMart, agriNews, cropcheck, diseaseCheck;
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        server_ip = getString(R.string.server_ip);
        crop_upload_url = server_ip.concat("/crop_check");
        disease_upload_url = server_ip.concat("/disease_check");

        dialog_spinner= new ProgressDialog(MainPage.this);
        dialog_spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog_spinner.setCanceledOnTouchOutside(false);


        img = (ImageView) findViewById(R.id.imgView); //Centre image
        helpline = (ImageView) findViewById(R.id.abtDev);
        agriNews = (ImageView) findViewById(R.id.random);
        agriMart = (ImageView) findViewById(R.id.agriMart);
//        about = (ImageView) findViewById(R.id.helpline);
        BuynSell = (ImageView) findViewById(R.id.buynsell);
//        news = (ImageView) findViewById(R.id.agriMart);
//        agriNews = (ImageView) findViewById(R.id.agriNews);
        cropcheck = (ImageView) findViewById(R.id.cropCheck);
        diseaseCheck = (ImageView) findViewById(R.id.diseaseCheck);
        tv= (TextView)findViewById(R.id.tv);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility(View.INVISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cropcheck.setVisibility(View.VISIBLE);
                    }
                }, 100);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        diseaseCheck.setVisibility(View.VISIBLE);
                    }
                }, 200);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        agriMart.setVisibility(View.VISIBLE);

                    }
                }, 300);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


//                        diseaseCheck.setVisibility(View.VISIBLE);

                    }
                }, 400);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        helpline.setVisibility(View.VISIBLE);
                    }
                }, 500);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        agriNews.setVisibility(View.VISIBLE);
                    }
                }, 600);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        agriNews.setVisibility(View.VISIBLE);

                    }
                }, 700);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BuynSell.setVisibility(View.VISIBLE);

                    }
                }, 800);
            }
        });
        agriMart.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                agriMart.setAlpha(0.2f);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                    @Override
                    public void run() {
                        agriMart.setAlpha(1f);
                    }
                }, 25);


                Intent fIntent = new Intent(MainPage.this, ActivityPricesTabbed.class);
                startActivity(fIntent);
            }
        });
//        about.setOnClickListener(new View.OnClickListener() {
//            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//            @Override
//            public void onClick(View v) {
//                about.setAlpha(0.2f);
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        about.setAlpha(1f);
//                    }
//                }, 25);
//
//
//                Intent fIntent = new Intent(MainPage.this, ActivityNews.class);
//                startActivity(fIntent);
//            }
//        });

//        news.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                news.setAlpha(0.2f);
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        news.setAlpha(1f);
//                    }
//                }, 25);
//
//
//                Intent fIntent = new Intent(MainPage.this, ActivityNews.class);
//                startActivity(fIntent);
//            }
//        });

        BuynSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuynSell.setAlpha(0.2f);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BuynSell.setAlpha(1f);
                    }
                }, 25);


                Intent fIntent = new Intent(MainPage.this, ActivityNews.class);
                startActivity(fIntent);
            }
        });

        cropcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cropcheck.setAlpha(0.2f);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cropcheck.setAlpha(1f);
                    }
                }, 25);

                if (haveNetworkConnection() == true) {
                    dialog_spinner.setMessage(getString(R.string.mainpage_image_process_crop));
                    upload_url = crop_upload_url;
                    imagePicker();
                } else {
                    Toast.makeText(MainPage.this, getString(R.string.mainpage_internet_offline_crop), Toast.LENGTH_SHORT).show();
                }


            }
        });


        agriNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agriNews.setAlpha(0.2f);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        agriNews.setAlpha(1f);
                    }
                }, 25);


                Intent fIntent = new Intent(MainPage.this, ActivityNews.class);
                startActivity(fIntent);
            }
        });

        diseaseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diseaseCheck.setAlpha(0.2f);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        diseaseCheck.setAlpha(1f);
                    }
                }, 25);



                if (haveNetworkConnection() == true) {
                    dialog_spinner.setMessage(getString(R.string.mainpage_image_process_disease));
                    upload_url = disease_upload_url;
                    imagePicker();
                } else {
                    Toast.makeText(MainPage.this, getString(R.string.mainpage_internet_offline_disease), Toast.LENGTH_SHORT).show();
                }
            }
        });

        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpline.setAlpha(0.2f);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        helpline.setAlpha(1f);
                    }
                }, 25);


                Intent fIntent = new Intent(MainPage.this, ActiveHelpline.class);
                startActivity(fIntent);
            }
        });
    }

    private boolean haveNetworkConnection(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return  true;
            }
            else{
                return false;
            }
        } else {
            // not connected to the internet
            return false;
        }

    }

    public void traceBack()
    {
        //  Toast.makeText(this, "Please click BACK again to exit",Toast.LENGTH_SHORT).show();
        helpline.setVisibility(View.INVISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BuynSell.setVisibility(View.INVISIBLE);
            }
        }, 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                agriNews.setVisibility(View.INVISIBLE);
            }
        }, 200);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                helpline.setVisibility(View.INVISIBLE);

            }
        }, 300);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                helpline.setVisibility(View.INVISIBLE);
            }
        }, 400);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                agriMart.setVisibility(View.INVISIBLE);
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                agriMart.setVisibility(View.INVISIBLE);
            }
        }, 600);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                diseaseCheck.setVisibility(View.INVISIBLE);

            }
        }, 700);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cropcheck.setVisibility(View.INVISIBLE);

            }
        }, 800);
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce==true) {
            moveTaskToBack(true);
            killApplication("in.co.eloquence.newfirebase");
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press Back Again To Exit",Toast.LENGTH_SHORT).show();
        traceBack();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
//
//        switch(item.getItemId())
//        {
//            case R.id.facebook :
//                String url = "https://www.facebook.com/groups/1757497641175266/";
//                Uri webpage = Uri.parse(url);
//                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//                break;
//
//            case R.id.instagram :
//                String url2 = "https://www.instagram.com/eloquence.bpit/";
//                Uri webpage2 = Uri.parse(url2);
//                Intent intent2 = new Intent(Intent.ACTION_VIEW, webpage2);
//                if (intent2.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent2);
//                }
//
//                break;
//
//            case R.id.aboutDev:
//                Intent devIntent = new Intent(this, Developers.class);
//                startActivity(devIntent);
//                break;
//        }
//
//        return true;
//    }

    public void killApplication(String killPackage){
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        android.os.Process.killProcess(android.os.Process.myPid() );
        am.killBackgroundProcesses(killPackage);
    }

    public void imagePicker()
    {
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, SELECT_PHOTO);



        new AlertDialog.Builder(MainPage.this).setTitle("Select Picture")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Camera")) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } else if (items[item].equals("Gallery")) {
                            if (Build.VERSION.SDK_INT <= 19) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                            } else if (Build.VERSION.SDK_INT > 19) {
                                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                            }
                        }else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                }).show();

    }

    private void sendFile(final File f) throws FileNotFoundException
    {
        dialog_spinner.show();
        final AsyncHttpClient client= new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("file",f);
        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status")==1) {
                        dialog_spinner.dismiss();
                        Bundle b = new Bundle() ;
                        b.putString("path" , f.getAbsolutePath());
                        b.putString("data" , response.getString("data"));
                        Intent disease = new Intent("com.example.ayush.krishi_help.ActivityDisease");
                        disease.putExtras(b);
                        startActivity(disease);
                        Log.d("MainPage", response.toString());
                    }

                    else if(response.getInt("status")==5){
                        dialog_spinner.dismiss();
                        Bundle b = new Bundle() ;
                        b.putString("path" , f.getAbsolutePath());
                        b.putString("data" , response.getString("data"));
                        Intent cropcheck = new Intent("com.example.ayush.krishi_help.Activitycropcheck");
                        cropcheck.putExtras(b);
                        startActivity(cropcheck);
                        Log.d("MainPage", response.toString());
                    }
                    else if (response.getInt("status")==0){
                        dialog_spinner.dismiss();
                        Toast.makeText(MainPage.this, "Unknown error occured. Please try again !", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.getInt("status")==2)  //image does not have leaf
                    {
                        dialog_spinner.dismiss();
                        Toast.makeText(MainPage.this, "Error! No leaf found in the given image." , Toast.LENGTH_LONG).show();

                    }
                    Log.d("JSON", response.getString("status"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                if (dialog_spinner.isShowing()) {
                    Toast.makeText(MainPage.this, "Upload failed due to connection error. Trying again!", Toast.LENGTH_SHORT).show();
                }
                else {
                    client_reponse.cancel(true);
                }
                if (retryNo == 2) {
                    dialog_spinner.dismiss();
                    Log.d("upload", "fail after three reply");
                    Toast.makeText(MainPage.this, "Upload failed. Please try again later!" , Toast.LENGTH_SHORT).show();
                    client_reponse.cancel(true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dialog_spinner.dismiss();
                Log.d("upload","fail");
                Toast.makeText(MainPage.this, "Upload failed. Please try again !" , Toast.LENGTH_SHORT).show();
            }
        };

        client_reponse =  client.post(upload_url, params, responseHandler);
    }


    private static final int PICK_IMAGE = 1;
    private static final int REQUEST_CAMERA = 2;

    final String[] items = new String[]{"Camera", "Gallery", "Cancel"};



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImageUri = data.getData();
            String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
            decodeFile(selectedImagePath);
        } else if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && null != data) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(MainPage.this.getApplicationContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));
            decodeFile(finalFile.toString());
        }
    }
    public String getRealPathFromURIForGallery(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = MainPage.this.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = MainPage.this.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }



    public void decodeFile(String path){
        File f = new File(path);
        if (f.exists())
        {
            try {
                Log.d("khvov","Sending file ");
                sendFile(f);
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(MainPage.this, "File error", Toast.LENGTH_SHORT).show();
        }
    }
}

