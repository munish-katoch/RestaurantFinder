package com.katoch.restaurantfinder.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.data.Business;
import com.katoch.restaurantfinder.data.BusinessCategory;
import com.katoch.restaurantfinder.presenter.IPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class DetailViewActivity extends Activity implements IView{

    private static final String TAG = "DetailViewActivity";

    @Inject
    public IPresenter mPresenter;
    private Business mBusinessObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        mBusinessObj = (Business) intent.getSerializableExtra(Utils.EXTRA_BUSINESS_DETAIL);

        mPresenter.attach(this);

        TextView titleView = findViewById(R.id.restaurant_title);
        titleView.setText(mBusinessObj.getName());

        TextView addressView = findViewById(R.id.restaurant_address);
        addressView.setText(mBusinessObj.getLocation().getAddress1() + " " + mBusinessObj.getLocation().getCity());

        TextView phoneView = findViewById(R.id.restaurant_phone);
        phoneView.setText(mBusinessObj.getPhone());

        TextView reviewView = findViewById(R.id.restaurant_review);
        reviewView.setText("Rating " + mBusinessObj.getRating());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestBusinessPhotos(mBusinessObj.getId());
    }

    @Override
    public void setBusinessesInfo(ArrayList<BusinessCategory> dataSet) {

    }


    @Override
    public void setBusinessPhoto(ArrayList<String> photos) {
        if( photos == null) return;

        LinearLayout layout = (LinearLayout) findViewById(R.id.image_list_view);
        int i = 1;
        for (String photo : photos) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i++);
            imageView.setPadding(2, 2, 2, 2);
            GlideApp.with(this).load(photo).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            layout.addView(imageView);
        }
    }

    @Override
    public void onFailure(String errorStr) {

    }
}