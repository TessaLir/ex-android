package ru.vetukov.java.sb.tutorialstorage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 123;

    private ImageView mIVImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIVImage = findViewById(R.id.main_img_image);

        // Проверка permission для чтения из PUBLIC STORAGE
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }

    }

    /*@Override
	public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
		if (requestCode == PERMISSION_REQUEST_CODE) {
			for (int i = 0; i < permissions.length; i++) {
				if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[i] == PackageManager.PERMISSION_DENIED) {
					throw new RuntimeException("WRITE_EXTERNAL_STORAGE is absolutely required");
				}
			}
		}
	}*/

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // Читаем из Ассета
            case R.id.main_btn_load_assets:
                new LoadFromAssetsTask().execute();
                break;
            // Читаем из ресурса (Картинка)
            case R.id.main_btn_load_resource:
                new LoadFromResourcesTask().execute();
                break;
            // Читаем из ресурса (Любой файл)
            case R.id.main_btn_load_resource_file:
                new LoadFromResourcesFileTask().execute();
                break;
            // Копируем чтото в PRIVATE STORAGE
            case R.id.main_btn_private_storage:
                new CopyTask().execute();
                break;
            // Читаем что то из PRIVATE STORAGE
            case R.id.main_btn_private_storage_show:
                new LoadFromPrivateStorageTask().execute();
                break;
            // Читаем что то из PUBLIC STORAGE
            case R.id.main_btn_public_storage_show:
                new LoadFromPublicStorageTask().execute();
                break;
        }
    }

    private class LoadFromAssetsTask extends AsyncTask<Void,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {

                final InputStream inputStream = getAssets().open("photo_green.png");
                return BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mIVImage.setImageBitmap(bitmap);

        }
    }

    private class LoadFromResourcesTask extends AsyncTask<Void,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            return BitmapFactory.decodeResource(getResources(), R.raw.photo_blue);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mIVImage.setImageBitmap(bitmap);

        }
    }

    private class LoadFromResourcesFileTask extends AsyncTask<Void,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            final InputStream inputStream = getResources().openRawResource(R.raw.photo_blue);
            return BitmapFactory.decodeStream(inputStream);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mIVImage.setImageBitmap(bitmap);

        }
    }

    private class CopyTask extends AsyncTask<Void,Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                final OutputStream outputStream = new BufferedOutputStream(openFileOutput("photo_purple.png",
                        Context.MODE_PRIVATE));
                final InputStream inputStream = new BufferedInputStream(getAssets().open("photo_purple.png"));
                final byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.close();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
        }
    }

    private class LoadFromPrivateStorageTask extends AsyncTask<Void,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            final InputStream inputStream;
            try {
                inputStream = new BufferedInputStream(openFileInput("photo_purple.png"));
                return BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mIVImage.setImageBitmap(bitmap);

        }
    }

    private class LoadFromPublicStorageTask extends AsyncTask<Void,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            final InputStream inputStream;
            try {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file = new File(dir, "bmw.jpg");
                inputStream = new BufferedInputStream(new FileInputStream(file));
                return BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mIVImage.setImageBitmap(bitmap);

        }
    }

}
