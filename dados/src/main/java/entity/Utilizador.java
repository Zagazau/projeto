package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "utilizador")
public class Utilizador {
    @Id
    @Column(name = "idutilizador", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codpostal", nullable = false)
    private Codigopostal codpostal;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private Integer telefone;

    @Column(name = "rua", nullable = false, length = 100)
    private String rua;

    @Column(name = "numporta", nullable = false)
    private Integer numporta;

    @Column(name = "nif", nullable = false)
    private Integer nif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipoutilizador")
    private Tipoutilizador idtipoutilizador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Codigopostal getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(Codigopostal codpostal) {
        this.codpostal = codpostal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumporta() {
        return numporta;
    }

    public void setNumporta(Integer numporta) {
        this.numporta = numporta;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public Tipoutilizador getIdtipoutilizador() {
        return idtipoutilizador;
    }

    public void setIdtipoutilizador(Tipoutilizador idtipoutilizador) {
        this.idtipoutilizador = idtipoutilizador;
    }

}