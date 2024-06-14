package BLL;

import entity.Faturacompra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class FaturaCompraBll {
    private EntityManager entityManager;

    public FaturaCompraBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarFaturaCompra(int idFatura, int idEncomenda, int idTipoPagamento, float valor, int quantidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Faturacompra fatura = new Faturacompra();
            fatura.setIdfaturac(idFatura);
            fatura.setIdencomenda(idEncomenda);
            fatura.setIdtipopag(idTipoPagamento);
            fatura.setValor(valor);
            fatura.setQuantidade(quantidade);

            entityManager.persist(fatura);
            transaction.commit();

            System.out.println("FaturaCompra inserida com sucesso na tabela.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir na tabela faturacompra: " + e.getMessage());
        }
    }
}
