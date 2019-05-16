package ru.vetukov.java.tutorialcontentprovider.providers;

import android.net.Uri;

public class CitiesContract {

    public static final String AUTHORITY = "com.example.androidtutorialcontentprovider.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private CitiesContract() {}

    public static final class Cities {

        private Cities() {}

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/city";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "cities");

        public static final String _ID = "_ID";
        public static final String NAME = "name";
    }
}
