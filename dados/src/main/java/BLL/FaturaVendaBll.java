package BLL;

import BLL.DbConnection;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FaturaVendaBll {
    private EntityManager entityManager;

    public FaturaVendaBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarFaturaVenda(int idFatura, int idPedido, int idTipoPagamento, int idUtilizador, float valor, int quantidade) {
        String query = "INSERT INTO faturavenda (idfaturav, idpedido, idtipopag, idutilizador, valor, quantidade) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFatura);
            statement.setInt(2, idPedido);
            statement.setInt(3, idTipoPagamento);
            statement.setInt(4, idUtilizador);
            statement.setFloat(5, valor);
            statement.setInt(6, quantidade);

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
