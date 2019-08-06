package ru.vetukov.sb.tutorialrecyclerviewadv;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.main_view_progressbar);

        mRecyclerView = findViewById(R.id.main_recycler_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        new LoadGoogleReportsTask().execute();
    }


    private List<String> getGoogleReposNames() {
        List<String> names = new ArrayList<>();

        URL url;
        try {
            url = new URL("https://api.github.com/users/google/repos");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpURLConnection urlConnection;
        StringBuilder sb = new StringBuilder();

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            do {
                line = reader.readLine();
                sb.append(line);
            } while (line != null);
            urlConnection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray reposJsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < reposJsonArray.length(); i++) {
                names.add(reposJsonArray.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return names;
    }

    private class LoadGoogleReportsTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            List<String> names = getGoogleReposNames();
            return names.toArray(new String[names.size()]);
        }

        @Override
        protected void onPostExecute(String[] strings) {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            mAdapter.setmData(strings);
            mAdapter.notifyDataSetChanged();
        }
    }

    private static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private String[] mData;

        public ListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public void setmData(String[] mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.view_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.titleTextView.setText((mData[position]));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView titleTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                titleTextView = itemView.findViewById(R.id.item_title);
            }
        }
    }
}
