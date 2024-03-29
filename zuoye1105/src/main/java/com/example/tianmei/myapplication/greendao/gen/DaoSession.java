package com.example.tianmei.myapplication.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bawei.zuoye1105.PersonInfor;

import com.example.tianmei.myapplication.greendao.gen.PersonInforDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig personInforDaoConfig;

    private final PersonInforDao personInforDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        personInforDaoConfig = daoConfigMap.get(PersonInforDao.class).clone();
        personInforDaoConfig.initIdentityScope(type);

        personInforDao = new PersonInforDao(personInforDaoConfig, this);

        registerDao(PersonInfor.class, personInforDao);
    }
    
    public void clear() {
        personInforDaoConfig.clearIdentityScope();
    }

    public PersonInforDao getPersonInforDao() {
        return personInforDao;
    }

}
