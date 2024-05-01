package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoProdutoBll {
    private EntityManager entityManager;

    public PedidoProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarPedidoProduto(int idPedido, int idProduto) {
        String query = "INSERT INTO pedidoproduto (idpedido, idproduto) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            statement = connection.prepareStatement(query);
            statement.setInt(1, idPedido);
            statement.setInt(2, idProduto);

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
