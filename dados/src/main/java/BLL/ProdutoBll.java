package BLL;

import entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProdutoBll {
    private EntityManager entityManager;

    public ProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarProduto(Integer id, String nome, Float valor, Integer quantidade, String adicionadoPor) {
        entityManager.getTransaction().begin();
        Produto produto = new Produto(id, nome, valor, quantidade, adicionadoPor);
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
    }

    public List<Produto> obterProdutosAdicionadosPor(String adicionadoPor) {
        TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p WHERE p.adicionadoPor = :adicionadoPor", Produto.class);
        query.setParameter("adicionadoPor", adicionadoPor);
        return query.getResultList();
    }

    public Produto obterProdutoPorNome(String nome) {
        TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    public void removerProduto(Integer id) {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, id);
        if (produto != null) {
            entityManager.remove(produto);
        }
        entityManager.getTransaction().commit();
    }
}
