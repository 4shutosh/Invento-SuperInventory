package com.supersu.inventory;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.supersu.inventory.activites.AddProduct;
import com.supersu.inventory.activites.BrandCategory;
import com.supersu.inventory.activites.ClosingManagement;
import com.supersu.inventory.activites.ShowAddedProductList;
import com.supersu.inventory.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.PrimitiveIterator;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    //private String sumURl= getResources().getString(R.string.homeCounterUrl);
    private JsonArrayRequest jsonArrayRequest;

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        makePermissions();
        //JsonTotalReader();

        //card1
        activityMainBinding.productAdd.setOnClickListener(view14 -> {

            Intent moveToProductAdd = new Intent(MainActivity.this, AddProduct.class);
            startActivity(moveToProductAdd);
        });

//casr3
        activityMainBinding.addBrand.setOnClickListener(view12 -> {

            Intent moveToBC = new Intent(MainActivity.this, BrandCategory.class);
            startActivity(moveToBC);


        });

//card2
        activityMainBinding.stockManager.setOnClickListener(view1 -> {
            Intent moveToBC = new Intent(MainActivity.this, ClosingManagement.class);
            startActivity(moveToBC);

        });


        activityMainBinding.reportSys.setOnClickListener(view13 -> {

        });

    }
    public void makePermissions(){

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE

                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }

    public void JsonTotalReader(){

/*

            jsonArrayRequest = new JsonArrayRequest(sumURl,response->{
               JSONObject jsonObject = null;

                try {
                    for(int i=0;i<response.length();i++){

                        assert false;
                        String totalProduct = jsonObject.getString("prod_count");

                    Log.d("T A G",totalProduct);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            },error -> {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
*/




    }
}
