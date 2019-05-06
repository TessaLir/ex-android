package ru.vetukov.java.sb.homeasynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    public static final String RET_FR_TAG = "MainActivity.RET_FR_TAG";
    public static final String UUID_KEY   = "MainActivity.UUID_KEY";

    private Retaining mRetaining;

    private ProgressBar mPbProgress;
    private ViewGroup mClContent;
    private TextView mTvResult;

    private Button mButton;


    private final CalculationTask.Listener mListener = new CalculationTask.Listener() {

        @Override
        public void onProgressChanged(final int progress) {
            mPbProgress.setProgress(progress);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onFinished(final int result) {
            updateUi(false);
            mTvResult.setText(String.format("Result: %d", result));
        }
    };

    private CalculationTask mTask;
    private String mTaskKey;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPbProgress = findViewById(R.id.main_progress);
        mClContent = findViewById(R.id.main_view_content);
        mTvResult = findViewById(R.id.main_tv_result);
        mButton = findViewById(R.id.main_btn_submit);

        // С Синглетоном, тут облегчилась жизнь, и кода стало меньше.
        if (savedInstanceState == null) {
            mTaskKey = UUID.randomUUID().toString();
        } else {
            mTaskKey = savedInstanceState.getString(UUID_KEY);
        }
        // Собственно мы получаем один и тот жет экземпляр класса.
        mRetaining = Retaining.getInstance();

        mTask = (CalculationTask) mRetaining.get(mTaskKey);

        mButton.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mTask != null) {
            mRetaining.put(mTaskKey, mTask);
            mTask.setListener(null);
        }
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onResume() {
        super.onResume();

        if (mTask != null) {
            if (mTask.getStatus() != AsyncTask.Status.FINISHED) {
                updateUi(true);
                mPbProgress.setProgress(mTask.getProgress());
                mTask.setListener(mListener);
            } else {
                updateUi(false);
                try {
                    mTvResult.setText(String.format("Result: %d", mTask.get()));
                } catch (final InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            updateUi(false);
            mTvResult.setText("Result: N/A");
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(UUID_KEY, mTaskKey);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!isChangingConfigurations()) {
            mRetaining.remove(mTaskKey);
        }
    }

    public void onCalculateClick(final View view) {
        mTask = new CalculationTask();
        mTask.setListener(mListener);
        mPbProgress.setProgress(0);
        mTask.execute();
        updateUi(true);
    }

    private void updateUi(final boolean isInProgress) {
        if (isInProgress) {
            mPbProgress.setVisibility(View.VISIBLE);
            mClContent.setVisibility(View.GONE);
        } else {
            mPbProgress.setVisibility(View.GONE);
            mClContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_submit :
                onCalculateClick(v);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private static class CalculationTask extends AsyncTask<Void, Integer, Integer> {

        private Listener mListener;
        private int mProgress;

        @Override
        protected Integer doInBackground(final Void... params) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (final InterruptedException e) {
                    throw new RuntimeException(e);
                }
                publishProgress(i);
            }

            return 42;
        }

        @Override
        protected void onProgressUpdate(final Integer... values) {
            mProgress = values[0];
            if (mListener != null) {
                mListener.onProgressChanged(mProgress);
            }
        }

        @Override
        protected void onPostExecute(final Integer result) {
            if (mListener != null) {
                mListener.onFinished(result);
            }
        }

        public void setListener(final Listener listener) {
            mListener = listener;
        }

        public int getProgress() {
            return mProgress;
        }

        public interface Listener {

            void onProgressChanged(int progress);

            void onFinished(int result);
        }
    }
}
