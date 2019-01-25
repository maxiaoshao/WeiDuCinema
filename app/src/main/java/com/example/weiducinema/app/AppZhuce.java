package com.example.weiducinema.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class AppZhuce extends Application {
    private final static String DATA_BASE_NAME = "ZZZ";

    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("hehe")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory()).build();
        //设置磁盘缓存的配置,生成配置文件
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig).build();

        Fresco.initialize(this,config);
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,DATA_BASE_NAME);
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
