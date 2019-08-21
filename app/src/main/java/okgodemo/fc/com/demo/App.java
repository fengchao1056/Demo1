package okgodemo.fc.com.demo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.iflytek.cloud.SpeechUtility;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

public class App extends Application {
    private static App app;
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(this, "appid=5cbd320c");
        mContext = this;
//        r热更新
        Bugly.init(mContext, "5baacfe84d", false);
    }


    public static App getInstance() {
        if (null == app) {
            synchronized (App.class) {
                app = new App();
//        参数1：上下文对象
//        参数2：注册时申请的APPID
//        参数3：是否开启debug模式，true表示打开debug模式，false表示关闭调试模式
                Beta.autoInit = true; //自动初始化开关  真表示应用程序启动自动初始化升级模块; 假不会自动初始化; 开发者如果担心SDK初始化影响应用程序启动速度，可以设置为假，在后面某个时刻手动调用Beta.init（getApplicationContext（），假）;
                Beta.autoCheckUpgrade = true;//自动检查更新开关   真表示初始化时自动检查升级; 假表示不会自动检查升级，需要手动调用Beta.checkUpgrade（）方法;
                Beta.upgradeCheckPeriod = 60 * 1000; //升级检查周期设置  设置升级检查周期为60秒（默认检查周期为0），60秒内SDK不重复向后台请求策略）;
                Beta.initDelay = 1 * 1000; // 延迟初始化  设置启动延时为1秒（默认延时3S），APP启动1秒后初始化SDK，避免影响APP启动速度;
                Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);  //设置SD卡的下载为更新资源存储目录
                Beta.autoDownloadOnWifi = false;  //设置无线上网下自动下载
                Beta.enableHotfix = false;//关闭热更新能力
                Bugly.init(mContext, "5baacfe84d", false);
            }

        }
        return app;
    }

    public static Context getContext() {
        return mContext;
    }
}
