package BLL;

import entity.Faturavenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class FaturaVendaBll {
    private EntityManager entityManager;

    public FaturaVendaBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarFaturaVenda(int idFatura, int idPedido, int idTipoPagamento, int idCliente, float valor, int quantidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Faturavenda faturavenda = new Faturavenda();
            faturavenda.setIdfaturav(idFatura);
            faturavenda.setIdpedido(idPedido);
            faturavenda.setIdtipopag(idTipoPagamento);
            faturavenda.setIdcliente(idCliente);
            faturavenda.setValor(valor);
            faturavenda.setQuantidade(quantidade);
            entityManager.persist(faturavenda);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
