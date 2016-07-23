package com.example.kcstation.js4android;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private WebView webView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.jni_msgView)).setText(getMsgFromJni());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        webView = (WebView) findViewById(R.id.webView);

        webView.setVerticalScrollbarOverlay(true);
        //����WebView֧��JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        String url = "http://192.168.0.109:8080/WebSer/Android.html";
        webView.loadUrl(url);

        //��js�е��ñ���java����
        webView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");

        //��ӿͻ���֧��
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.kcstation.js4android/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.kcstation.js4android/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //��js�е���window.AndroidWebView.showInfoFromJs(name)����ᴥ���˷�����
        @JavascriptInterface
        public void showInfoFromJs(String amap) {
            Toast.makeText(mContext, amap+"", Toast.LENGTH_SHORT).show();
        }
    }

    //��java�е���js����
    public void sendInfoToJs(View view) {
        String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        //����js�еĺ�����showInfoFromJava(msg)
        Map<String,Object> amap = new HashMap<>();
        amap.put("a",msg);
        webView.loadUrl("javascript:showInfoFromJava(" + amap + ")");
    }
    //����c/c++
    static {
        System.loadLibrary("hello-android-jni");
    }
    public native String getMsgFromJni();
}
