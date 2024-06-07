package BLL;

import entity.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PedidoBll {
    private EntityManager entityManager;

    public PedidoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarPedido(Pedido pedido) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
    }
}
