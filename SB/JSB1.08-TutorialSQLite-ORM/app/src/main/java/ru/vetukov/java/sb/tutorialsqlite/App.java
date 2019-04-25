package ru.vetukov.java.sb.tutorialsqlite;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataBaseHelper.createInstanc(this);
    }
}
