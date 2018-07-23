package com.example.administrator.test9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
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
                    url=new URL("http://192.168.3.81:8080/Student.xml");
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder().url(url).build();
                    Response response=okHttpClient.newCall(request).execute();
                    String s =response.body().string();
                    XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser=factory.newPullParser();
                    xmlPullParser.setInput(new StringReader(s));
                    int type=xmlPullParser.getEventType();
                    String id=null,name=null,grade=null;
                    while (type!=XmlPullParser.END_DOCUMENT){
                        String nodeName=xmlPullParser.getName();
                        switch (type){
                            case XmlPullParser.START_TAG:
                                if (nodeName.equals("id")){
                                    id=xmlPullParser.nextText();
                                }else if (nodeName.equals("name")){
                                    name=xmlPullParser.nextText();
                                }else if (nodeName.equals("grade")){
                                    grade=xmlPullParser.nextText();
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                if (nodeName.equals("student")){
                                    Log.d("result",id+"--"+name+"--"+grade);
                                }
                        }
                        type=xmlPullParser.next();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
