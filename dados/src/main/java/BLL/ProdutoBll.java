package BLL;

import entity.Produto;
import jakarta.persistence.*;

import java.util.List;

public class ProdutoBll {
    private EntityManager entityManager;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public ProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarProduto(Integer id, String nome, Float valor, Integer quantidade, String adicionadoPor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Produto produto = new Produto(id, nome, valor, quantidade, adicionadoPor);
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
    }

    public List<Produto> obterProdutosAdicionadosPor(String adicionadoPor) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.adicionadoPor = :adicionadoPor", Produto.class);
        query.setParameter("adicionadoPor", adicionadoPor);
        List<Produto> produtos = query.getResultList();
        em.close();
        return produtos;
    }

    public void removerProduto(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.remove(produto);
        }
        em.getTransaction().commit();
        em.close();
    }
}
