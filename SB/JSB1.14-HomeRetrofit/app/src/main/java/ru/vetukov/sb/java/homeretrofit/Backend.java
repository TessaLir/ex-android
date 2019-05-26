package ru.vetukov.sb.java.homeretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Backend {

    @GET("/posts")
    Call<List<Post>> listPosts();

    @GET("/users")
    Call<List<User>> listUserss();

}
