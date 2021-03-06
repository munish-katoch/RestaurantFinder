package com.katoch.restaurantfinder;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;

import android.location.Location;

import java.io.IOException;
import java.util.List;

public class Utils {

    public static final String EXTRA_BUSINESS_DETAIL ="BUSINESS_DETAIL";

    /*
        Default Location: Toronto (latitude of Toronto City Hall, Toronto, ON, Canada is 43.653908, and the longitude is -79.384293)
        Return LastKnownLocation
     */
    public static Location getLastKnownOrDefaultLocation(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location defaultLocation = new Location("");
        defaultLocation.setLatitude(43.653908);
        defaultLocation.setLongitude(-79.384293);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return defaultLocation;
        }
        Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        return (lastKnownLocation!=null)?lastKnownLocation:defaultLocation;
    }

    public static Address getLocationFromAddress(Context context, String strAddress){

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        Address location = null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null || address.size() <= 0) {
                return null;
            }
            location=address.get(0);
            location.getLatitude();
            location.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }
}
