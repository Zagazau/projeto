package BLL;

import entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;

public class ClienteBLL {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static void criar(Cliente cli) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cli);
            em.getTransaction().commit();
            System.out.println("Cliente criado com sucesso.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void apagar(Cliente cli) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(cli) ? cli : em.merge(cli));
            em.getTransaction().commit();
            System.out.println("Cliente apagado com sucesso.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao apagar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static Cliente findClienteByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.username = :username", Cliente.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static String getPasswordForCliente(String username) {
        Cliente cliente = findClienteByUsername(username);
        return cliente != null ? cliente.getSenha() : null;
    }

    public static List<Cliente> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Cliente", Cliente.class).getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Cliente> listarWithName(String nome) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Cliente where nome like ?1", Cliente.class)
                    .setParameter(1, nome)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
