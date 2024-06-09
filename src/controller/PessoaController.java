package controller;

import models.Pessoa.Pessoa;

import java.util.ArrayList;
import java.util.Date;

public interface PessoaController {
    void cadastrarPessoa(String nome,
                         String cpf,
                         String email,
                         String telefone,
                         String logradouro,
                         String cidade,
                         String cep,
                         Date dataCadastro,
                         Date dataNascimento,
                         String uf,
                         String senha);

    void editarPessoa(int idPessoa, String novoNome, String novoCpf, String novoEmail, String novoTelefone, String novoLogradouro, String novaCidade, String novoCep, Date novaDataCadastro, Date novaDataNascimento, String novoUf, String novaSenha);

    Pessoa pesquisarPessoa(int idPessoa, String nome, String cpf, String telefone);

    ArrayList pesquisarPessoas();

    boolean login(String cpf, String senha);
}

