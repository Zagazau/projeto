package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipopagamento")
public class Tipopagamento {
    @Id
    @Column(name = "idtipopag", nullable = false)
    private Integer id;

    @Column(name = "nometipopag", nullable = false, length = 100)
    private String nometipopag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNometipopag() {
        return nometipopag;
    }

    public void setNometipopag(String nometipopag) {
        this.nometipopag = nometipopag;
    }

}