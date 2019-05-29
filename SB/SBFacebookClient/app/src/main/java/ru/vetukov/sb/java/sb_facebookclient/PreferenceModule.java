package ru.vetukov.sb.java.sb_facebookclient;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceModule {

    private static final String PREFERENCES_FILENAME = "preferences.dat";
    private static final String IS_POSTS_LOADED_FIRST_TIME_KEY = "IS_POSTS_LOADED_FIRST_TIME";

    private static PreferenceModule sInstance;

    private final SharedPreferences mSettings;

    public static void createInstance(final Context context) {
        sInstance = new PreferenceModule(context);
    }

    public static PreferenceModule getInstance() {
        return sInstance;
    }

    private PreferenceModule(final Context context) {
        mSettings = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE);
    }

    public boolean isPostLoadedFirstTime() {
        return mSettings.getBoolean(IS_POSTS_LOADED_FIRST_TIME_KEY, false);
    }

    public void setIsPostsLoadedFirstTime(final boolean postsLoadedFirstTime) {
        mSettings.edit().putBoolean(IS_POSTS_LOADED_FIRST_TIME_KEY, postsLoadedFirstTime).apply();
    }

}
