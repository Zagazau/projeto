package BLL;

import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoBll {
    private EntityManager entityManager;

    public ProdutoBll() {
        entityManager = DbConnection.getEntityManager();
    }

    public void adicionarProduto(int idProduto, String nome, float valor, int quantidade) {
        String query = "INSERT INTO produto (idproduto, nome, valor, quantidade) VALUES (?, ?, ?, ?)";
        try {
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(Connection.class);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProduto);
            statement.setString(2, nome);
            statement.setFloat(3, valor);
            statement.setInt(4, quantidade);

            statement.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (SQLException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
