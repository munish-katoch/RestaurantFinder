package com.katoch.restaurantfinder;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;

import android.location.Location;

public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
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
}
