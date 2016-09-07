package ntu.com.mylife.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PDFController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView mWebView = new WebView(PDFController.this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+getFileName());
        setContentView(R.layout.activity_pdfcontroller);
    }

    @Override
    public static void getFileName() {

    }
}
