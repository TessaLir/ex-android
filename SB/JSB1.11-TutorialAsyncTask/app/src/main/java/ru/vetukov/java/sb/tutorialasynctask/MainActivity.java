package ru.vetukov.java.sb.tutorialasynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String RETAINING_FRAGMENT_TAG = "MainActivity.RetainingFragment";
    private static final String TASK_UUID_KEY = "MainActivity.TASK_UUID";

    private RetainingFragment mRetainingFragment;

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

        mPbProgress = findViewById(R.id.main_pr_progress);
        mClContent = findViewById(R.id.main_content);
        mTvResult = findViewById(R.id.main_content_result);
        mButton = findViewById(R.id.main_content_submit);

        if (savedInstanceState == null) {
            mTaskKey = UUID.randomUUID().toString();
            mRetainingFragment = new RetainingFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(mRetainingFragment, RETAINING_FRAGMENT_TAG)
                    .commit();
        } else {
            mTaskKey = savedInstanceState.getString(TASK_UUID_KEY);
            mRetainingFragment = (RetainingFragment) getSupportFragmentManager()
                    .findFragmentByTag(RETAINING_FRAGMENT_TAG);
        }

        mTask = (CalculationTask) mRetainingFragment.get(mTaskKey);

        mButton.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mTask != null) {
            mRetainingFragment.put(mTaskKey, mTask);
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
        outState.putString(TASK_UUID_KEY, mTaskKey);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!isChangingConfigurations()) {
            mRetainingFragment.remove(mTaskKey);
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
            case R.id.main_content_submit :
                onCalculateClick(v);
                break;
        }
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
