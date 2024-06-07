package entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
public class Encomenda {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idencomenda")
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
    @Column(name = "tipoLeite")
    private String tipoLeite;

    @Basic
    @Column(name = "idproduto")
    private int idproduto;

    // Getters and Setters
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

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
}
