package com.katoch.restaurantfinder.data;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
Top Level Search Response.

https://www.yelp.com/developers/documentation/v3/business_search
{
  "total": 8228,
  "businesses": [
    {
        ...
    },
    // ...
  ],
  "region": {
    "center": {
      "latitude": 37.767413217936834,
      "longitude": -122.42820739746094
    }
  }
}
 */
public class YelpSearchResponse implements Serializable {
    private static final String TAG = "YelpSearchResponse";

    @JsonGetter("businesses")
    public ArrayList<Business> getBusinesses() {
        return this.businesses;
    }

    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
    }

    ArrayList<Business> businesses;

    @JsonGetter("total")
    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    int total;

    public ArrayList<BusinessCategory> getBusinessesSortByCategory() {
        ArrayList<BusinessCategory> newList = new ArrayList<BusinessCategory>();
        Map<String, HashSet<Business>> map = new HashMap<String, HashSet<Business>>();
        for (Business business : businesses) {
            for (Category category : business.getCategories()) {
                String categoryStr = category.getAlias();
                if (map.containsKey(categoryStr)) {
                    (map.get(categoryStr)).add(business);
                } else {
                    HashSet newHashSet = new HashSet<Business>();
                    newHashSet.add(business);
                    map.put(categoryStr, newHashSet);
                }
            }
        }

        ArrayList<String> sortedKeys = new ArrayList(map.keySet());
        Collections.sort(sortedKeys);

        for (String key : sortedKeys) {
            Log.d(TAG, "category = " + key);
            HashSet<Business> set = map.get(key);
            Business[] tempList = set.toArray(new Business[set.size()]);
            Collections.sort(Arrays.asList(tempList), new BusinessComparator());
            newList.add(new BusinessCategory(null, key, set.size()));
            for (Business business : tempList) {
                Log.d(TAG, "business = " + business.getName());
                newList.add(new BusinessCategory(business, key, set.size()));
            }
        }

        return newList;
    }
}
