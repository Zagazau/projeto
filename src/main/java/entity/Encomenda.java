package entity;

import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
public class Encomenda {
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "idencomenda")
    private int idencomenda;

    public int getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(int idencomenda) {
        this.idencomenda = idencomenda;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idfornecedor")
    private int idfornecedor;

    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "quantidade")
    private float quantidade;

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
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
