package ru.vetukov.sb.java.homeretrofit;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendModule {

    public static final String URL_JPH = "http://jsonplaceholder.typicode.com/";

    private static BackendModule sInstance;

    private final Retrofit mRetrofit;
    private final Backend mBackend;

    private State mState = State.IDLE_POST;
    private List<BaseObj> mPosts = new ArrayList<>();

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

    public void setState(int i) {
        switch (i) {
            case 1:
                changeState(State.IDLE_POST);
                break;
            case 2:
                changeState(State.IDLE_USERS);
                break;
            case 3:
                changeState(State.LOADING_POSTS);
                break;
            case 4:
                changeState(State.LOADING_USERS);
                break;
        }
    }

    public void loadPosts() {
        if (mState != State.IDLE_POST) {
            return;
        }

        changeState(State.LOADING_POSTS);
        mBackend.listPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mPosts.clear();
                mPosts.addAll(response.body());
                if (mListener != null) {
                    mListener.onPostsLoaded(response.body());
                }
                changeState(State.IDLE_POST);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (mListener != null) {
                    mListener.onPostsLoadingFailed(t);
                }
                changeState(State.IDLE_POST);
            }
        });
    }

    public void loadUsers() {
        if (mState != State.IDLE_USERS) {
            return;
        }

        changeState(State.LOADING_USERS);
        mBackend.listUserss().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mPosts.clear();
                mPosts.addAll(response.body());
                if (mListener != null) {
                    mListener.onUsersLoaded(response.body());
                }
                changeState(State.IDLE_USERS);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (mListener != null) {
                    mListener.onPostsLoadingFailed(t);
                }
                changeState(State.IDLE_USERS);
            }
        });
    }

    public void setListener(final Listener listener) {
        mListener = listener;
        if (mListener != null) {
            mListener.onStateChanged(mState);
        }
    }

    public List<BaseObj> getPosts() {
        return mPosts;
    }

    public enum State {
        IDLE_POST, IDLE_USERS, LOADING_POSTS, LOADING_USERS
    }

    public interface Listener {
        void onStateChanged(State state);
        void onPostsLoaded(List<Post> posts);
        void onUsersLoaded(List<User> posts);
        void onPostsLoadingFailed(Throwable t);
    }

}
