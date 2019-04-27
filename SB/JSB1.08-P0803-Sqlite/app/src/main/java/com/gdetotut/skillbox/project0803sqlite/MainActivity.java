//
// Модуль 8, урок 3: SQLite
//          Написать приложение с использованием SQLite для хранения данных, аналогично тому, что показано в видео,
//          но со своими таблицами (например таблица “Article” с названием и куском текста).
//
// 35987: Валерий Куликов, 79068017667@yandex.ru
// 2018/11/04
//
//  Примечания:
// 		- использование Progressbar для ожидания завершения потенциально долгих операций
// 		- обработка поворота экрана
// 		- избегание утечки памяти при помощи статических классов тасков и WeakReference на контекст
//

package com.gdetotut.skillbox.project0803sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final Map<String, String> mArticles = new HashMap<String, String>() {{
        put("BMW", "BMW (German: [ˈbeːˈʔɛmˈveː]; originally an initialism for Bayerische Motoren Werke in German, " +
                "or Bavarian Motor Works in English) is a German multinational company which currently produces " +
                "luxury automobiles and motorcycles, and also produced aircraft engines until 1945.");
        put("Audi", " (German: [ˈʔaʊ̯diː ˈʔaːˈgeː] (About this sound listen)) is a German automobile manufacturer " +
                "that designs, engineers, produces, markets and distributes luxury vehicles. Audi is a member " +
                "of the Volkswagen Group and has its roots at Ingolstadt, Bavaria, Germany. Audi-branded vehicles " +
                "are produced in nine production facilities worldwide.");
        put("Lexus", " (レクサス Rekusasu) is the luxury vehicle division of Japanese automaker Toyota. The Lexus marque " +
                "is marketed in more than 70 countries and territories worldwide[1] and has become Japan's " +
                "largest-selling make of premium cars. It has ranked among the 10 largest Japanese global " +
                "brands in market value.[2] Lexus is headquartered in Nagoya, Japan. Operational centers " +
                "are located in Brussels, Belgium and the U.S. in Plano, Texas.");
        put("Mercedes", " (German: [mɛʁˈtseːdəs]) was a brand of the Daimler-Motoren-Gesellschaft (DMG). " +
                "DMG began to develop in 1900, after the death of its co-founder, Gottlieb Daimler. Although the name " +
                "was not lodged as a trade name until 23 June 1902 and not registered legally until 26 September, " +
                "the brand name eventually would be applied to an automobile model built by Wilhelm Maybach " +
                "to specifications by Emil Jellinek that was delivered to him on 22 December 1900. " +
                "By Jellinek's contract, the new model contained a newly designed engine designated \"Daimler-Mercedes\". " +
                "This engine name is the first instance of the use of the name, Mercedes, by DMG. The automobile " +
                "design would later be called the Mercedes 35 hp.");
    }};

    private final DbHelper mDbHelper = DbHelper.getsInstance();
    private ArticleListAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new ArticleListAdapter(this);
        final RecyclerView mArticleList = findViewById(R.id.view_user_list);
        mArticleList.setAdapter(mAdapter);
        mArticleList.setLayoutManager(new LinearLayoutManager(this));

        mProgressBar = findViewById(R.id.view_progress);

        new SelectTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onInsertButtonClick(View view) {
        new InsertTask(this).execute();
    }

    public void onDeleteButtonClick(View view) {
        new DeleteTask(this).execute();
    }

    /**
     * Задача для выборки и отображения данных.
     */
    private static class SelectTask extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<MainActivity> mRef;

        private SelectTask(MainActivity activity) {
            mRef = new WeakReference<>(activity);
        }

        /**
         * Эмуляция долгой операции выборки.
         */
        @Override
        protected Cursor doInBackground(Void... voids) {

            final long start = System.currentTimeMillis();

            final Cursor cursor = mRef.get().mDbHelper
                    .getWritableDatabase()
                    .query(Article.TABLE_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();

            while (System.currentTimeMillis() < start + 1000) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return cursor;
        }

        @Override
        protected void onPreExecute() {
            mRef.get().mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mRef.get().mAdapter.setCursor(cursor);
            mRef.get().mProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Задача для вставки данных в базу.
     */
    private static class InsertTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<MainActivity> mRef;

        private InsertTask(MainActivity activity) {
            mRef = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (Map.Entry<String, String> entry : mRef.get().mArticles.entrySet()) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put(Article.COLUMN_TITLE, entry.getKey());
                contentValues.put(Article.COLUMN_TEXT, entry.getValue());
                mRef.get().mDbHelper.getWritableDatabase().insert(Article.TABLE_NAME, null, contentValues);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask(mRef.get()).execute();
        }
    }

    /**
     * Задача для удаления данных из базы.
     */
    private static class DeleteTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<MainActivity> mRef;

        private DeleteTask(MainActivity activity) {
            mRef = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mRef.get().mDbHelper.getWritableDatabase().delete(Article.TABLE_NAME, null, null);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask(mRef.get()).execute();
        }
    }


}
