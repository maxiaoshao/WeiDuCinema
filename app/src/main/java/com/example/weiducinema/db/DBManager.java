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
    private Dao<UserInfo,String> dao;

    public DBManager(Context context) throws SQLException {
        Helper helper = new Helper(context);
        dao = helper.getDao(UserInfo.class);
    }

    // 创建数据
    public void insertStudent(UserInfo student) throws SQLException {
        //在数据库中创建一条记录，作用与SQLiteDatabase.insert一样
        dao.createOrUpdate(student);
    }

    public void batchInsert(List<UserInfo> user) throws SQLException {
        dao.create(user);
    }
    /**
     * 查询数据
     *
     * @return
     * @throws SQLException
     */
    public List<UserInfo> getStudent() throws SQLException {
        List<UserInfo> list = dao.queryForAll();
        return list;
    }



    /**
     * 删除数据
     *
     * @param student
     * @throws SQLException
     */
    public void deleteStudent(UserInfo student) throws SQLException {
        //只看id
        dao.delete(student);
    }


}
