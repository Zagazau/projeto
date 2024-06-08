package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Faturavenda {
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "idfaturav")
    private int idfaturav;

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idcliente")
    private int idcliente;

    public int getIdfaturav() {
        return idfaturav;
    }

    public void setIdfaturav(int idfaturav) {
        this.idfaturav = idfaturav;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idpedido")
    private int idpedido;

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idtipopag")
    private int idtipopag;

    public int getIdtipopag() {
        return idtipopag;
    }

    public void setIdtipopag(int idtipopag) {
        this.idtipopag = idtipopag;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "valor")
    private float valor;

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "quantidade")
    private int quantidade;

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
}
