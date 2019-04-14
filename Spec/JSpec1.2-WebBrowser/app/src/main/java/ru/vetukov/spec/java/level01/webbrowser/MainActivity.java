package ru.vetukov.spec.java.level01.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final int W = ViewGroup.LayoutParams.WRAP_CONTENT;
    public static final int M = ViewGroup.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // HARDCORE
        final EditText mAddress = new EditText(this);
        mAddress.setLayoutParams(new ViewGroup.LayoutParams(M, W));

        final Button mButton = new Button(this);
        mButton.setLayoutParams(new ViewGroup.LayoutParams(W, W));
        mButton.setText("Press Me!!!");

        final WebView mWeb =new WebView(this);
        mWeb.setLayoutParams(new ViewGroup.LayoutParams(M, M));
        mWeb.setWebViewClient(new WebViewClient());

        final LinearLayout mL = new LinearLayout(this);
        mL.setLayoutParams(new ViewGroup.LayoutParams(M, M));
        mL.setOrientation(LinearLayout.VERTICAL);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mAddress.getText().toString();
                try {
                    URL u = new URL(text);
                    mWeb.loadUrl(u.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        mL.addView(mAddress);
        mL.addView(mButton);
        mL.addView(mWeb);

        setContentView(mL);

    }
}
