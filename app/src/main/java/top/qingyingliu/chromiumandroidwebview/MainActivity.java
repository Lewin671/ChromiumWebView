package top.qingyingliu.chromiumandroidwebview;

import android.os.Bundle;
import android.webkit.WebResourceResponse;

import androidx.appcompat.app.AppCompatActivity;

import com.mogoweb.chrome.WebView;
import com.mogoweb.chrome.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Logger.d("shouldInterceptRequest " + url);
                return super.shouldInterceptRequest(view, url);
            }
        });
        webView.loadUrl("https://baidu.com");
        setContentView(webView);
    }
}