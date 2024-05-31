package BLL;

import entity.Fornecedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;

public class FornecedorBll {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static void criar(Fornecedor fornecedor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
            System.out.println("Fornecedor criado com sucesso.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar fornecedor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static Fornecedor findFornecedorByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM Fornecedor f WHERE f.username = :username", Fornecedor.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Fornecedor> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Fornecedor", Fornecedor.class).getResultList();
        } finally {
            em.close();
        }
    }
}
