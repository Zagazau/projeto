package entity;

import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
public class Pedido {
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "idpedido")
    private int idpedido;

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idcliente")
    private int idcliente;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "quantidade")
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "data")
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
