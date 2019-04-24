package ru.vetukov.java.sb.tutorialsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String[] mUserNames = new String[] {
            "Vsevolod", "Irina", "Piter", "Harry"
    };

    private final DataBaseHelper mHelper = DataBaseHelper.getInstance();

    private RecyclerView mUserList;
    private UserListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserList = findViewById(R.id.main_recycler);
        mAdapter = new UserListAdapter(this);
        mUserList.setLayoutManager(new LinearLayoutManager(this));
        mUserList.setAdapter(mAdapter);

        new SelectTask().execute();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_insert :
                new InsertTask().execute();
                break;
            case R.id.main_btn_clear :
                new DeleteTask().execute();
                break;
        }
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Dao<User, Long> dao = mHelper.getDao(User.class);
                dao.deleteBuilder().delete();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask().execute();
        }
    }

    private class InsertTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Dao<User, Long> dao = mHelper.getDao(User.class);

                for (final String userName : mUserNames) {

                    User user = new User();
                    user.setmFName(userName);
                    dao.create(user);
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask().execute();
        }
    }

    private class SelectTask extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... params) {
            try {
                Dao<User, Long> dao = mHelper.getDao(User.class);

                return dao.queryForAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<User> users) {
            mAdapter.setData(users);
        }
    }

}
