package ru.vetukov.sb.java.homeretrofit;

import com.google.gson.annotations.SerializedName;

public class Post extends BaseObj {

    @SerializedName("userId")
    public long mUserId;

    @SerializedName("id")
    public long mId;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("body")
    public String mBody;

    public Post(long mUserId, long mId, String mTitle, String mBody) {
        this.mUserId = mUserId;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mBody = mBody;
    }
}
