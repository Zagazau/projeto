package DAL;

import entity.Encomenda;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EncomendaDAL {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EncomendaDAL() {
        this.emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
    }

    public void salvar(Encomenda encomenda) {
        em.getTransaction().begin();
        em.persist(encomenda);
        em.getTransaction().commit();
    }

    public List<Encomenda> listar() {
        return em.createQuery("FROM Encomenda", Encomenda.class).getResultList();
    }

    public void atualizar(Encomenda encomenda) {
        em.getTransaction().begin();
        em.merge(encomenda);
        em.getTransaction().commit();
    }

    public void excluir(int id) {
        em.getTransaction().begin();
        Encomenda encomenda = em.find(Encomenda.class, id);
        if (encomenda != null) {
            em.remove(encomenda);
        }
        em.getTransaction().commit();
    }
}
