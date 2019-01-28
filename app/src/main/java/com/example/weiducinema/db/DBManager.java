package com.example.weiducinema.db;

import android.content.Context;

import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.bean.encrypt.UserInfoBean;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/24
 */
public class DBManager {
    private static DBManager sDBManager;
    private Dao<UserInfo,String> userDao;

    private DBManager(Context context) {
        Helper helper = new Helper(context);
        try {
            userDao = helper.getDao(UserInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance(Context context){
        if (sDBManager==null){
            sDBManager = new DBManager(context);
        }
        return sDBManager;
    }

    public Dao<UserInfo, String> getUserDao() {
        return userDao;
    }
}
