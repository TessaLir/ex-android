package ru.vetukov.java.sb.tutorialasynctask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class RetainingFragment extends Fragment {

    private final Map<String, Object> mStorage = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void put (final String key, final Object value) {
        mStorage.put(key, value);
    }

    public Object get(final String key) {
        return mStorage.get(key);
    }

    public void remove(final String key) {
        mStorage.remove(key);
    }

}
