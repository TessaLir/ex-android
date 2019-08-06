package com.gdetotut.skillbox.project0804ormlite;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DbHelper.createInstance(this);
    }
}
