package BLL;

import entity.Faturacompra;
import entity.Encomenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FaturaCompraBll {

    private EntityManager entityManager;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public FaturaCompraBll() {
        entityManager = emf.createEntityManager();
    }

    public void adicionarFaturaCompra(int idfaturac, Encomenda encomenda, int idtipopag, float valor, int quantidade) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Faturacompra faturacompra = new Faturacompra();
            faturacompra.setIdfaturac(idfaturac);
            faturacompra.setEncomenda(encomenda);
            faturacompra.setIdtipopag(idtipopag);
            faturacompra.setValor(valor);
            faturacompra.setQuantidade(quantidade);
            entityManager.persist(faturacompra);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

}
