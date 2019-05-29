package ru.vetukov.sb.java.homewebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWvBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWvBrowser = findViewById(R.id.main_web);

        final WebSettings settings = mWvBrowser.getSettings();
        settings.setJavaScriptEnabled(true);
        mWvBrowser.setWebViewClient(new WebViewClient());

        if (savedInstanceState == null) {
            mWvBrowser.loadUrl("http://ya.ru");
        } else {
            mWvBrowser.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWvBrowser.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        if (mWvBrowser.canGoBack()) {
            mWvBrowser.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
