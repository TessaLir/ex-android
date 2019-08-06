package ru.vetukov.sb.java.homewebviewjs;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String STR_KEY = "MainActivity.STR_KEY";

    private WebView mWvBrowser;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWvBrowser = findViewById(R.id.main_web);
        final WebSettings setting = mWvBrowser.getSettings();
        setting.setJavaScriptEnabled(true);

        mWvBrowser.setWebViewClient(new WebViewClient());
        mWvBrowser.addJavascriptInterface(new WebAppInterface(), "Android");

        if (savedInstanceState == null) {
            mWvBrowser.loadUrl("file:///android_asset/index.html");
        } else {
            mWvBrowser.restoreState(savedInstanceState);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWvBrowser.saveState(outState);
    }

    private class WebAppInterface {

        @JavascriptInterface
        public void goTo(final String string) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    intent.putExtra(STR_KEY, string);

                    startActivity(intent);

                }
            });
        }

    }
}
