package ru.vetukov.spec.java.level01.beautifulplacesstarter;

public class Place {

    private String mPlace;
    private String mDescription;
    private String mOldPrice;
    private String mNewPrice;
    private String mPicture;

    public Place(String mPlace, String mDescription, String mOldPrice, String mNewPrice, String mPicture) {
        this.mPlace = mPlace;
        this.mDescription = mDescription;
        this.mOldPrice = mOldPrice;
        this.mNewPrice = mNewPrice;
        this.mPicture = mPicture;
    }

    public String getmPlace() {
        return mPlace;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmOldPrice() {
        return mOldPrice;
    }

    public void setmOldPrice(String mOldPrice) {
        this.mOldPrice = mOldPrice;
    }

    public String getmNewPrice() {
        return mNewPrice;
    }

    public void setmNewPrice(String mNewPrice) {
        this.mNewPrice = mNewPrice;
    }

    public String getmPicture() {
        return mPicture;
    }

    public void setmPicture(String mPicture) {
        this.mPicture = mPicture;
    }
}
