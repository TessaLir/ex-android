package ru.vetukov.java.sb.tutorialcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int PHOTO_CODE = 65486;
    public static final String PHOTO_URI_KEY = "MainActivity.PHOTO_URI_KEY";

    private ImageView mPhotoImageView;
    private Uri mUri;

    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhotoImageView = findViewById(R.id.main_image);
        mButton = findViewById(R.id.main_btn_button);

        mButton.setOnClickListener(onClick());

        if (savedInstanceState != null) {
            mUri = savedInstanceState.getParcelable(PHOTO_URI_KEY);
        }

        if (mUri != null) {
            new LoadPhotoTask().execute(mUri);
        }
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUri =  createPhotoUri();
                final Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                startActivityForResult(intent, PHOTO_CODE);

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PHOTO_CODE && resultCode == RESULT_OK) {
            new LoadPhotoTask().execute(mUri);
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            mPhotoImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(PHOTO_URI_KEY, mUri);
    }

    private Uri createPhotoUri() {
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        path.mkdir();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");

        final File photoFile = new File(path, dateFormat.format(Calendar.getInstance().getTime()) + ".jpg");

        return Uri.fromFile(photoFile);
    }

    private class LoadPhotoTask extends AsyncTask<Uri, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Uri... params) {
            final Uri  photoUri = params[0];
            return BitmapFactory.decodeFile(photoUri.getPath());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mPhotoImageView.setImageBitmap(bitmap);
        }
    }
}
