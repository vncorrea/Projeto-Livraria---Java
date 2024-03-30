package models.Pessoa;

import java.util.Date;

public class Colaborador extends Pessoa {

    private String cargo;
    private Date dataRegistro;
    private String pis;
    private String rg;


    public Colaborador(int idPessoa, String nome, String cpf, String email, String telefone, String logradouro, String cidade, String estado, String cep, Date dataCadastro, Date dataNascimento, String uf, String cargo, Date dataRegistro, String pis, String rg, String senha) {
        super(idPessoa, nome, cpf, email, telefone, logradouro, cidade, estado, cep, dataCadastro, dataNascimento, uf, senha);
        this.cargo = cargo;
        this.dataRegistro = dataRegistro;
        this.pis = pis;
        this.rg = rg;
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