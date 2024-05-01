package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoBll {
    private EntityManager entityManager;

    public PedidoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarPedido(int idPedido, int idCliente, int quantidade, String data) {
        String query = "INSERT INTO pedido (idpedido, idcliente, quantidade, data) VALUES (?, ?, ?, ?)";
        try {
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(Connection.class);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPedido);
            statement.setInt(2, idCliente);
            statement.setInt(3, quantidade);
            statement.setString(4, data);

            statement.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (SQLException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
