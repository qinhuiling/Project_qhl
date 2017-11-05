package com.bwie.asus.project_qhl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        WebView webView = (WebView) findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();

        //允许交互
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        //加载内容
        webView.loadUrl(url);

    }

}
