package com.nesez.quicksns;





import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

public class MainActivity extends AppCompatActivity implements MainActivity1{


    private WebView webView;
    private String url = "https://yourdomian.nesez.com";                                   //your url 







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void checkPermission() {

    }
    private void clearApplicationCache(){
        File dir = getCacheDir();
        if(dir==null) return;

        File[] children = dir.listFiles();
        try {
            // delete cookies
            //CookieManager cookieManager = CookieManager.getInstance();
            //cookieManager.removeSessionCookie();

            for(int i=0;i<children.length;i++) {
                if(children[i].isDirectory()) {
                    clearApplicationCache(children[i]);
                } else {
                    children[i].delete();
                }
            }
        } catch(Exception e){}
    }


    private void clearApplicationCache(java.io.File dir){
        if(dir==null) dir = getCacheDir();
        if(dir==null) return;

        java.io.File[] children = dir.listFiles();
        try{
            for(int i=0;i<children.length;i++)
                if(children[i].isDirectory()) {
                    clearApplicationCache(children[i]);
                } else {
                    children[i].delete();
                }
        }
        catch(Exception e){}
    }

    private class WebViewClientClass extends WebViewClient {
        @Override

        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }
}
