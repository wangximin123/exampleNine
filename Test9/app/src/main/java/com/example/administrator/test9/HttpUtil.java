package com.example.administrator.test9;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendRequest(final String address, final HttpCallBack httpCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader = null;
                try {
                    URL url=new URL(address);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestMethod("GET");
                    InputStream inputStream=connection.getInputStream();
                    bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder builder=new StringBuilder();
                    String s=null;
                    while ((s=bufferedReader.readLine())!=null){
                        builder.append(s);
                    }
                    if (httpCallBack!=null){
                        httpCallBack.onSuccess(builder.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (httpCallBack!=null){
                        httpCallBack.onError(e);
                    }
                } finally {
                    try {
                        bufferedReader.close();
                    }catch (IOException e){
                        Log.d("result",e.getMessage());
                    }

                }

            }
        }).start();
    }
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
