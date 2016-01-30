package com.unitn.local_database.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by erinda on 1/25/16.
 */
public enum Dao {
        instance;
        private EntityManagerFactory emf;

        Dao() {
            if (emf != null) {
                emf.close();
            }
            try {
                emf = Persistence.createEntityManagerFactory("localdb-JPA");
            }catch (Exception e){
                System.out.println("Error while creating entity manager factory!");
                e.printStackTrace();
            }
        }


        public EntityManager createEntityManager() {
            try {
                return emf.createEntityManager();
            } catch (Exception e) {
                System.out.println("Error while creating entity manager!");
                e.printStackTrace();
            }
            return null;
        }


        public static <T> T updateEntity(T entity) {
            EntityManager em = Dao.instance.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            entity = em.merge(entity);
            tx.commit();
            Dao.instance.closeConnections(em);
            return entity;
        }


        public static <T> T saveEntity(T entity) {
            try {
                EntityManager em = Dao.instance.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(entity);
                tx.commit();
                Dao.instance.closeConnections(em);
                return entity;
            }catch (Exception e){
                System.err.println("Error while creating entity of type "+ entity.getClass());
                e.printStackTrace();
                return null;
            }
        }


        public static <T> void removeEntity(T entity) {
            EntityManager em = Dao.instance.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            entity = em.merge(entity);
            em.remove(entity);
            tx.commit();
            Dao.instance.closeConnections(em);
        }



        public void closeConnections(EntityManager em) {
            em.close();
        }

        public EntityTransaction getTransaction(EntityManager em) {
            return em.getTransaction();
        }

        public EntityManagerFactory getEntityManagerFactory() {
            return emf;
        }

}
