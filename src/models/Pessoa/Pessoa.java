package models.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import models.Livro.LivroStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Pessoa")
public class Pessoa {
    @Id
    @GeneratedValue
    protected int idPessoa;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone;
    protected String logradouro;
    protected String cidade;
    protected String estado;
    protected String cep;

    protected String uf;

    protected Date dataCadastro;

    protected Date dataNascimento;

    private String senha;

    public Pessoa(String nome,
                  String cpf,
                  String email,
                  String telefone,
                  String logradouro,
                  String cidade,
                  String cep,
                  Date dataCadastro,
                  Date dataNascimento,
                  String uf,
                  String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.cep = cep;
        this.dataCadastro = dataCadastro;
        this.dataNascimento = dataNascimento;
        this.uf = uf;
        this.senha = senha;
    }

    public Pessoa() {

    }

    public Pessoa(String nome, String cpf, String email, String telefone, String logradouro, String cidade, String estado, String cep, Date dataCadastro, Date dataNascimento, String uf, String senha) {
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static List<String> listarNomePessoas(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(Pessoa::getNome)
                .collect(Collectors.toList());
    }
}
