package com.supersu.inventory.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.supersu.inventory.R;
import com.supersu.inventory.databinding.ActivityAddProductMiddleBinding;
import com.supersu.inventory.databinding.ActivityMainBinding;

public class AddProductMiddle extends AppCompatActivity {

    ActivityAddProductMiddleBinding addProductMiddleBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_middle);
        addProductMiddleBinding = ActivityAddProductMiddleBinding.inflate(getLayoutInflater());
        View view = addProductMiddleBinding.getRoot();
        setContentView(view);

        addProductMiddleBinding.addProductForm.setOnClickListener(view1 -> {
            Intent i = new Intent(AddProductMiddle.this,AddProduct.class);
            startActivity(i);

        });

        addProductMiddleBinding.viewProducts.setOnClickListener(view1 -> {
            Intent i = new Intent(AddProductMiddle.this,ShowAddedProductList.class);
            startActivity(i);

        });



    }
}
