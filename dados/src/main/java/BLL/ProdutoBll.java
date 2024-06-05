package BLL;

import entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProdutoBll {
    private EntityManager entityManager;

    public ProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarProduto(int idProduto, String nome, float valor, int quantidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Produto produto = new Produto();
            produto.setId(idProduto);
            produto.setNome(nome);
            produto.setValor(valor);
            produto.setQuantidade(quantidade);
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
