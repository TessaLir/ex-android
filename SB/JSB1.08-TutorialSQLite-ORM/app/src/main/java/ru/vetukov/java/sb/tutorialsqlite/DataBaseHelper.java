package ru.vetukov.java.sb.tutorialsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static DataBaseHelper sInstance;

    private static final String NAME = "database.db";
    private static final int VERSION = 1;

    public static void createInstanc(Context context) {
        sInstance = new DataBaseHelper(context);
    }

    public static DataBaseHelper getInstance() { return sInstance; }

    private DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        // На будущее
    }
}
