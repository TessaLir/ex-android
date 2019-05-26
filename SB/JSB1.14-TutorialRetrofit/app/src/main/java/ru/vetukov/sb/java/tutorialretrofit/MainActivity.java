package ru.vetukov.sb.java.tutorialretrofit;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity.TAG.%$###$";

    private final BackendModule mBackendModule = BackendModule.getInstance();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mPostsList;
    private PostsAdapter mAdapter;

    private final BackendModule.Listener mListener = new BackendModule.Listener() {
        @Override
        public void onStateChanged(BackendModule.State state) {
            switch (state) {
                case IDLE:
                    mSwipeRefreshLayout.setRefreshing(false);
                    mPostsList.setVisibility(View.VISIBLE);
                    mAdapter.setPosts(mBackendModule.getPosts());
                    break;
                case LOADING:
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

        mAdapter = new PostsAdapter();
        mPostsList.setLayoutManager(new LinearLayoutManager(this));
        mPostsList.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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

    private class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

        private final LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
        private List<Post> mPosts = new ArrayList<>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTvTitle.setText(mPosts.get(position).mTitle);
        }

        @Override
        public int getItemCount() {
            return mPosts.size();
        }

        public void setPosts(final List<Post> posts) {
            mPosts = posts;
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
