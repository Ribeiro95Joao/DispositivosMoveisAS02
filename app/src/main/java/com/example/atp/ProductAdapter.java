package com.example.atp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.channels.DatagramChannel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView genericProduct;
        TextView genericPrice;
        public ViewHolder(View itemView){
            super(itemView);
            genericProduct = itemView.findViewById(R.id.genericProduct);
            genericPrice = itemView.findViewById(R.id.genericPrice);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = DataModel.getInstance().products.get(position);
        holder.genericProduct.setText(p.getName());
        holder.genericPrice.setText(String.valueOf(p.getValue()));
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().products.size();
    }
}
