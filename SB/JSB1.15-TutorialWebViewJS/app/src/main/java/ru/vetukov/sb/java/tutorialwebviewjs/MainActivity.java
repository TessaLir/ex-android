package ru.vetukov.sb.java.tutorialwebviewjs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final Random mRandom = new Random();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private WebView mWebView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.main_web);
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(setClient());

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.addJavascriptInterface(new WebAppInterface(), "Android");

        if (savedInstanceState == null) {
            mWebView.loadUrl("file:///android_asset/index.html");
        } else {
            mWebView.restoreState(savedInstanceState);
        }

    }

    private WebChromeClient setClient() {
        return new WebChromeClient(){
            @Override
            public boolean onJsAlert(final WebView view, final String url, final String message, final JsResult result) {

                new AlertDialog.Builder(MainActivity.this)
                               .setTitle("WebApp")
                               .setMessage(message + " Some text")
                               .setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener(){
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       result.confirm();
                                   }
                               })
                               .setCancelable(false)
                               .create()
                               .show();

                return true;
            }
        };
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    public void evaluteJs(final String jsString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.evaluateJavascript(jsString, null);
        } else {
            mWebView.loadUrl("javascript:" + jsString);
        }
    }

    public class WebAppInterface {

        @JavascriptInterface
        public void shotToast(final String toast) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    evaluteJs("document.getElementById('msg').innerHTML = '" + mRandom.nextLong() + "'");
                }
            });
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
        }

    }

}
