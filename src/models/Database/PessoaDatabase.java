package models.Database;

import models.Pessoa.Pessoa;

import java.util.ArrayList;
import java.util.Date;

public interface PessoaDatabase {
    void cadastrarPessoa(String nome, String cpf, String email, String telefone, String logradouro, String cidade, String cep, Date dataCadastro, Date dataNascimento, String uf, String senha, boolean colaborador, String cargo, Date dataRegistro, String pis, String rg, boolean administrador);
    void editarPessoa(int idPessoa, String novoNome, String novoCpf, String novoEmail, String novoTelefone, String novoLogradouro, String novaCidade, String novoCep, Date novaDataCadastro, Date novaDataNascimento, String novoUf, String novaSenha, boolean colaborador, String novoCargo, Date novaDataRegistro, String novoPis, String novoRg, boolean Administrador);
    Pessoa pesquisarPessoa(int idPessoa, String nome, String cpf, String telefone);
    ArrayList pesquisarPessoas(String nome, String cpf, String telefone);
    boolean login(String cpf, String senha);
    void excluirPessoa(int idPessoa);
}
