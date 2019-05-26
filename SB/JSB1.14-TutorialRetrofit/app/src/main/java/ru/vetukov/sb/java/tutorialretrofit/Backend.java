package ru.vetukov.sb.java.tutorialretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Backend {

    @GET("/posts")
    Call<List<Post>> listPosts();

}
