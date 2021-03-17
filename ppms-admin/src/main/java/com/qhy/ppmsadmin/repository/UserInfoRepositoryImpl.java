package com.qhy.ppmsadmin.repository;

import com.qhy.ppmsadmin.dto.UserInfo;

import org.hibernate.HibernateException;
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
        // TODO Auto-generated method stub
        try {
            Session session = sessionFactory.getCurrentSession();
            UserInfo userInfo = session.createQuery("from user_info as u where u.email = :id", UserInfo.class)
                    .setParameter("email", email).getSingleResult();
            return userInfo;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo findByName(String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserInfo save(UserInfo user) {
        // TODO Auto-generated method stub

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return user;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public UserInfo updateUser(UserInfo user, String email) {
        // TODO Auto-generated method stub
        return null;
    }

}
