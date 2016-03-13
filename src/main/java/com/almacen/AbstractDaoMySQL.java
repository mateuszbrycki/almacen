package com.almacen;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;

public class AbstractDaoMySQL {
    @Inject
    @Qualifier("sessionFactoryMySQL")
    private SessionFactory sessionFactoryMySQL;

    protected Session getSession(){
        return sessionFactoryMySQL.getCurrentSession();
    }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public void update(Object entity) { getSession().update(entity);}

    public void delete(Object entity) {
        getSession().delete(entity);
    }



}
