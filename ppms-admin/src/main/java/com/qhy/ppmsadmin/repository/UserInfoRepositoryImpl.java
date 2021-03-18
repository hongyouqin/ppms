package com.qhy.ppmsadmin.repository;

import java.util.Date;

import com.qhy.ppmsadmin.entity.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserInfo findByEmail(String email) {
        try {
            Session session = sessionFactory.getCurrentSession();
            UserInfo userInfo = session.createQuery("from UserInfo as u where u.email = :email", UserInfo.class)
                    .setParameter("email", email).getSingleResult();
            return userInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo findByName(String userName) {
        try {
            Session session = sessionFactory.getCurrentSession();
            UserInfo userInfo = session.createQuery("from UserInfo as u where u.user_name = :user_name", UserInfo.class)
                    .setParameter("user_name", userName).getSingleResult();
            return userInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo save(UserInfo user) {

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public UserInfo updateLoginTime(Date loginTime, String userName) {
        UserInfo existUser = this.findByName(userName);
        if (existUser == null) {
            return null;
        }
        existUser.setLoginTime(loginTime);
        return existUser;
    }

    @Override
    public UserInfo updatePassword(String password, String userName) {
        UserInfo existUser = this.findByName(userName);
        if (existUser == null) {
            return null;
        }
        existUser.setPassword(password);
        return existUser;
    }

}
