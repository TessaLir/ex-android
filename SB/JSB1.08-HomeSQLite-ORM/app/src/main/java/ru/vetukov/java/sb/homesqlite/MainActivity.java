package ru.vetukov.java.sb.homesqlite;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final DataBaseHelper mHelper = DataBaseHelper.getInstance();

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

            try {
                Dao<Employee, Long> dao = mHelper.getDao(Employee.class);
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
                Dao<Employee, Long> dao = mHelper.getDao(Employee.class);
                for (String userName : mSomePerson) {
                    String[] arr = userName.split(";");
                    Employee emp = new Employee();
                    emp.setmFName(arr[0]);
                    emp.setmLname(arr[1]);
                    emp.setmEmail(arr[2]);
                    emp.setmPhone(arr[3]);
                    dao.create(emp);
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
            try {
                StringBuilder result = new StringBuilder();
                Dao<Employee, Long> dao = mHelper.getDao(Employee.class);

                for (Employee emp : dao.queryForAll()) {
                    result.append(String.format("%s - %s %s   %s   %s\n", getNumber(emp.getmId()),
                                                                          emp.getmFName(),
                                                                          emp.getmLname(),
                                                                          emp.getmPhone(),
                                                                          emp.getmEmail()));
                }

                return result.toString();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String line) {
            super.onPostExecute(line);
            mTvOutput.setText(line);
        }
    }

    private static String getNumber(long numb) {
        if (numb < 10) return "00" + numb;
        if (numb < 100) return "0" + numb;
        return "" + numb;
    }
}
