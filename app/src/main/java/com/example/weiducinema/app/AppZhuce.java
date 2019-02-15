package com.example.weiducinema.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.umeng.analytics.MobclickAgent;

public class AppZhuce extends MultiDexApplication {
    private final static String DATA_BASE_NAME = "ZZZ";
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("hehe")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory()).build();
        //设置磁盘缓存的配置,生成配置文件
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig).build();

        Fresco.initialize(this,config);
//        UMConfigure.init(this, "you AppKey", "you channel", UMConfigure.DEVICE_TYPE_PHONE, null);
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//
//        //禁止默认的页面统计功能，这样将不会再自动统计Activity页面。（包含Activity、Fragment或View的应用）
//        MobclickAgent.openActivityDurationTrack(false);
//
//        // 打开统计SDK调试模式（上线时记得关闭）
//        UMConfigure.setLogEnabled(true);
//        0.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,DATA_BASE_NAME);
//        SQLiteDatabase db = openHelper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        mdaoSession = daoMaster.newSession();
//        TestinDataConfig testinDataConfig = new TestinDataConfig()
//                .openShake(true)//设置是否打开摇一摇反馈bug功能
//                .collectCrash(true)//设置是否收集app崩溃信息
//                .collectANR(true)//设置是否收集ANR异常信息
//                .collectLogCat(false)//设置是否收集logcat系统日志
//                .collectUserSteps(true);//设置是否收集用户操作步骤
//        //SDK初始化
//        TestinDataApi.init(this, "985828a181852a865dceae94774c1b16", testinDataConfig);
    }
//    public static DaoSession getMdaoSession(){
//        return mdaoSession;
//    }
}
