package com.katoch.restaurantfinder.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.model.Business;

public class DetailViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Business businessObj = (Business) intent.getSerializableExtra(Utils.EXTRA_BUSINESS_DETAIL);
        //imageView1 - imageView10
        //restaurant_title
        //restaurant_address
        //restaurant_phone
        //restaurant_review
        TextView titleView = findViewById(R.id.restaurant_title);
        titleView.setText(businessObj.getName());

        TextView addressView = findViewById(R.id.restaurant_address);
        addressView.setText(businessObj.getAddress());

        TextView phoneView = findViewById(R.id.restaurant_phone);
        phoneView.setText(businessObj.getPhone());

        TextView reviewView = findViewById(R.id.restaurant_review);
        reviewView.setText("Rating " + businessObj.getRating());
    }

}