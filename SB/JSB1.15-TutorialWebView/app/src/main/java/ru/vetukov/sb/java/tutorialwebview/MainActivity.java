package ru.vetukov.sb.java.tutorialwebview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWvWeb;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWvWeb = findViewById(R.id.main_web);
        final WebSettings settings = mWvWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWvWeb.setWebViewClient(new WebViewClient());

        if (savedInstanceState == null) {
            mWvWeb.loadUrl("http://ru.wikipedia.org");
        } else {
            mWvWeb.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWvWeb.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        if (mWvWeb.canGoBack()) {
            mWvWeb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
