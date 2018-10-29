package com.katoch.restaurantfinder.data;

import java.util.Comparator;

public class BusinessComparator  implements Comparator<Business> {
    public int compare(Business b1, Business b2) {
        return b1.getName().compareTo(b2.getName());
    }
}