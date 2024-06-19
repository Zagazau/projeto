package DAL;

import BLL.DbConnection;
import entity.Encomenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EncomendaDAL {
    private EntityManager entityManager;

    public EncomendaDAL() {
        this.entityManager = DbConnection.getEntityManager();
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
        Encomenda encomenda = entityManager.find(Encomenda.class, id);
        if (encomenda != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(encomenda);
            entityManager.getTransaction().commit();
        }
    }
}
