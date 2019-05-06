package ru.vetukov.java.sb.homeasynctask;

import java.util.HashMap;
import java.util.Map;

public class Retaining {

    private static Retaining retaining;

    private final Map<String, Object> mStorage;

    private Retaining() {
        mStorage = new HashMap<>();
    }

    public static Retaining getInstance() {
        if (retaining == null) {
            retaining = new Retaining();
        }
        return retaining;
    }

    public void put(final String key, final Object value) {
        mStorage.put(key, value);
    }

    public Object get(final String key) {
        return mStorage.get(key);
    }

    public void remove(final String key) {
        mStorage.remove(key);
    }

}
