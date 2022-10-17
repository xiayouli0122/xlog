# xlog
Android Log Library

## 帮你更方便的打印Log

### 使用方法
```grovvy
compile 'com.yuri.xlog:xlog:1.1.5'
```

（回答某位网友的提问：不支持Eclipse，谢谢）

### 初始化

在Application onCreate中进行初始化

```java
XLog.initialize()
                .setDebug(BuildConfig.DEBUG)
                .hideMethodLink()  //隐藏方法链接，默认显示的
                .hideThreadInfo() //隐藏线程信息，默认显示的
                .setAppTag("Yuri")
                .setNetTag("Net");
```

### 常用的Log打印方法
```java
XLog.i();
String title = "TITLE";
String name = "cats";
XLog.d("title:%s,name:%s,items:%d", title, name, 22);
XLog.d("url:%s", "http://www.baidu.com");
XLog.v("my ssss");
XLog.w("warning");
XLog.e("error:%s", "errorcode msssage");
```
#### json

```java
String json = "{\"addressLatitude\":\"31.246017\",\"addressLongitude\":\"121.609757\",\"city\":\"上海市\",\"province\":\"上海市\",\"header\":{\"clientVersion\":\"1.0.01\",\"requestTime\":1464845832926,\"serviceVersion\":\"1.0\",\"sourceID\":\"1000\",\"userToken\":\"24a4012f-d0de-44f7-9a21-24b62de13f9d\"}}";
XLog.json(json);
```

#### object

```java
UserInfo userInfo = new UserInfo();
        userInfo.userName = "lilei";
        userInfo.password = "123123";
        userInfo.age = 23;
XLog.object(userInfo);

List<String> list = new ArrayList<>();
for (int i = 0 ; i < 8; i++) {
     list.add("name" + i);
}
XLog.object(list);
```

### CrashHandler
当程序出现崩溃时，保存error log到文件
初始化方式
```java
XLogCrashHandler.getInstance().init();
```

还可以将log写入到文件,log文件会保存到/sdcard/[tag] 目录下,其中tag为初始化时设置的tag信息
#### 开始记录Log
```java
//Application onCreate()
XLog.startSaveToFile();
```

#### 停止记录Log
```java
    @Override
    public void onTerminate() {
        super.onTerminate();
        XLog.stopAndSave();
    }
```
更多方法请查看源码

部分灵感来源

感谢 orhanobut
