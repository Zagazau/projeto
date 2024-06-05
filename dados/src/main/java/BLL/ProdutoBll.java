package BLL;

import entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProdutoBll {
    private EntityManager entityManager;

    public ProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarProduto(Produto produto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

