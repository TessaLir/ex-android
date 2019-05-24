package ru.vetukov.java.tutorialcontentprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ru.vetukov.java.tutorialcontentprovider.providers.CitiesContract;


public class MainActivity
        extends     AppCompatActivity
        implements  LoaderManager.LoaderCallbacks<Cursor> {

    private CitiesAdapter mAdapter;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView cities = findViewById(R.id.view_cities);
        mAdapter = new CitiesAdapter(this);
        cities.setLayoutManager(new LinearLayoutManager(this));
        cities.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        return new CursorLoader(
                this,
                CitiesContract.Cities.CONTENT_URI,
                new String[] {
                        CitiesContract.Cities._ID,
                        CitiesContract.Cities.NAME
                },
                null,
                null,
                CitiesContract.Cities.NAME + " ASC"
        );
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor) {
        mAdapter.setCursor(cursor);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        mAdapter.setCursor(null);
    }

    private class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

        private final LayoutInflater mInflater;

        private Cursor mCursor;

        public CitiesAdapter(final Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public void setCursor(final Cursor cursor) {
            this.mCursor = cursor;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (mCursor != null) {
                mCursor.moveToPosition(position);
                holder.id = mCursor.getLong(mCursor.getColumnIndex(CitiesContract.Cities._ID));
                final String nameAndId = String.format(
                        "#%d %s",
                        holder.id,
                        mCursor.getString(mCursor.getColumnIndex(CitiesContract.Cities.NAME))
                );
                holder.textView.setText(nameAndId);
            }
        }

        @Override
        public int getItemCount() {
            if (mCursor == null) {
                return 0;
            } else {
                return mCursor.getCount();
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public long id;
            public TextView textView;

            public ViewHolder(final View itemView) {
                super(itemView);

                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
