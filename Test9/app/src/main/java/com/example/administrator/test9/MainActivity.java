package com.example.administrator.test9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url=new URL("https://www.baidu.com");
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder().url(url).build();
                    Response response=okHttpClient.newCall(request).execute();
                    final String s =response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(s);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
