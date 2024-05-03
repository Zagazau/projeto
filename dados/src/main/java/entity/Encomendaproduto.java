package entity;

import jakarta.persistence.*;

@Entity
public class Encomendaproduto {
    @Basic
    @Column(name = "idencomenda")
    private int idencomenda;
    @Basic
    @Column(name = "idproduto")
    private int idproduto;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public int getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(int idencomenda) {
        this.idencomenda = idencomenda;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Encomendaproduto that = (Encomendaproduto) o;

        if (idencomenda != that.idencomenda) return false;
        if (idproduto != that.idproduto) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idencomenda;
        result = 31 * result + idproduto;
        result = 31 * result + id;
        return result;
    }
}
