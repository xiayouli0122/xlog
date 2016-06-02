# xlog
Android Log Library

帮你更方便的打印Log

使用方法
compile 'com.yuri.xlog:xlog:0.1.1'

初始化
在Application onCreate中进行初始化
`Log.initialize(
                Settings.getInstance()
                .isDebug(BuildConfig.DEBUG)
                        .isShowMethodLink(false)
                        .isShowThreadInfo(true)
                .setAppTag("Yuri")
                .setNetTag("YuriNet")
        );`
