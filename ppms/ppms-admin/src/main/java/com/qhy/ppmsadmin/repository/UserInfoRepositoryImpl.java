package com.qhy.ppmsadmin.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import com.qhy.ppmsadmin.entity.UserInfo;
import com.qhy.ppmsadmin.exception.DatabaseManipulationException;

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
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            UserInfo userInfo = session.createQuery("from UserInfo as u where u.email = :email", UserInfo.class)
                    .setParameter("email", email).getSingleResult();
            tx.commit();
            session.close();
            return userInfo;
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DatabaseManipulationException(email + " not found");
        }
    }

    @Override
    public UserInfo findByName(String userName) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            UserInfo userInfo = session.createQuery("from UserInfo as u where u.userName = :user_name", UserInfo.class)
                    .setParameter("user_name", userName).getSingleResult();
            tx.commit();
            session.close();
            return userInfo;
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DatabaseManipulationException(userName + " not found");
        }
    }

    @Override
    public UserInfo save(UserInfo user) {

        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DatabaseManipulationException(user.getUserName() + " save failed");
        }
    }

    @Override
    public UserInfo updateLoginTime(Date loginTime, String userName) {
        UserInfo existUser = this.findByName(userName);
        if (existUser == null) {
            return null;
        }
        existUser.setLoginTime(loginTime);
        return save(existUser);
    }

    @Override
    public UserInfo updatePassword(String password, String userName) {
        UserInfo existUser = this.findByName(userName);
        if (existUser == null) {
            return null;
        }
        existUser.setPassword(password);
        return save(existUser);
    }

    @Override
    public List<UserInfo> findAll() {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            List<UserInfo> infos = session.createQuery("from UserInfo", UserInfo.class).list();
            tx.commit();
            session.close();
            return infos;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DatabaseManipulationException("findAll failed");
        }
    }

}
