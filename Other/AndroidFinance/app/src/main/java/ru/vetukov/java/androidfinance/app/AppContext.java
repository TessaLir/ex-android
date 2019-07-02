package ru.vetukov.java.androidfinance.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        DBConnection.initConnection(this);
    }
}
