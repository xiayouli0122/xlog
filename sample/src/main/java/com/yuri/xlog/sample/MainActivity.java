package com.yuri.xlog.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yuri.xlog.Log;
import com.yuri.xlog.Settings;
import com.yuri.xlog.util.ObjParser;
import com.yuri.xlog.util.XmlJsonParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i();
        String title = "TITLE";
        String name = "cats";
        Log.d("title:%s,name:%s,items:%d", title, name, 22);
        Log.d("url:%s", "http://www.baidu.com");
        Log.v("my ssss");
        Log.w("warning");
        Log.e("error:%s", "errorcode msssage");

        String json = "{\"addressLatitude\":\"31.246017\",\"addressLongitude\":\"121.609757\",\"city\":\"上海市\",\"province\":\"上海市\",\"header\":{\"clientVersion\":\"1.0.01\",\"requestTime\":1464845832926,\"serviceVersion\":\"1.0\",\"sourceID\":\"1000\",\"userToken\":\"24a4012f-d0de-44f7-9a21-24b62de13f9d\"}}";
        Log.json(json);
        UserInfo userInfo = new UserInfo();
        userInfo.userName = "lilei";
        userInfo.password = "123123";
        userInfo.age = 23;
        Log.object(userInfo);

        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 8; i++) {
            list.add("name" + i);
        }
        Log.object(list);


        findViewById(R.id.btn_test)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("test btn has clicked");
                    }
                });

    }
}
