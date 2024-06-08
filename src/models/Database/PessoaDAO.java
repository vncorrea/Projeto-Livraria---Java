package models.Database;

import models.Pessoa.Pessoa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class PessoaDAO implements PessoaDatabase {

    @Override
    public void cadastrarPessoa(String nome, String cpf, String email, String telefone, String logradouro, String cidade, String cep, Date dataCadastro, Date dataNascimento, String uf, String senha) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            dataCadastro = new Date();

            Pessoa pessoa = new Pessoa(nome, cpf, email, telefone, logradouro, cidade, cep, dataCadastro, dataNascimento, uf, senha);
            session.save(pessoa);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void editarPessoa(int idPessoa, String novoNome, String novoCpf, String novoEmail, String novoTelefone, String novoLogradouro, String novaCidade, String novoCep, Date novaDataCadastro, Date novaDataNascimento, String novoUf, String novaSenha) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Pessoa pessoa = session.get(Pessoa.class, idPessoa);

            pessoa.setNome(novoNome);
            pessoa.setCpf(novoCpf);
            pessoa.setEmail(novoEmail);
            pessoa.setTelefone(novoTelefone);
            pessoa.setLogradouro(novoLogradouro);
            pessoa.setCidade(novaCidade);
            pessoa.setCep(novoCep);
            pessoa.setDataNascimento(novaDataNascimento);
            pessoa.setUf(novoUf);
            pessoa.setSenha(novaSenha);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Pessoa pesquisarPessoa(int idPessoa, String nome, String cpf, String telefone) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Pessoa pessoa = session.get(Pessoa.class, idPessoa);

            if (pessoa == null) {
                pessoa = new Pessoa();
            }

            transaction.commit();
            return pessoa;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
