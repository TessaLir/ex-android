package ru.vetukov.sb.java.homeretrofit;

import com.google.gson.annotations.SerializedName;

public class Post extends BaseObj {

    @SerializedName("userId")
    long mUserId;

    @SerializedName("id")
    long mId;

    @SerializedName("title")
    String mTitle;

    @SerializedName("body")
    String mBody;

    public Post(long mUserId, long mId, String mTitle, String mBody) {
        this.mUserId = mUserId;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mBody = mBody;
    }
}
