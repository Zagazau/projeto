package DAL;

import entity.Encomenda;
import entity.Faturacompra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EncomendaDAL {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public EncomendaDAL() {
        emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
    }

    public void salvar(Encomenda encomenda) {
        entityManager.getTransaction().begin();
        entityManager.persist(encomenda);
        entityManager.getTransaction().commit();
    }

    public List<Encomenda> listar() {
        TypedQuery<Encomenda> query = entityManager.createQuery("SELECT e FROM Encomenda e", Encomenda.class);
        return query.getResultList();
    }

    public void atualizar(Encomenda encomenda) {
        entityManager.getTransaction().begin();
        entityManager.merge(encomenda);
        entityManager.getTransaction().commit();
    }

    public void excluir(int id) {
        entityManager.getTransaction().begin();

        TypedQuery<Faturacompra> query = entityManager.createQuery("SELECT f FROM Faturacompra f WHERE f.encomenda.idencomenda = :id", Faturacompra.class);
        query.setParameter("id", id);
        List<Faturacompra> faturas = query.getResultList();

        for (Faturacompra fatura : faturas) {
            entityManager.remove(fatura);
        }

        Encomenda encomenda = entityManager.find(Encomenda.class, id);
        if (encomenda != null) {
            entityManager.remove(encomenda);
        }

        entityManager.getTransaction().commit();
    }

    public Encomenda buscarPorId(int id) {
        try {
            TypedQuery<Encomenda> query = entityManager.createQuery("SELECT e FROM Encomenda e WHERE e.idencomenda = :id", Encomenda.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            // Pode lançar uma exceção específica (como NoResultException) se não encontrar a encomenda
            return null; // Ou lançar uma exceção personalizada, dependendo do comportamento esperado
        }
    }
}
