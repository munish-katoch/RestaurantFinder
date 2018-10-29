package com.katoch.restaurantfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.data.Business;
import com.katoch.restaurantfinder.data.BusinessCategory;
import com.katoch.restaurantfinder.view.DetailViewActivity;
import com.katoch.restaurantfinder.view.GlideApp;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CustomRecyclerAdapter";
    private static final int TYPE_CATEGORY = 1;
    private static final int TYPE_ITEM = 2;

    private ArrayList<BusinessCategory> mDataSet;
    private Context mContext;


    public CustomRecyclerAdapter(Context context) {
        mContext = context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView; //icon
        private final TextView nameTextView; //restaurant_name
        private final TextView categoryTextView; //restaurant_category
        private final TextView priceTextView; //restaurant_price

        public ItemViewHolder(View v) {
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

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryTextView;
        public CategoryViewHolder(View v) {
            super(v);
            categoryTextView = (TextView) v.findViewById(R.id.main_category);
        }
        public TextView getCategoryTextView() {
            return categoryTextView;
        }
    }

    private void startDetailViewActivity(Business object) {
        Intent intent = new Intent(mContext, DetailViewActivity.class);
        intent.putExtra(Utils.EXTRA_BUSINESS_DETAIL, object);
        mContext.startActivity(intent);
    }

    public void setData(ArrayList<BusinessCategory> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = null;
        if (viewType == TYPE_CATEGORY) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_category, viewGroup, false);
            return new CategoryViewHolder(v);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_item, viewGroup, false);
            return new ItemViewHolder(v);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + position + " click");
                    startDetailViewActivity(mDataSet.get(position).getBusiness());
                }
            });
            Log.d(TAG, "Element " + position + "URL: " + mDataSet.get(position).getBusiness().getImageUrl());
            viewHolder.getNameTextView().setText(mDataSet.get(position).getBusiness().getName());
            viewHolder.getCategoryTextView().setText(mDataSet.get(position).getCategory());
            viewHolder.getPriceTextView().setText(mDataSet.get(position).getBusiness().getPrice());
            String url = mDataSet.get(position).getBusiness().getImageUrl();
            if (url != null) GlideApp.with(mContext).load(url).into(viewHolder.getIconView());
        } else {
            CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
            viewHolder.getCategoryTextView().setText(mDataSet.get(position).getCategory() + " (" + mDataSet.get(position).getNoOfBusinessInCategory() + ")");
        }
    }

    @Override
    public int getItemCount() {
        return (mDataSet != null) ? mDataSet.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSet.get(position).getBusiness() == null) {
            return TYPE_CATEGORY;
        } else {
            return TYPE_ITEM;
        }
    }
}
