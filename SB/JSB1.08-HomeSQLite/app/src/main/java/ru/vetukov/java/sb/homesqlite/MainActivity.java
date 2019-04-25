package ru.vetukov.java.sb.homesqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();

    private static String[] mSomePerson = new String[] {
        "Иван;Иванов;32342;+8(903)111-22-00",
        "Валентин;Иванов;32342;+8(903)111-22-00",
        "Ксения;Иванов;32342;+8(903)111-22-00",
        "Дарья;Иванов;32342;+8(903)111-22-00",
        "Леонид;Иванов;32342;+8(903)111-22-00",
        "Профья;Иванов;32342;+8(903)111-22-00",
        "Кирилл;Иванов;32342;+8(903)111-22-00",
        "Инокентий;Иванов;32342;+8(903)111-22-00"
    };

    private TextView mTvOutput;
    private Button mBtnClear;
    private Button mBtnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {

        mTvOutput = findViewById(R.id.main_tv_output);
        mBtnClear = findViewById(R.id.main_btn_right);
        mBtnInsert = findViewById(R.id.main_btn_left);

        mBtnInsert.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);

        new SelectTask().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_left :
                new InsertTask().execute();
                break;
            case R.id.main_btn_right :
                new DeleteTask().execute();
                break;
        }
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
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
            for (String userName : mSomePerson) {
                String[] arr = userName.split(";");
                final ContentValues cv = new ContentValues();
                cv.put(ContractUser.COLUMN_LNAME, arr[1]);
                cv.put(ContractUser.COLUMN_FNAME, arr[0]);
                cv.put(ContractUser.COLUMN_PHONE, arr[3]);
                cv.put(ContractUser.COLUBN_EMAIL, arr[2]);
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

    private class SelectTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            Cursor cursor = db.query(ContractUser.TABLE_NAME,null,null,null,null,null,null);

            if (cursor.moveToFirst()) {
                do {
                    String id = getLilne(cursor, ContractUser.COLUBN_ID);
                    String fname = getLilne(cursor, ContractUser.COLUMN_FNAME);
                    String lname = getLilne(cursor, ContractUser.COLUMN_LNAME);
                    String phone = getLilne(cursor, ContractUser.COLUMN_PHONE);
                    String email = getLilne(cursor, ContractUser.COLUBN_EMAIL);

                    result.append(String.format("%s - %s %s   %s   %s\n", getNumber(Integer.parseInt(id)), lname, fname, phone, email));
                } while (cursor.moveToNext());
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String line) {
            super.onPostExecute(line);
            mTvOutput.setText(line);
        }
    }

    private static String getLilne(Cursor cursor, String col) {
        return cursor.getString(cursor.getColumnIndex(col));
    }

    private static String getNumber(int number) {
        if (number < 10) return "00" + number;
        if (number < 100) return "0" + number;
        return "" + number;
    }
}
