package com.katoch.restaurantfinder.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class YelpSearchResponse  implements Serializable {
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

    public ArrayList<Business> getBusinessesSortByCategory() {
//        Map<String,HashSet<Business>> map = new HashMap<String,HashSet<Business>>();
//        for(Business business:businesses) {
//            for (Category categorie : business.getCategories()) {
//                String categorieStr = categorie.getAlias();
//                if (map.containsKey(categorieStr)) {
//                    (map.get(categorieStr)).add(business);
//                } else {
//                    HashSet newHashSet = new HashSet<Business>();
//                    newHashSet.add(business);
//                    map.put(categorieStr,newHashSet );
//                }
//            }
//        }
        return this.businesses;
    }
}
