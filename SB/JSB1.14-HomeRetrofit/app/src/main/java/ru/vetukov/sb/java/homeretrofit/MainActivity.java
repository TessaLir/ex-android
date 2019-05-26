package ru.vetukov.sb.java.homeretrofit;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
Урок 2. RetroFit. Загрузка и использование JSON с сервера
Загрузить с тестового сервера и отобразить список Пользователей или список Комментариев, аналогично тому, как мы загрузили список Постов на занятии.

Тут в общем все то же самое что на уроке, только я добавил абстрактный класс BaseObj.
Он играет роль объекта котрый храним в листе в Адаптере для Ретрофита.

Добавил кнопки прогрузки User или Post.

Класс Post и User наследуются от этого BaseObj.
В Адаптере в методе onBindViewHolder, мы смотрим, что конкретно нам пришло, в зависимости от этого и задаем текстовое поле.

Методы loadPosts и loadUsers. Конечно нужно модернизировать, так как там явный повтор кода. но суть понятна...

*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity.TAG.%$###$";

    private final BackendModule mBackendModule = BackendModule.getInstance();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mPostsList;
    private PostsAdapter mAdapter;

    private Button mBtnPosts;
    private Button mBtnUsers;

    private final BackendModule.Listener mListener = new BackendModule.Listener() {
        @Override
        public void onStateChanged(BackendModule.State state) {
            switch (state) {
                case IDLE_POST:
                    mSwipeRefreshLayout.setRefreshing(false);
                    mPostsList.setVisibility(View.VISIBLE);
                    mAdapter.setOjects(mBackendModule.getPosts());
                    break;
                case IDLE_USERS:
                    mSwipeRefreshLayout.setRefreshing(false);
                    mPostsList.setVisibility(View.VISIBLE);
                    mAdapter.setOjects(mBackendModule.getPosts());
                    break;
                default:
                    mPostsList.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(true);
                    break;
            }
        }

        @Override
        public void onPostsLoaded(List<Post> posts) {
            // do nothing
        }

        @Override
        public void onUsersLoaded(List<User> posts) {
            // do nothing
        }

        @Override
        public void onPostsLoadingFailed(Throwable t) {
            Toast.makeText(MainActivity.this, "Error loading posts", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error loading posts", t);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = findViewById(R.id.main_view_swipe_refresh);
        mPostsList = findViewById(R.id.main_view_posts);

        mBtnPosts = findViewById(R.id.main_btn_post);
        mBtnUsers = findViewById(R.id.main_btn_user);

        mBtnPosts.setOnClickListener(this);
        mBtnUsers.setOnClickListener(this);

        mAdapter = new PostsAdapter();
        mPostsList.setLayoutManager(new LinearLayoutManager(this));
        mPostsList.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBackendModule.setState(1);
                mBackendModule.loadPosts();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBackendModule.setListener(mListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBackendModule.setListener(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_post:
                mBackendModule.setState(1);
                mBackendModule.loadPosts();
                break;
            case R.id.main_btn_user:
                mBackendModule.setState(2);
                mBackendModule.loadUsers();
                break;
        }
    }

    private class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

        private final LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
        private List<BaseObj> mObjects = new ArrayList<>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            BaseObj obj = mObjects.get(position);
            if (obj instanceof Post) {
                holder.mTvTitle.setText(((Post)obj).mTitle);
            }
            if (obj instanceof User) {
                holder.mTvTitle.setText(((User)obj).mName);
            }
        }

        @Override
        public int getItemCount() {
            return mObjects.size();
        }

        public void setOjects(final List<BaseObj> objects) {
            mObjects = objects;
            notifyDataSetChanged();
        }

        public class ViewHolder extends  RecyclerView.ViewHolder {

            public final TextView mTvTitle;

            public ViewHolder(@NonNull View view) {
                super(view);
                mTvTitle = view.findViewById(android.R.id.text1);
            }
        }
    }
}
