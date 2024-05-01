package BLL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbConnection {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private static EntityManager em = factory.createEntityManager();
    //private static EntityTransaction transaction = em.getTransaction();

    public static EntityManager getEntityManager(){
        return em;
    }
}