package com.katoch.restaurantfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.model.Business;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private static final String TAG = "CustomRecyclerAdapter";

    private ArrayList<Business> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView; //icon
        private final TextView nameTextView; //restaurant_name
        private final TextView categoryTextView; //restaurant_category
        private final TextView priceTextView; //restaurant_price

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
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

    /**
     * Initialize the dataset of the Adapter.
     *
     */
    public CustomRecyclerAdapter() {
        //mDataSet = dataSet;

    }

    public void setData( ArrayList<Business> dataSet) {
        mDataSet = dataSet;
    }
    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        viewHolder.getNameTextView().setText(mDataSet.get(position).getName());
        viewHolder.getCategoryTextView().setText(mDataSet.get(position).getCategories().get(0).getAlias());
        viewHolder.getPriceTextView().setText(mDataSet.get(position).getPrice());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (mDataSet !=null)?mDataSet.size():0;
    }
}
