package ru.vetukov.java.sb.homesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper sInstance;

    public static final String NAME = "database.db";
    public static final int VERSION = 1;

    public static DataBaseHelper getInstance() { return sInstance; }

    public static void createInstance(Context context) {
        sInstance = new DataBaseHelper(context);
    }

    public DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE   `" + ContractUser.TABLE_NAME + "`  (" +
                "   `" + ContractUser.COLUBN_ID        + "`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "   `" + ContractUser.COLUMN_FNAME     + "`   TEXT NOT NULL," +
                "   `" + ContractUser.COLUMN_LNAME     + "`   TEXT NOT NULL," +
                "   `" + ContractUser.COLUMN_PATR      + "`   TEXT," +
                "   `" + ContractUser.COLUMN_PHONE     + "`   TEXT," +
                "   `" + ContractUser.COLUBN_EMAIL     + "`   TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //При обновлении Базы, обновить
    }

}
