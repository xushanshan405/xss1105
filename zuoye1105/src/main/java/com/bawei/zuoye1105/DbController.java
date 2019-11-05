package com.bawei.zuoye1105;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.zuoye1105.data.sqlite.DaoMaster;
import com.bawei.zuoye1105.data.sqlite.DaoSession;
import com.bawei.zuoye1105.data.sqlite.PersonInforDao;


import java.util.List;

public class DbController {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private Context context;
    private PersonInforDao personInforDao;

    private static DbController mDbController;

    public static DbController getInstance(Context context){
        if(mDbController == null){
            synchronized (DbController.class){
                if(mDbController == null){
                    mDbController = new DbController(context);
                }
            }
        }
        return mDbController;
    }

    public DbController(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, "person.db", null);
        mDaoMaster =new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        personInforDao = ((DaoSession) mDaoSession).getPersonInforDao();
    }

    private SQLiteDatabase getReadableDatabase(){
        if(mHelper == null){
            mHelper = new DaoMaster.DevOpenHelper(context,"person.db",null);
        }
        SQLiteDatabase db =mHelper.getReadableDatabase();
        return db;
    }

    private SQLiteDatabase getWritableDatabase(){
        if(mHelper == null){
            mHelper =new DaoMaster.DevOpenHelper(context,"person.db",null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    public void insertOrReplace(PersonInfor personInfor){
        personInforDao.insertOrReplace(personInfor);
    }
    public long insert(PersonInfor personInfor){
        return  personInforDao.insert(personInfor);
    }

    public void update(PersonInfor personInfor){
        PersonInfor mOldPersonInfor = personInforDao.queryBuilder().where(PersonInforDao.Properties.Id.eq(personInfor.getId())).build().unique();//拿到之前的记录
        if(mOldPersonInfor !=null){
            mOldPersonInfor.setName("徐姗姗");
            personInforDao.update(mOldPersonInfor);
        }
    }
    //條件查詢
    public List<PersonInfor> searchByWhere(String wherecluse){
        List<PersonInfor>personInfors = (List<PersonInfor>) personInforDao.queryBuilder().where(PersonInforDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfors;
    }
    //查詢
    public List<PersonInfor> searchAll(){
        List<PersonInfor>personInfors=personInforDao.queryBuilder().list();
        return personInfors;
    }
    //刪除
    public void delete(String wherecluse){
        personInforDao.queryBuilder().where(PersonInforDao.Properties.Name.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }
}
