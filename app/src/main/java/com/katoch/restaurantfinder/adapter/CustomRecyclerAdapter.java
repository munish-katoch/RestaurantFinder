package com.katoch.restaurantfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.model.Business;
import com.katoch.restaurantfinder.view.DetailViewActivity;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private static final String TAG = "CustomRecyclerAdapter";

    private ArrayList<Business> mDataSet;
    private Context mContext;

    public CustomRecyclerAdapter(Context context) {
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView; //icon
        private final TextView nameTextView; //restaurant_name
        private final TextView categoryTextView; //restaurant_category
        private final TextView priceTextView; //restaurant_price

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.icon);
            nameTextView = (TextView) v.findViewById(R.id.restaurant_name);
            categoryTextView = (TextView) v.findViewById(R.id.restaurant_category);
            priceTextView = (TextView) v.findViewById(R.id.restaurant_price);
        }

        public ImageView getIconView() {
            return imageView;
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getCategoryTextView() {
            return categoryTextView;
        }

        public TextView getPriceTextView() {
            return priceTextView;
        }
    }

    private void startDetailViewActivity(Business object) {
        Intent intent = new Intent(mContext, DetailViewActivity.class);
        intent.putExtra(Utils.EXTRA_BUSINESS_DETAIL, object);
        mContext.startActivity(intent);
    }
    public void setData( ArrayList<Business> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Element " + position + " click");
                startDetailViewActivity(mDataSet.get(position));
            }
        });

        viewHolder.getNameTextView().setText(mDataSet.get(position).getName());
        viewHolder.getCategoryTextView().setText(mDataSet.get(position).getCategories().get(0).getAlias());
        viewHolder.getPriceTextView().setText(mDataSet.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return (mDataSet !=null)?mDataSet.size():0;
    }


}
