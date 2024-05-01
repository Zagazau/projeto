package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilizadorBll {
    private EntityManager entityManager;

    public UtilizadorBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarUtilizador(int idUtilizador, String codPostal, String nome, int telefone, String rua, int numPorta, int nif, int idTipoUtilizador) {
        String query = "INSERT INTO utilizador (idutilizador, codpostal, nome, telefone, rua, numporta, nif, idtipoutilizador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(Connection.class);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUtilizador);
            statement.setString(2, codPostal);
            statement.setString(3, nome);
            statement.setInt(4, telefone);
            statement.setString(5, rua);
            statement.setInt(6, numPorta);
            statement.setInt(7, nif);
            statement.setInt(8, idTipoUtilizador);

            statement.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (SQLException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
