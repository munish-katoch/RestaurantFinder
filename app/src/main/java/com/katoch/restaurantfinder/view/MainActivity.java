package com.katoch.restaurantfinder.view;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.katoch.restaurantfinder.R;
import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.adapter.CustomRecyclerAdapter;
import com.katoch.restaurantfinder.model.Business;
import com.katoch.restaurantfinder.presenter.IPresenter;
import com.katoch.restaurantfinder.presenter.PresenterFactory;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements IView{

    private IPresenter mPresenter = null;
    private CustomRecyclerAdapter mAdapter = null;
    private RecyclerView mRecyclerView = null;
    private final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.action_bar_title);
        mRecyclerView = findViewById(R.id.recycler_view);
        if ( mRecyclerView != null ) {
            mAdapter = new CustomRecyclerAdapter();
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        mPresenter = PresenterFactory.getInstance(getApplicationContext());
        mPresenter.attach(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Location location = Utils.getLastKnownOrDefaultLocation(this);
        mPresenter.requestData(Double.toString(location.getLatitude()),Double.toString(location.getLongitude()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
    public void showProgress() {

    }

    @Override
    public void setDataToRecyclerView(final ArrayList<Business> dataSet) {
        Log.d(TAG,"setDataToRecyclerView");
        mAdapter.setData(dataSet);
        mAdapter.notifyDataSetChanged();
//        runOnUiThread(new Runnable() {
//            public void run() {
//
//            }
//        });
    }

    @Override
    public void onFailure(String error) {
        runOnUiThread(new Runnable() {
            public void run() {

            }
        });

    }
}
