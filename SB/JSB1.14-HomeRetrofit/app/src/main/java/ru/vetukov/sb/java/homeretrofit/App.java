package ru.vetukov.sb.java.homeretrofit;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BackendModule.createInstance();
    }
}
