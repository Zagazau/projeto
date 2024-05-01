package BLL;

import entity.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteBLL {


    public static void criar(Cliente cli){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(cli);
        em.getTransaction().commit();
    }

    public static void apagar(Cliente cli){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(cli);
        em.getTransaction().commit();
    }

    public static Cliente findClienteByUsername(String username) {
        EntityManager em = DbConnection.getEntityManager();
        return em.createQuery("SELECT c FROM Cliente c WHERE c.username = :username", Cliente.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
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
