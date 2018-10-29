package com.katoch.restaurantfinder.view;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.adapter.CustomRecyclerAdapter;
import com.katoch.restaurantfinder.data.BusinessCategory;
import com.katoch.restaurantfinder.presenter.IPresenter;
import com.katoch.restaurantfinder.presenter.PresenterFactory;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements IView{

    private IPresenter mPresenter = null;
    private CustomRecyclerAdapter mAdapter = null;
    private RecyclerView mRecyclerView = null;
    private final String TAG="MainActivity";
    private ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.action_bar_title);
        mSpinner = (ProgressBar)findViewById(R.id.progressbar);
        mSpinner.setVisibility(View.VISIBLE);
        mRecyclerView = findViewById(R.id.recycler_view);
        if ( mRecyclerView != null ) {
            mAdapter = new CustomRecyclerAdapter(this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        mPresenter = PresenterFactory.getInstance(getApplicationContext());
        mPresenter.attach(this);
        Location location = Utils.getLastKnownOrDefaultLocation(this);
        mPresenter.requestBusinessesInfo(Double.toString(location.getLatitude()),Double.toString(location.getLongitude()));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Address location = Utils.getLocationFromAddress(getApplicationContext(),s);
                if (location != null) {
                    mSpinner.setVisibility(View.VISIBLE);
                    mPresenter.requestBusinessesInfo(Double.toString(location.getLatitude()),Double.toString(location.getLongitude()));
                    search.clearFocus();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void setBusinessesInfo(final ArrayList<BusinessCategory> dataSet) {
        Log.d(TAG,"setBusinessesInfo");
        mSpinner.setVisibility(View.GONE);
        mAdapter.setData(dataSet);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBusinessPhoto(ArrayList<String> photos) {

    }

    @Override
    public void onFailure(String error) {
        mSpinner.setVisibility(View.GONE);
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
        finish();
    }
}
