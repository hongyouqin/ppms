package com.qhy.ppmsadmin.repository;

import com.qhy.ppmsadmin.entity.Income;
import com.qhy.ppmsadmin.exception.DatabaseManipulationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeRepositoryImpl implements IncomeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Income save(Income item) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(item);
            tx.commit();
            session.close();
            return item;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DatabaseManipulationException(item.getUserName() + " record income failed");
        }
    }

}
