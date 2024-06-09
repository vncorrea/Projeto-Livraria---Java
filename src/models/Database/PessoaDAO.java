package models.Database;

import models.Livro.LivroStatus;
import models.Pessoa.Pessoa;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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
        Pessoa pessoa = null;
        try {
            pessoa = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                Query<Pessoa> query;
                if (idPessoa == 0) {
                    query = session.createQuery("from Pessoa where nome = :nome", Pessoa.class);
                    query.setParameter("nome", nome);
                } else {
                    query = session.createQuery("from Pessoa where idPessoa = :idPessoa", Pessoa.class);
                    query.setParameter("idPessoa", idPessoa);
                }
                return query.getSingleResult();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    @Override
    public ArrayList pesquisarPessoas() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) session.createQuery("FROM Pessoa").list();

            transaction.commit();
            return pessoas;
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
