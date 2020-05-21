package com.supersu.inventory;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.supersu.inventory.activites.AddProduct;
import com.supersu.inventory.activites.BrandCategory;
import com.supersu.inventory.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        makePermissions();
        activityMainBinding.productAdd.setOnClickListener(view14 -> {

            Intent moveToProductAdd = new Intent(MainActivity.this, AddProduct.class);
            startActivity(moveToProductAdd);
        });


        activityMainBinding.addBrand.setOnClickListener(view12 -> {

            Intent moveToBC = new Intent(MainActivity.this, BrandCategory.class);
            startActivity(moveToBC);


        });


        activityMainBinding.stockManager.setOnClickListener(view1 -> {

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
}
