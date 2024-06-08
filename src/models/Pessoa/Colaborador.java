package models.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "Colaborador")
public class Colaborador extends Pessoa {
    @Id
    @GeneratedValue

    private String cargo;
    private Date dataRegistro;
    private String pis;
    private String rg;


    public Colaborador(String nome, String cpf, String email, String telefone, String logradouro, String cidade, String estado, String cep, Date dataCadastro, Date dataNascimento, String uf, String cargo, Date dataRegistro, String pis, String rg, String senha) {
        super(nome, cpf, email, telefone, logradouro, cidade, estado, cep, dataCadastro, dataNascimento, uf, senha);
        this.cargo = cargo;
        this.dataRegistro = dataRegistro;
        this.pis = pis;
        this.rg = rg;
    }

    public Colaborador() {

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}