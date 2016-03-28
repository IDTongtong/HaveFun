package com.lanou.tong.fun.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lanou.tong.fun.base.BaseApplication;


/**
 * Created by zt on 16/3/21.
 */
public class DaoSingleton {
    private static final String DATABASE_NAME = "fun.db";
    private volatile static DaoSingleton instance;

    // 数据库
    private SQLiteDatabase db;
    // 管理者
    private DaoMaster daoMaster;
    // 会话
    private DaoSession daoSession;
    private Context context;
    private DaoMaster.DevOpenHelper helper;
    // 对应的表
    // 由java代码生成, 对数据库内相应的表操作使用此对象
    private CollectionDao collectionDao;
    private HistoryDao historyDao;

    public DaoSingleton() {
        context = BaseApplication.getContext();
    }

    public static DaoSingleton getInstance() {
        if (instance == null) {
            synchronized (DaoSingleton.class) {
                if (instance == null) {
                    instance = new DaoSingleton();
                }
            }
        }
        return instance;
    }

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            // 此DevOpenHelper类继承自SQLiteOpenHelper,
            // 第一个参数Context,第二个参数数据库名字,第三个参数CursorFactory
            helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
        }
        return helper;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public CollectionDao getCollectionDao() {
        if (collectionDao == null) {
            collectionDao = getDaoSession().getCollectionDao();
        }
        return collectionDao;
    }

    public HistoryDao getHistoryDao() {
        if (historyDao == null) {
            historyDao = getDaoSession().getHistoryDao();
        }
        return historyDao;
    }
}
