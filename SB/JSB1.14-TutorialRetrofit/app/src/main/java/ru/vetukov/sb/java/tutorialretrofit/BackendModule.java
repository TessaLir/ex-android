package ru.vetukov.sb.java.tutorialretrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class BackendModule {

    public static final String URL_JPH = "http://jsonplaceholder.typicode.com/";

    private static BackendModule sInstance;

    private final Retrofit mRetrofit;
    private final Backend mBackend;

    private State mState = State.IDLE;
    private List<Post> mPosts = new ArrayList<>();

    private Listener mListener;

    public static void createInstance() {
        sInstance = new BackendModule();
    }

    public static BackendModule getInstance() {
        return sInstance;
    }

    private BackendModule() {
        mRetrofit = new Retrofit.Builder()
                                .baseUrl(URL_JPH)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        mBackend = mRetrofit.create(Backend.class);
    }

    private void changeState(final State newState) {
        mState = newState;

        if (mListener != null) {
            mListener.onStateChanged(mState);
        }
    }

    public void loadPosts() {
        if (mState != State.IDLE) {
            return;
        }

        changeState(State.LOADING);
        mBackend.listPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mPosts = response.body();
                if (mListener != null) {
                    mListener.onPostsLoaded(response.body());
                }
                changeState(State.IDLE);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (mListener != null) {
                    mListener.onPostsLoadingFailed(t);
                }
                changeState(State.IDLE);
            }
        });
    }

    public void setListener(final Listener listener) {
        mListener = listener;
        if (mListener != null) {
            mListener.onStateChanged(mState);
        }
    }

    public List<Post> getPosts() {
        return mPosts;
    }

    public enum State {
        IDLE, LOADING
    }

    public interface Listener {
        void onStateChanged(State state);
        void onPostsLoaded(List<Post> posts);
        void onPostsLoadingFailed(Throwable t);
    }

}
