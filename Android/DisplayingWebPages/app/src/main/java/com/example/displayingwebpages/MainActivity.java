package com.example.displayingwebpages;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new CallBack());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);//allow zoom
        //webView.loadUrl("http://www.cnn.com");

        String mimeType = "text/html";
        String encoding = "UTF-8";
        String html = "<H1>This is my header</H1><p>this is my paragraph.</p>";
        webView.loadData(html,mimeType,encoding);

    }

    private static class CallBack extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;//on redirect will not open the page in default Browser

        }
    }
}
