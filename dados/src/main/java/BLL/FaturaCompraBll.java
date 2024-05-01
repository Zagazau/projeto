package BLL;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FaturaCompraBll {
    private EntityManager entityManager;

    public FaturaCompraBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarFaturaCompra(int idFatura, int idEncomenda, int idTipoPagamento, float valor, int quantidade) {
        String query = "INSERT INTO faturacompra (idfaturac, idencomenda, idtipopag, valor, quantidade) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            entityManager.getTransaction().begin();
            connection = entityManager.unwrap(Connection.class);
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFatura);
            statement.setInt(2, idEncomenda);
            statement.setInt(3, idTipoPagamento);
            statement.setFloat(4, valor);
            statement.setInt(5, quantidade);

            statement.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (SQLException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
