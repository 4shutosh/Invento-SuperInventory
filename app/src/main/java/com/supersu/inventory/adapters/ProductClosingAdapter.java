package com.supersu.inventory.adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.supersu.inventory.R;
import com.supersu.inventory.models.ItemModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductClosingAdapter extends  RecyclerView.Adapter<ProductClosingAdapter.MyClosingViewHolder>{
    private Context mContext;
    private List<ItemModel> mData;


    public ProductClosingAdapter(Context mContext, List<ItemModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyClosingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.single_closing_product,parent,false);


        return new MyClosingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClosingViewHolder holder, int position) {

        holder.tvClosingProductName.setText(mData.get(position).getItemName());
        holder.tvClosingProductPacking.setText(mData.get(position).getPacking());
        holder.tvClosingProductUnit.setText(mData.get(position).getUnit());
        holder.tvClosingProductBatchNumber.setText(mData.get(position).getBatchNumber());
        holder.tvClosingProductRatePerBottle.setText(mData.get(position).getPurchaseRate());
        holder.tvClosingProductTotalCost.setText(mData.get(position).getTotalAmount());
        holder.tvBottlesPurchased.setText(mData.get(position).getBottle());
        holder.tvCasesOpening.setText(mData.get(position).getCaseNumber());
        holder.btnAddToExciseClosing.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                /*Toast.makeText(
                        mContext,
                        "Hello Data Sent",
                        Toast.LENGTH_SHORT
                ).show();*/


                if(holder.etClosingStock.getText().toString().trim().equals("")){

                holder.etClosingStock.setError("Please Enter Closing Stock first!");


                }else{

                    int mbottlesPurchased = Integer.parseInt(holder.tvBottlesPurchased.getText().toString().trim());
                    int mClosedbottles  = Integer.parseInt(holder.etClosingStock.getText().toString().trim());
                    int totalSale = mbottlesPurchased - mClosedbottles;

                holder.tvBottlesSale.setText(""+totalSale);

                //pushing data main
                    String insertClosingdata = mContext.getString(R.string.cloginSendurl);
                    RequestQueue requestQueue ;
                    requestQueue = Volley.newRequestQueue(mContext);
                    ProgressDialog dialog = ProgressDialog.show(mContext, "Please Wait", "Storing Data...",
                            true);
                    dialog.show();
                    Log.d("TAG", "clicked");
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        StringRequest requestmaker = new StringRequest(Request.Method.POST,insertClosingdata, response -> {

                        },error -> {

                        }) {
                            //mapput
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> map = new HashMap<>();
                                map.put("product_name", holder.tvClosingProductName.getText().toString());
                                map.put("packing", holder.tvClosingProductPacking.getText().toString());
                                map.put("unit", holder.tvClosingProductUnit.getText().toString());
                                map.put("batchNumber", holder.tvClosingProductBatchNumber.getText().toString());
                                map.put("ratePerBottle", holder.tvClosingProductRatePerBottle.getText().toString());
                                map.put("totalAmount", holder.tvClosingProductTotalCost.getText().toString());
                                map.put("openingBottleStock",  holder.tvBottlesPurchased.getText().toString());
                                map.put("closingBottleStock", holder.tvBottlesSale.getText().toString());
                                map.put("totalSale", holder.etClosingStock.getText().toString());

                                return map;

                            }

                        };
                        requestQueue.add(requestmaker);
                        String updateUrl = mContext.getString(R.string.updateCloser);
                        StringRequest updateMaker = new StringRequest(Request.Method.POST,updateUrl,response ->
                                Toast.makeText(mContext,"Product For Tomorrow Added",Toast.LENGTH_SHORT).show(), error -> {}){
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> updateMap = new HashMap<>();
                                updateMap.put("productName",holder.tvClosingProductName.getText().toString());
                                updateMap.put("totalSale", holder.etClosingStock.getText().toString());
                                return updateMap;
                            }
                        };
                        requestQueue.add(updateMaker);

                        dialog.dismiss();
                        holder.btnAddToExciseClosing.setEnabled(false);


                    },2000);







                }


            }
        });



    }

    private void pushGreaterData() {
        /*$id  = $_POST['id'];
    $product_name  = $_POST['product_name'];
    $packing  = $_POST['packing'];
    $unit  = $_POST['unit'];
    $batchNumber  = $_POST['batchNumber'];
    $ratePerBottle  = $_POST['ratePerBottle'];
    $totalAmount = $_POST['totalAmount'];
    $openingBottleStock	  = $_POST['openingBottleStock'];
    $closingBottleStock  = $_POST['closingBottleStock'];
    $totalSale  = $_POST['totalSale'];*/



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyClosingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tvClosingProductName,tvClosingProductPacking,tvClosingProductUnit,tvClosingProductBatchNumber,
                tvClosingProductRatePerBottle,tvClosingProductTotalCost,tvBottlesPurchased,tvCasesOpening,tvBottlesSale;

        EditText etClosingStock;
        Button btnAddToExciseClosing;

        public MyClosingViewHolder(@NonNull View itemView) {
            super(itemView);


            tvClosingProductName = itemView.findViewById(R.id.tvClosingProductName);
            tvClosingProductPacking = itemView.findViewById(R.id.tvClosingProductPacking);
            tvClosingProductUnit = itemView.findViewById(R.id.tvClosingProductUnit);
            tvClosingProductBatchNumber = itemView.findViewById(R.id.tvClosingProductBatchNumber);
            tvClosingProductRatePerBottle = itemView.findViewById(R.id.tvClosingProductRatePerBottle);
            tvClosingProductTotalCost = itemView.findViewById(R.id.tvClosingProductTotalCost);
            tvBottlesPurchased = itemView.findViewById(R.id.tvBottlesPurchased);
            tvCasesOpening = itemView.findViewById(R.id.tvCasesOpening);
            tvBottlesSale = itemView.findViewById(R.id.tvBottlesSale);




            ///et
            etClosingStock = itemView.findViewById(R.id.etClosingStock);
            //btn
            btnAddToExciseClosing = itemView.findViewById(R.id.btnAddToExciseClosing);


        }

        @Override
        public void onClick(View view) {
            switch(itemView.getId()){

                case R.id.btnAddToExciseClosing :



                    break;

            }

        }
    }
}
