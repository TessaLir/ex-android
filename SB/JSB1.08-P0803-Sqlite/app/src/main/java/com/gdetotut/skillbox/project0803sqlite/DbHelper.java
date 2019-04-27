package com.gdetotut.skillbox.project0803sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper sInstance;

    private static final String NAME = "mydb.db";
    private static final int VERSION = 1;

    static void createInstance(@NonNull final Context context) {
        sInstance = new DbHelper(context);
    }

    static DbHelper getsInstance() {
        return sInstance;
    }

    private DbHelper(@NonNull final Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String query =
                "CREATE TABLE `" + Article.TABLE_NAME + "` (`"
                        + Article.COLUMN_TITLE + "` VARCHAR , `"
                        + Article.COLUMN_TEXT + "` VARCHAR , `"
                        + Article.COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // later...
    }
}
