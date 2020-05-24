package com.supersu.inventory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.supersu.inventory.R;
import com.supersu.inventory.models.ItemModel;

import java.util.List;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<ItemModel> mData;

    public ProductRecycleViewAdapter(Context mContext, List<ItemModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.single_daily_product,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(mData.get(position).getItemName());
        holder.productPacking.setText(mData.get(position).getPacking());
        holder.productUnit.setText(mData.get(position).getUnit());
        holder.productBatchNumber.setText(mData.get(position).getBatchNumber());
        holder.productRatePerBottle.setText(mData.get(position).getPurchaseRate());
        holder.productTotalAmount.setText(mData.get(position).getTotalAmount());
        holder.productBottleNumber.setText(mData.get(position).getBottle());
        holder.productCaseNumber.setText(mData.get(position).getCaseNumber());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView productName,productPacking,productUnit,productBatchNumber,productRatePerBottle,productTotalAmount,productBottleNumber,productCaseNumber;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvProductName);
            productPacking = itemView.findViewById(R.id.tvProductPacking);
            productUnit = itemView.findViewById(R.id.tvProductUnit);
            productBatchNumber = itemView.findViewById(R.id.tvProductBatchNumber);
            productRatePerBottle = itemView.findViewById(R.id.tvProductRatePerBottle);
            productTotalAmount = itemView.findViewById(R.id.tvProductTotalCost);
            productBottleNumber = itemView.findViewById(R.id.tvBottles);
            productCaseNumber = itemView.findViewById(R.id.tvCases);

        }
    }
}
