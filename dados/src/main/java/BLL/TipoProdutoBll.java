package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TipoProdutoBll {
    private EntityManager entityManager;

    public TipoProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarTipoProduto(int idTipoProduto, String descricao, int idProduto) {
        String query = "INSERT INTO tipoproduto (idtipoproduto, descricao, idproduto) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            statement = connection.prepareStatement(query);
            statement.setInt(1, idTipoProduto);
            statement.setString(2, descricao);
            statement.setInt(3, idProduto);

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
