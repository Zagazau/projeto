package BLL;

import entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;

public class ClienteBLL {

    public static void criar(Cliente cli){
        EntityManager em = DbConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cli);
            em.getTransaction().commit();
            System.out.println("Cliente criado com sucesso.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao criar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void apagar(Cliente cli){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(cli);
        em.getTransaction().commit();
    }

    public static Cliente findClienteByUsername(String username) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.xml");
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.username = :username", Cliente.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    public static String getPasswordForCliente(String username) {
        Cliente cliente = findClienteByUsername(username);
        return cliente != null ? cliente.getSenha() : null;
    }

    public static List<Cliente> listar(){
        return DbConnection.getEntityManager().createQuery("from Cliente").getResultList();
    }

    public static List<Cliente> listarWithName(String nome){
        return DbConnection.getEntityManager().createQuery("from Cliente where nome like ?1").setParameter(1, nome).getResultList();
    }
}
