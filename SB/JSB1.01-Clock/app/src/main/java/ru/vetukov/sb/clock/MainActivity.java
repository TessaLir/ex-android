package ru.vetukov.sb.clock;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int TICK_DELAY_MILLS = 250;

    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private TextView mTimeTextView;
    private TextView mDateTextView;

    private Runnable mTickRoutine = new Runnable() {
        @Override
        public void run() {
            mTimeTextView.setText(mTimeFormat.format(Calendar.getInstance().getTime()));
            mDateTextView.setText(mDateFormat.format(Calendar.getInstance().getTime()));
            mHandler.postDelayed(this, TICK_DELAY_MILLS);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = findViewById(R.id.main_tvTime);
        mDateTextView = findViewById(R.id.main_tvDate);

        mTickRoutine.run();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacks(mTickRoutine);
        mTimeTextView = null;
        mDateTextView = null;

    }
}
