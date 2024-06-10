package controller;

import models.Database.PessoaDatabase;
import models.Pessoa.Pessoa;

import java.util.ArrayList;
import java.util.Date;

public class PessoaControllerImpl implements PessoaController {
    private final PessoaDatabase pessoaDatabase;
    public PessoaControllerImpl(PessoaDatabase pessoaDatabase) {
        this.pessoaDatabase = pessoaDatabase;
    }

    @Override
    public void cadastrarPessoa(String nome, String cpf, String email, String telefone, String logradouro, String cidade, String cep, Date dataCadastro, Date dataNascimento, String uf, String senha, boolean colaborador, String cargo, Date dataRegistro, String pis, String rg, boolean administrador) {
        pessoaDatabase.cadastrarPessoa(nome, cpf, email, telefone, logradouro, cidade, cep, dataCadastro, dataNascimento, uf, senha, colaborador, cargo, dataRegistro, pis, rg, administrador);
    }
    @Override
    public void editarPessoa(int idPessoa, String novoNome, String novoCpf, String novoEmail, String novoTelefone, String novoLogradouro, String novaCidade, String novoCep, Date novaDataCadastro, Date novaDataNascimento, String novoUf, String novaSenha, boolean colaborador, String novoCargo, Date novaDataRegistro, String novoPis, String novoRg, boolean novoAdministrador) {
        pessoaDatabase.editarPessoa(idPessoa, novoNome, novoCpf, novoEmail, novoTelefone, novoLogradouro, novaCidade, novoCep, novaDataCadastro, novaDataNascimento, novoUf, novaSenha, colaborador, novoCargo, novaDataRegistro, novoPis, novoRg, novoAdministrador);
    }
    @Override
    public Pessoa pesquisarPessoa(int idPessoa, String nome, String cpf, String telefone) {
        return pessoaDatabase.pesquisarPessoa(idPessoa, nome, cpf, telefone);
    }
    @Override
    public ArrayList pesquisarPessoas(String nome, String cpf, String telefone) {
        return pessoaDatabase.pesquisarPessoas(nome, cpf, telefone);
    }
    @Override
    public boolean login(String cpf, String senha) {
       return pessoaDatabase.login(cpf, senha);
    }
    @Override
    public void excluirPessoa(int idPessoa) {
        pessoaDatabase.excluirPessoa(idPessoa);
    }
}
