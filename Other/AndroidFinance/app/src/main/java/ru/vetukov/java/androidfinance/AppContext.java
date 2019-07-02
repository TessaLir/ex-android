package ru.vetukov.java.androidfinance;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AppContext extends Application {

    public static final String TAG = AppContext.class.getName();

    public static final String DB_NAME = "money.db";

    private static String dbFolder;
    private static String dbPath;

    @Override
    public void onCreate() {
        super.onCreate();

        checkDBExist(this);
    }

    // если нет файла БД - скопировать его из папки assets
    private void checkDBExist(Context context) {

        // Определить папку с данными приложения
        dbFolder = context.getApplicationInfo().dataDir + "/" + "databases/";

        dbPath = dbFolder + DB_NAME;
        if (!checkDataBaseExists()) {
            copyDataBase(context);
        }

    }

    private static void copyDataBase(Context context) {

        Log.d(TAG, "Copy DB");

        try (InputStream sourceFile = context.getAssets().open(DB_NAME);        // что-то копируем
             OutputStream destinationFolder = new FileOutputStream(dbPath);     // куда кипируем
        ) {

            // создаем папку database
            File databaseFolder = new File(dbFolder);
            databaseFolder.mkdir();

            // копируем по байтам весь файл, стандартным способом JAVA I/O
            byte[] buffer = new byte[1024];
            int length;
            while ((length = sourceFile.read(buffer)) > 0) {
                destinationFolder.write(buffer, 0, length);
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private static boolean checkDataBaseExists() {
        File dbFile = new File(dbPath);
        return dbFile.exists();
    }
}
