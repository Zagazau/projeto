package entity;

import jakarta.persistence.*;

@Entity
public class Fornecedor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idfornecedor")
    private int idfornecedor;

    @Basic
    @Column(name = "codpostal")
    private String codpostal;

    @Basic
    @Column(name = "nome")
    private String nome;

    @Basic
    @Column(name = "telefone")
    private int telefone;

    @Basic
    @Column(name = "rua")
    private String rua;

    @Basic
    @Column(name = "numporta")
    private int numporta;

    @Basic
    @Column(name = "nif")
    private int nif;

    @Basic
    @Column(name = "username", unique = true)
    private String username;

    @Basic
    @Column(name = "senha")
    private String senha;


    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumporta() {
        return numporta;
    }

    public void setNumporta(int numporta) {
        this.numporta = numporta;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
