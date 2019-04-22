package ru.vetukov.java.sb.tutorialsqlite;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String[] mUserNames = new String[] {
            "Vsevolod", "Irina", "Piter", "Harry"
    };

    private final SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();

    //TODO Остановился где то на 29 минуте.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_insert :

                break;
            case R.id.main_btn_clear :
                new DeleteTask().execute();
                break;
        }
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            db.execSQL("DELETE FROM `" + ContractUser.TABLE_NAME + "`");
            return null;
        }
    }

    private class InsertTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
