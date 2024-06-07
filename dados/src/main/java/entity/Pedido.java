package entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido")
    private int idpedido;

    @Basic
    @Column(name = "idcliente")
    private int idcliente;

    @Basic
    @Column(name = "quantidade")
    private int quantidade;

    @Basic
    @Column(name = "data")
    private Date data;

    @Basic
    @Column(name = "valor")
    private double valor;

    @Basic
    @Column(name = "idproduto")
    private int idproduto;

    // Getters and Setters

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
}
