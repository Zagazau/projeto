package BLL;

import jakarta.persistence.EntityManager;
import java.util.List;
import entity.Admin;

public class AdminBll {

    public static void criar(Admin admin){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
    }

    public static void apagar(Admin admin){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(admin);
        em.getTransaction().commit();
    }

    public static Admin findAdminById(int id){
        return DbConnection.getEntityManager().find(Admin.class, id);
    }

    public static Admin findAdminByUsername(String username){
        EntityManager em = DbConnection.getEntityManager();
        System.out.println("Searching for admin with username: " + username);
        try {
            Admin admin = em.createQuery("SELECT a FROM Admin a WHERE a.username = :username", Admin.class)
                    .setParameter("username", username)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
            System.out.println("Found admin: " + admin); // Verifica se o admin foi encontrado
            return admin;
        } finally {
            em.close(); // Feche o EntityManager somente após ter concluído o trabalho com os resultados da consulta
        }
    }



    public static List<Admin> listar(){
        return DbConnection.getEntityManager().createQuery("FROM Admin", Admin.class).getResultList();
    }
}

