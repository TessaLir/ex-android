package ru.vetukov.sb.java.homeretrofit;

import com.google.gson.annotations.SerializedName;

public class User extends BaseObj {

    @SerializedName("id")
    long mId;

    @SerializedName("name")
    String mName;

    @SerializedName("email")
    String mEmail;

    @SerializedName("phone")
    String mPhone;

    @SerializedName("website")
    String mWebsite;

    @SerializedName("company")
    Company mCompany;

    @SerializedName("address")
    Company mAddress;

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
