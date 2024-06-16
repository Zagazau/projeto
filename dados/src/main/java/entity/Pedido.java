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
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;


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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
