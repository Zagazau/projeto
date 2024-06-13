package entity;

import jakarta.persistence.*;

@Entity
public class Faturacompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfaturac")
    private int idfaturac;

    @ManyToOne
    @JoinColumn(name = "idencomenda")
    private Encomenda encomenda;

    @Basic
    @Column(name = "idtipopag")
    private int idtipopag;

    @Basic
    @Column(name = "valor")
    private float valor;

    @Basic
    @Column(name = "quantidade")
    private int quantidade;

    // Getters e Setters

    public int getIdfaturac() {
        return idfaturac;
    }

    public void setIdfaturac(int idfaturac) {
        this.idfaturac = idfaturac;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    public int getIdtipopag() {
        return idtipopag;
    }

    public void setIdtipopag(int idtipopag) {
        this.idtipopag = idtipopag;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
