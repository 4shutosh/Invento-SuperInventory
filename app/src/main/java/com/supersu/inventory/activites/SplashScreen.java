package com.supersu.inventory.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.supersu.inventory.MainActivity;
import com.supersu.inventory.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SplashScreen extends AppCompatActivity {
    StringRequest request ;
    TextView loading;
    Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();




        loading = findViewById(R.id.loading);
        retry = findViewById(R.id.retry);




            /*Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);*/

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < Integer.parseInt(getString(R.string.splash_delay))) {
                        sleep(100);
                        waited += 100;
                    }

                } catch (InterruptedException e) {
                    // do nothing
                } finally {

                    connectionStatus();


                }
            }
        };
        splashThread.start();



    }
    public void connectionStatus () {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


            finish();

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);


        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {



            runOnUiThread(() -> {


                Toast.makeText(getApplicationContext(),"Please Check Your Internet Connection !",Toast.LENGTH_LONG).show();
                loading.setText("Please Check Your \nInternet Connection !");
                loading.setGravity(Gravity.CENTER_HORIZONTAL);
                loading.setTextSize(20);
                retry.setVisibility(View.VISIBLE);


            });


        }
        retry.setOnClickListener((View.OnClickListener) v -> connectionStatus());
    }
}