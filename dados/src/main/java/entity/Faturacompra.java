package entity;

import jakarta.persistence.*;

@Entity
public class Faturacompra {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idfaturac")
    private int idfaturac;

    public int getIdfaturac() {
        return idfaturac;
    }

    public void setIdfaturac(int idfaturac) {
        this.idfaturac = idfaturac;
    }

    @Basic
    @Column(name = "idencomenda")
    private int idencomenda;

    public int getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(int idencomenda) {
        this.idencomenda = idencomenda;
    }

    @Basic
    @Column(name = "idtipopag")
    private int idtipopag;

    public int getIdtipopag() {
        return idtipopag;
    }

    public void setIdtipopag(int idtipopag) {
        this.idtipopag = idtipopag;
    }

    @Basic
    @Column(name = "valor")
    private float valor;

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "quantidade")
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
