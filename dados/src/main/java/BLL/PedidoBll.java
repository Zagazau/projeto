package BLL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.Pedido;

public class PedidoBll {
    private EntityManager entityManager;

    public PedidoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarPedido(int idCliente, int quantidade, java.sql.Date data) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Pedido pedido = new Pedido();
            pedido.setIdcliente(idCliente);
            pedido.setQuantidade(quantidade);
            pedido.setData(data);

            entityManager.persist(pedido);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
