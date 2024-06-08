package DAL;

import entity.Encomenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EncomendaDAL {
    private EntityManagerFactory emf;

    public EncomendaDAL() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    public void salvar(Encomenda encomenda) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(encomenda);
        em.getTransaction().commit();
        em.close();
    }

    public List<Encomenda> listar() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Encomenda> query = em.createQuery("SELECT e FROM Encomenda e", Encomenda.class);
        List<Encomenda> encomendas = query.getResultList();
        em.close();
        return encomendas;
    }

    public void atualizar(Encomenda encomenda) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(encomenda);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Encomenda encomenda = em.find(Encomenda.class, id);
        if (encomenda != null) {
            em.remove(encomenda);
        }
        em.getTransaction().commit();
        em.close();
    }
}
