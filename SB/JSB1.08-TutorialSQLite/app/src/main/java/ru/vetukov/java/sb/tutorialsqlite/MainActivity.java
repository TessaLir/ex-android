package ru.vetukov.java.sb.tutorialsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String[] mUserNames = new String[] {
            "Vsevolod", "Irina", "Piter", "Harry"
    };

    private final SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();

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
//            db.rawQuery("DELETE FROM `" + ContractUser.TABLE_NAME + "`", null);
            db.delete(ContractUser.TABLE_NAME, null, null);
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
            for (final String userName : mUserNames) {
//                db.rawQuery("INSERT INTO `" + ContractUser.TABLE_NAME + "` (`" +
//                           ContractUser.COLOMN_NAME_FNAME + "`) VALUES (?)",
//                           new String[] {userName});
                final ContentValues cv = new ContentValues();
                cv.put(ContractUser.COLOMN_NAME_FNAME, userName);
                db.insert(ContractUser.TABLE_NAME,null,cv);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new SelectTask().execute();
        }
    }

    private class SelectTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
//            Cursor cursor = db.rawQuery("SELECT * FROM `" + ContractUser.TABLE_NAME + "`", null);
            Cursor cursor = db.query(ContractUser.TABLE_NAME,null,null,null,null,null,null);
            cursor.moveToFirst();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mAdapter.setmCursor(cursor);
        }
    }

}
