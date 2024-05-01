package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TipoUtilizadorBll {
    private EntityManager entityManager;

    public TipoUtilizadorBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarTipoUtilizador(int idTipoUtilizador, String nomeTipoUtilizador) {
        String query = "INSERT INTO tipoutilizador (idtipoutilizador, nometipoutil) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            statement = connection.prepareStatement(query);
            statement.setInt(1, idTipoUtilizador);
            statement.setString(2, nomeTipoUtilizador);

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
