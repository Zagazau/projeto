package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipoutilizador")
public class Tipoutilizador {
    @Id
    @Column(name = "idtipoutilizador", nullable = false)
    private Integer id;

    @Column(name = "nometipoutil", nullable = false, length = 100)
    private String nometipoutil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNometipoutil() {
        return nometipoutil;
    }

    public void setNometipoutil(String nometipoutil) {
        this.nometipoutil = nometipoutil;
    }

}