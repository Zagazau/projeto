package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TipoPagamentoBll {
    private EntityManager entityManager;

    public TipoPagamentoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarTipoPagamento(int idTipoPagamento, String nomeTipoPagamento) {
        String query = "INSERT INTO tipopagamento (idtipopag, nometipopag) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            statement = connection.prepareStatement(query);
            statement.setInt(1, idTipoPagamento);
            statement.setString(2, nomeTipoPagamento);

            statement.executeUpdate();
        } catch (SQLException e) {
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
