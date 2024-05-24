package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipoproduto")
public class Tipoproduto {
    @Id
    @Column(name = "idtipoproduto", nullable = false)
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduto", nullable = false)
    private Produto idproduto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        this.idproduto = idproduto;
    }

}