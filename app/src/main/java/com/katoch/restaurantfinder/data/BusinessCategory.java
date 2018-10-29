package com.katoch.restaurantfinder.data;

public class BusinessCategory {
    private Business mBusiness;
    private String mCategory;
    private int mNoOfBusinessInCategory;

    BusinessCategory(Business business,String category,int noOfBusinessInCategory) {
        mBusiness = business;
        mCategory = category;
        mNoOfBusinessInCategory = noOfBusinessInCategory;
    }

    public Business getBusiness() {
        return mBusiness;
    }

    public String getCategory() {
        return mCategory;
    }

    public int getNoOfBusinessInCategory() {
        return mNoOfBusinessInCategory;
    }
}
