package ru.vetukov.spec.java.level01.allsaintsstarter;

import android.support.annotation.NonNull;

public class Saint implements Comparable<Saint> {

    private String mName;
    private String mDob;            // Дата рождения
    private String mDod;            // Дата смерти
    private float mRating = 0f;

    public Saint(String mName, String mDob, String mDod, float mRating) {
        this.mName = mName;
        this.mDob = mDob;
        this.mDod = mDod;
        this.mRating = mRating;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public float getmRating() {
        return mRating;
    }

    public void setmRating(float mRating) {
        this.mRating = mRating;
    }

    public String getmDob() {
        return mDob;
    }

    public String getmDod() {
        return mDod;
    }

    @Override
    public int compareTo(@NonNull Saint saint) {
        return getmName().compareTo(saint.getmName());
    }
}
