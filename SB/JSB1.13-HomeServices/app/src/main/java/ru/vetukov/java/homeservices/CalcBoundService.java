package ru.vetukov.java.homeservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class CalcBoundService
                                extends Service
                                implements TheCalc {

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        CalcBoundService getService() { return CalcBoundService.this; }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // тут все методы для калькулятора...
    public int getSumma(int x, int y) { return x + y; }
    public int getMinus(int x, int y) { return x - y; }
    public int getMulty(int x, int y) { return x * y; }
    public int getDevision(int x, int y) { return x / y; } // Условно пусть целый остаток отдает....

}
