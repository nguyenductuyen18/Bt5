package com.example.mp3.sevice;

import com.example.mp3.model.Zing;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZingSevice implements IZingSevice {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            
            sessionFactory = new Configuration()
                    .configure("hirberanate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Zing> findAll() {
        String queryStr = "SELECT c FROM Zing AS c";
        TypedQuery<Zing> query = entityManager.createQuery(queryStr, Zing.class);
        return query.getResultList();
    }

    @Override
    public Zing findById(int id) {
        String queryStr = "SELECT c FROM Zing AS c where c.id = :id";
        TypedQuery<Zing> query = entityManager.createQuery(queryStr, Zing.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(Zing zing) {
        Transaction transaction = null;
        Zing origin;
        if (zing.getId() == 0) {
            origin = new Zing();
        } else {
            origin = findById(zing.getId());
        }
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            origin.setName(zing.getName());
            origin.setArtist(zing.getArtist());
            origin.setFile(zing.getFile());
            session.saveOrUpdate(origin);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
    }


    @Override
    public void AddZing(Zing zing) {
        Transaction transaction = null;
        Zing origin;
        if(zing.getId() == 0) {
            origin = new Zing();
        }else{
            origin=findById(zing.getId());
        }
      try {
          Session session = sessionFactory.openSession();
          transaction = session.beginTransaction();
          origin.setName(zing.getName());
          origin.setArtist(zing.getArtist());
          origin.setFile(zing.getFile());
          session.saveOrUpdate(origin);
          transaction.commit();
      }catch (Exception e) {
      e.printStackTrace();
      if (transaction != null){
          transaction.rollback();
      }
      }
      }







    @Override
    public void deleteZing(Zing zing) {
        Transaction transaction = null;
        Zing origin =findById(zing.getId());
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(zing);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();}
    }
    }



