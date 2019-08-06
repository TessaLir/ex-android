package ru.vetukov.sb.java.homeretrofit;

import com.google.gson.annotations.SerializedName;

public class User extends BaseObj {

    @SerializedName("id")
    public long mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("email")
    public String mEmail;

    @SerializedName("phone")
    public String mPhone;

    @SerializedName("website")
    public String mWebsite;

    @SerializedName("company")
    public Company mCompany;

    @SerializedName("address")
    public Company mAddress;

    private class Company {

        @SerializedName("name")
        String mName;

        @SerializedName("catchPhrase")
        String mCatchPhrase;

        @SerializedName("bs")
        String mBs;

    }

    private class Address {

        @SerializedName("street")
        String mStreet;

        @SerializedName("suite")
        String mSuite;

        @SerializedName("city")
        String mCity;

        @SerializedName("zipcode")
        String mZipcode;

        @SerializedName("geo")
        GEO mGEO;

        private class GEO {

            @SerializedName("lat")
            String mLat;

            @SerializedName("lng")
            String mLng;

        }

    }
}
