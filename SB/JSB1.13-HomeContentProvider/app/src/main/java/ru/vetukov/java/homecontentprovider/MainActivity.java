package ru.vetukov.java.homecontentprovider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/*
Урок 2. ContentProvider: загрузка и отображение контактов телефона
Загрузить контакты, используя ContentProvider.

Основной алгоритм использования получения Контактов, такой же, как и на уроке.
Только использую родной Контент Провайдер для получения контактов.

Для получения требуется так же добавить разрешения в Манифест на чтение Контактов
Добавил проверку на получение контактов... Может немного косолапо, но на мой взгляд норм :)
остальное, все как на уроке,
Так как по контактам ContactsContract.Contacts все стандартное, сторонних классов делать не стал.
*/

public class MainActivity
                            extends AppCompatActivity
                            implements LoaderManager.LoaderCallbacks<Cursor> {

    private ContactAdapter mAdapter;

    private static final int REQUEST_CONTACTS = 2342;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView cities = findViewById(R.id.main_recycler);
        mAdapter = new ContactAdapter(this);
        cities.setLayoutManager(new LinearLayoutManager(this));
        cities.setAdapter(mAdapter);

        init();
    }

    private void init() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED )   {
            // Нужно запросить разрешение у пользователя на просмотр контактов.
            requestPermissions(new String[] { Manifest.permission.READ_CONTACTS },
                    REQUEST_CONTACTS);
        } else {   // Права есть
            getSupportLoaderManager().initLoader(0, null, this);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                new String[] {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME
                },
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mAdapter.setCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.setCursor(null);
    }

    @Override
    public void onRequestPermissionsResult( int requestCode,
                                            @NonNull String[] permissions,
                                            @NonNull int[] grantResults )   {
        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                init();
            } else {
                Toast.makeText(this,"немогу показать контакты",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

        private final LayoutInflater mInflater;

        private Cursor mCursor;

        public ContactAdapter(final Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public void setCursor(final Cursor cursor) {
            this.mCursor = cursor;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (mCursor != null) {
                mCursor.moveToPosition(position);
                holder.id = mCursor.getLong(mCursor.getColumnIndex(ContactsContract.Contacts._ID));
                final String nameAndId = String.format(
                        "#%d %s",
                        holder.id,
                        mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                );
                holder.textView.setText(nameAndId);
            }
        }

        @Override
        public int getItemCount() {
            if (mCursor == null) {
                return 0;
            } else {
                return mCursor.getCount();
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public long id;
            public TextView textView;

            public ViewHolder(final View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }

}
