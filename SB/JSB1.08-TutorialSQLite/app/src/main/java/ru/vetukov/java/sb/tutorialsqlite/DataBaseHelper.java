package ru.vetukov.java.sb.tutorialsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper sInstance;

    private static final String NAME = "database.db";
    private static final int VERSION = 1;

    public static void createInstanc(@Nullable Context context) {
        sInstance = new DataBaseHelper(context);
    }

    public static DataBaseHelper getInstance() { return sInstance; }

    private DataBaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE   `" + ContractUser.TABLE_NAME + "`  (" +
                    "   `" + ContractUser.COLOMN_NAME_ID        + "`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "   `" + ContractUser.COLOMN_NAME_FNAME     + "`   TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // На будущее
    }
}
