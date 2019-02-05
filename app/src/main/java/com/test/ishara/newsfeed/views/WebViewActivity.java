package com.test.ishara.newsfeed.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.test.ishara.newsfeed.R;
import com.test.ishara.newsfeed.dto.ArticlesDto;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    ArticlesDto articlesDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);

        articlesDto = (ArticlesDto) getIntent().getExtras().getParcelable("MyClass");
        if (articlesDto == null) {
            finish();
        } else {
            initWebview(articlesDto);
        }

    }

    public void initWebview(ArticlesDto articlesDto) {
       webView.loadUrl(articlesDto.getUrl());
    }
}
