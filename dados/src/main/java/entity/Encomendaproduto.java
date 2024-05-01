package entity;

import jakarta.persistence.Entity;

@Entity
public class Encomendaproduto {
    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idencomenda")
    private int idencomenda;

    public int getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(int idencomenda) {
        this.idencomenda = idencomenda;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "idproduto")
    private int idproduto;

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
}
