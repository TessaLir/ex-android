package ru.vetukov.java.tutorialcontentprovider.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;


public class CitiesContentProvider extends ContentProvider {

    private static final int CITIES = 0;

    private final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    @Override
    public boolean onCreate() {
        mUriMatcher.addURI(CitiesContract.AUTHORITY, "cities", CITIES);

        return true;
    }

    @Override
    public Cursor query(@NonNull final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
        switch (mUriMatcher.match(uri)) {
            case CITIES: {
                final MatrixCursor cursor = new MatrixCursor(new String[]{ CitiesContract.Cities._ID, CitiesContract.Cities.NAME });
                for (int i = 0; i < sCities.length; i++) {
                    cursor.addRow(new Object[]{ i, sCities[i] });
                }
                return cursor;
            }

            default:
                return null;
        }
    }

    @Override
    public String getType(@NonNull final Uri uri) {
        return CitiesContract.Cities.CONTENT_TYPE;
    }

    @Override
    public Uri insert(@NonNull final Uri uri, final ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull final Uri uri, final String selection, final String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs) {
        return 0;
    }

    private static final String[] sCities = {
            "Москва",
            "Санкт-Петербург",
            "Новосибирск",
            "Екатеринбург",
            "Нижний Новгород",
            "Казань",
            "Самара",
            "Челябинск",
            "Омск",
            "Ростов-на-Дону",
            "Уфа",
            "Красноярск",
            "Пермь",
            "Волгоград",
            "Воронеж",
            "Саратов",
            "Краснодар",
            "Тольятти",
            "Тюмень",
            "Ижевск",
            "Барнаул",
            "Ульяновск",
            "Иркутск",
            "Владивосток",
            "Ярославль",
            "Хабаровск",
            "Махачкала",
            "Оренбург",
            "Томск",
            "Новокузнецк",
            "Кемерово",
            "Астрахань",
            "Рязань",
            "Набережные Челны",
            "Пенза",
            "Липецк",
            "Тула",
            "Киров",
            "Чебоксары",
            "Калининград",
            "Курск",
            "Улан-Удэ",
            "Ставрополь",
            "Магнитогорск",
            "Брянск",
            "Иваново",
            "Тверь",
            "Сочи",
            "Белгород",
            "Нижний Тагил",
            "Нью-Йорк",
            "Джэксонвилл",
            "Лос-Анджелес",
            "Индианаполис",
            "Чикаго",
            "Остин",
            "Хьюстон",
            "Сан-Франциско",
            "Филадельфия",
            "Колумбус",
            "Финикс",
            "Форт-Уэрт",
            "Сан-Антонио",
            "Шарлотт",
            "Сан-Диего",
            "Детройт",
            "Даллас",
            "Эль-Пасо",
            "Сан-Хосе",
            "Мемфис"
    };
}