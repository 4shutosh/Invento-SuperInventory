package com.supersu.inventory.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.supersu.inventory.R;
import com.supersu.inventory.adapters.ProductClosingAdapter;
import com.supersu.inventory.adapters.ProductRecycleViewAdapter;
import com.supersu.inventory.models.ItemModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClosingManagement extends AppCompatActivity {
    private RecyclerView mainClosingList;

    private List<ItemModel> data_list_closing;
    public String closingShowURL;
    private JsonArrayRequest jsonArrayRequests;
    private RequestQueue requestQueues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closing_management);
        closingShowURL = getString(R.string.closingFetchurl);
        mainClosingList = findViewById(R.id.mainClosingIterate);
        data_list_closing = new ArrayList<>();
        jsonRequestMaker();
        TextInputEditText etclosingSearch;

        etclosingSearch = findViewById(R.id.etSearchClosing);
        etclosingSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ProductClosingAdapter pcA = new ProductClosingAdapter();
                pcA.filter(s.toString());
            }
        });



    }

    private void jsonRequestMaker() {
        jsonArrayRequests = new JsonArrayRequest(closingShowURL, response -> {
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
                    Log.d("TAG", String.valueOf(data_list_closing));





                    data_list_closing.add(itemModel);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupRecycleview(data_list_closing);
            }


        }, error -> Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show());
        requestQueues = Volley.newRequestQueue(ClosingManagement.this);
        requestQueues.add(jsonArrayRequests);


    }

    private void setupRecycleview(List<ItemModel> data_list_closing) {
        ProductClosingAdapter closingRecycleViewAdapter = new ProductClosingAdapter(this,data_list_closing);
        mainClosingList.setLayoutManager(new LinearLayoutManager(this));
        mainClosingList.setAdapter(closingRecycleViewAdapter);

    }

}
