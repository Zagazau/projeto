package BLL;

import entity.Pedido;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PedidoBll {
    private EntityManager entityManager;

    public PedidoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarPedido(Pedido pedido) {
        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
    }

    public List<Pedido> obterTodosPedidos() {
        return entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    public List<Pedido> obterPedidosPorCliente(int idCliente) {
        return entityManager.createQuery("SELECT p FROM Pedido p WHERE p.idcliente = :idCliente", Pedido.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }
}
