package com.supersu.inventory.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.supersu.inventory.R;
import com.supersu.inventory.adapters.ProductRecycleViewAdapter;
import com.supersu.inventory.models.ItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowAddedProductList extends AppCompatActivity {
    private RecyclerView mainProductList;

    private List<ItemModel> data_list;
    public  String showProductURL ;
    private JsonArrayRequest jsonArrayRequest;

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_added_product_list);
        showProductURL= getResources().getString(R.string.showProductURL);
        mainProductList = findViewById(R.id.mainProductList);
        data_list = new ArrayList<>();
        jsonRequestMaker();

    }

    private void jsonRequestMaker() {
                   jsonArrayRequest = new JsonArrayRequest(showProductURL, response -> {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        ItemModel itemModel = new ItemModel();
                        itemModel.setItemName(jsonObject.getString("itemName"));
                        itemModel.setUnit(jsonObject.getString("unitInt"));
                        itemModel.setPacking(jsonObject.getString("packing"));
                        itemModel.setBatchNumber(jsonObject.getString("batchNumber"));
                        itemModel.setCaseNumber(jsonObject.getString("caseNumber"));
                        itemModel.setPurchaseRate(jsonObject.getString("purchaseRate"));
                        itemModel.setTotalAmount(jsonObject.getString("toatlAmount"));
                        itemModel.setBottle(jsonObject.getString("bottle"));
                        Log.d("TAG", String.valueOf(data_list));
                        data_list.add(itemModel);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setupRecycleview(data_list);
                }


        }, error -> Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show());
        requestQueue = Volley.newRequestQueue(ShowAddedProductList.this);
        requestQueue.add(jsonArrayRequest);

    }

    private void setupRecycleview(List<ItemModel> data_list) {
        ProductRecycleViewAdapter productRecycleViewAdapter = new ProductRecycleViewAdapter(this,data_list);
        mainProductList.setLayoutManager(new LinearLayoutManager(this));
        mainProductList.setAdapter(productRecycleViewAdapter);

    }

}
