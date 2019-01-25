package com.example.weiducinema.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.bean.encrypt.UserInfoBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/24
 */
public class Helper extends OrmLiteSqliteOpenHelper {

    public Helper(Context context) {
        super(context, "test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // 建表
            TableUtils.createTable(connectionSource,UserInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
