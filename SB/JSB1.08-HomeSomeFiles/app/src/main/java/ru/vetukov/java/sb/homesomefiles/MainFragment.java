package ru.vetukov.java.sb.homesomefiles;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainFragment extends Fragment implements View.OnClickListener {

    public static final String BTN_COPY = "MainActivity.mBtnCopy";
    public static final String BTN_SHOW = "MainActivity.mBtnShow";
    public static final String FILE_NAME = "bat.jpg";

    public static final int PERM_REQ_CODE = 42579;

    private View view;

    private Button mBtnCopy;
    private Button mBtnShow;
    private ImageView mIvPickage;
    private TextView mTvPreviewText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        init();

        // Заняться было не чем...
        if (savedInstanceState != null) {
            if (!savedInstanceState.getBoolean(BTN_SHOW)) new ReadMapTask().execute();
            else if (!savedInstanceState.getBoolean(BTN_COPY)) {
                mBtnCopy.setClickable(false);
                changeTextViewText();
            }
        }

        return view;
    }

    //TODO Вопрос 1. Не уверен, что это коректно, можно ли так записывать, или как лучше обрабатывать Листнеры?
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_copy :
                new CopyFileTask().execute();
                break;
            case R.id.main_btn_show:
                new ReadMapTask().execute();
                break;
        }
    }

    private void init() {
        mBtnCopy = view.findViewById(R.id.main_btn_copy);
        mBtnShow = view.findViewById(R.id.main_btn_show);
        mIvPickage = view.findViewById(R.id.main_img_image);
        mTvPreviewText = view.findViewById(R.id.main_tv_preview);
        mBtnCopy.setOnClickListener(this);
        mBtnShow.setOnClickListener(this);
        // Были проблемы с проектом Camera, где тестили камеру и запись Битмапа в дирикторию.
        // Данная проверка помогла решить ту проблему!!! УИИИ )))
        ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE },
                PERM_REQ_CODE);
    }

    private void changeTextViewText() {
        mTvPreviewText.append("\nPicture add in Pictures directory!");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BTN_COPY, mBtnCopy.isClickable());
        outState.putBoolean(BTN_SHOW, mBtnShow.isClickable());
    }

    private class CopyFileTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Получим путь к Директории картинок...
                final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES );
                // Определяем Потоки записи и чтения
                final InputStream is = getResources().openRawResource(R.raw.bat);
                final String filePath = String.format("%s/%s",path,FILE_NAME);
                final OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));
                // TMP файл для  побитового чтения из файла
                final byte[] buffer = new byte[1024];
                int length;
                // ситаем файлик побайтово.
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                //Вывел в лог, что бы понять куда оно пишет...
                // /storage/emulated/0/Pictures - Эмулятор, такой же путь и у реального устройствва
                Log.d("#$%$HomeSomeFiles", path.getPath());
                // Закрываем потоки чтения и записи. ОБЯЗАТЕЛЬНО!
                is.close();
                os.close();
                return null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // То что они были кликабельны не страшно, но нечего беспорядочно тыкать :)
            mBtnCopy.setClickable(false);
            // Можно было вывести Toast но я решил поправить TextView
            changeTextViewText();
        }

    }

    private class ReadMapTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mTvPreviewText.setVisibility(View.INVISIBLE);
            // То что они были кликабельны не страшно, но нечего беспорядочно тыкать :)
            mBtnCopy.setClickable(false);
            mBtnShow.setClickable(false);
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            final InputStream inputStream;
            try {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File file = new File(dir, FILE_NAME);
                inputStream = new BufferedInputStream(new FileInputStream(file));
                return BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mIvPickage.setImageBitmap(bitmap);
            mIvPickage.setVisibility(View.VISIBLE);
        }
    }

}
