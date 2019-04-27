//
// Модуль 8, урок 1: Файловая система и файлы.
//		Создать приложение с двумя кнопками. Одна кнопка копирует из raw в public storage в Picture,
// 		по другой кнопке отобразить картинку (отображение картинки сделать аналогично тому, как сделано в уроке).
//
// 35987: Валерий Куликов, 79068017667@yandex.ru
// 2018/11/02
//
//  Примечания:
// 		- использование Progressbar для ожидания завершения потенциально долгих операций
// 		- обработка поворота экрана
// 		- избегание утечки памяти при помощи статических классов тасков и WeakReference на контекст
//

package com.example.androidtutorialstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

	private static final int PERMISSION_REQUEST_CODE = 123;
	private static final String DEST_FILE_NAME = "android.png";
	public static final String KEY_ALREADY_SHOWN = "ALREADY_SHOWN";

	private ImageView mImageView;
	private ProgressBar mProgress;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = findViewById(R.id.view_image);
		mProgress = findViewById(R.id.view_progress);

		ActivityCompat.requestPermissions(this, new String[]
						{
								Manifest.permission.READ_EXTERNAL_STORAGE,
								Manifest.permission.WRITE_EXTERNAL_STORAGE
						},
				PERMISSION_REQUEST_CODE);

		if(savedInstanceState != null) {
			if(savedInstanceState.getBoolean(KEY_ALREADY_SHOWN, false)) {
				new LoadFromPublicStorageTask(this).execute();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == PERMISSION_REQUEST_CODE) {
			for (int i = 0; i < permissions.length; i++) {
				// Достаточно запросить одну пермиссию, и доступ открывается по обеим.
				if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[i]
						== PackageManager.PERMISSION_DENIED) {
					throw new RuntimeException("WRITE_EXTERNAL_STORAGE is absolutely required");
				}
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(KEY_ALREADY_SHOWN, mImageView.getDrawable() != null);
	}

	public void onCopyButtonClick(final View view) {
		new CopyTask(this).execute();
	}

	public void onShowButtonClick(View view) {
		new LoadFromPublicStorageTask(this).execute();
	}

	/**
	 * Таск для копирования в 'Pictures'
	 */
	private static class CopyTask extends AsyncTask<Void, Void, Void> {

		private WeakReference<MainActivity> mRef;

		CopyTask(@NonNull final MainActivity context) {
			mRef = new WeakReference<>(context);
		}

		@Override
		protected void onPreExecute() {
			final MainActivity act = mRef.get();
			if(act != null) {
				act.mProgress.setVisibility(View.VISIBLE);
			}
		}

		@Override
		protected Void doInBackground(final Void... params) {

			final long startTime = System.currentTimeMillis();

			try {
				final File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				//noinspection ResultOfMethodCallIgnored
				dir.mkdirs();
				final File file = new File(dir, DEST_FILE_NAME);
				final OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
				final InputStream inputStream = new BufferedInputStream(mRef.get().getResources()
						.openRawResource(R.raw.andr_halloween));
				final byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				inputStream.close();
				os.close();

				while (System.currentTimeMillis() - startTime < 1000) {
					Thread.sleep(100);
				}

			} catch (final IOException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(final Void aVoid) {
			final MainActivity act = mRef.get();
			if(act != null) {
				act.mProgress.setVisibility(View.GONE);
				Toast.makeText(act, act.getString(R.string.msg_copying_done), Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * Таск для отображения
	 */
	private static class LoadFromPublicStorageTask extends AsyncTask<Void, Void, Bitmap> {

		private WeakReference<MainActivity> mRef;

		LoadFromPublicStorageTask(@NonNull final MainActivity context) {
			mRef = new WeakReference<>(context);
		}

		@Override
		protected void onPreExecute() {
			final MainActivity act = mRef.get();
			if(act != null) {
				act.mProgress.setVisibility(View.VISIBLE);
			}
		}

		@Override
		protected Bitmap doInBackground(final Void... params) {

			final long startTime = System.currentTimeMillis();

			try {
				final File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				final File file = new File(dir, DEST_FILE_NAME);
				final InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				while (System.currentTimeMillis() - startTime < 1000) {
					Thread.sleep(100);
				}

				return BitmapFactory.decodeStream(inputStream);
			} catch (final FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(final Bitmap bitmap) {
			final MainActivity act = mRef.get();
			if(act != null) {
				act.mProgress.setVisibility(View.GONE);
				act.mImageView.setImageBitmap(bitmap);
			}
		}
	}

}
