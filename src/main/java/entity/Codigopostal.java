package entity;

import jakarta.persistence.Entity;

@Entity
public class Codigopostal {
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "codpostal")
    private String codpostal;

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "localidade")
    private String localidade;

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
