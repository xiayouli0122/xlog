package com.yuri.xlog.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuri.xlog.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i();
        String title = "TITLE";
        String name = "cats";
        Log.d("title:%s,name:%s,items:%d", title, name, 22);
        Log.v("my ssss");
        Log.w("warning");
        Log.e("error:%s", "errorcode msssage");
//        int n = 12 / 0;
    }
}
