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

    public Produto obterProdutoPorNome(String nome) {
        try {
            return entityManager.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
                    .setParameter("nome", nome)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            return null;
        }
    }

    public void atualizarProduto(Produto produto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
        em.close();
    }

    public boolean temPedidosAssociados(Integer id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Pedido p WHERE p.produto.id = :id", Long.class);
        query.setParameter("id", id);
        Long count = query.getSingleResult();
        em.close();
        return count > 0;
    }

    public void removerProduto(Integer id) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            if (temPedidosAssociados(id)) {
                throw new Exception("Não é possível remover o produto uma vez que está associado a um ou mais pedidos.");
            }
            em.remove(produto);
        }
        em.getTransaction().commit();
        em.close();
    }
}

