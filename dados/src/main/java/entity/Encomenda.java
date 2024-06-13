package entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idencomenda;

    @Basic
    @Column(name = "idfornecedor")
    private int idfornecedor;

    @Basic
    @Column(name = "quantidade")
    private float quantidade;

    @Basic
    @Column(name = "data")
    private Date data;

    @Basic
    @Column(name = "tipoleite")
    private String tipoLeite;

    @Basic
    @Column(name = "valor")
    private Float valor;

    @Basic
    @Column(name = "idproduto")
    private Integer idproduto;

    @OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL)
    private List<Faturacompra> faturacompras;

    public int getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(int idencomenda) {
        this.idencomenda = idencomenda;
    }

    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipoLeite() {
        return tipoLeite;
    }

    public void setTipoLeite(String tipoLeite) {
        this.tipoLeite = tipoLeite;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public List<Faturacompra> getFaturacompras() {
        return faturacompras;
    }

    public void setFaturacompras(List<Faturacompra> faturacompras) {
        this.faturacompras = faturacompras;
    }
}
