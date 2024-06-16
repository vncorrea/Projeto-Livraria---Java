package models.Database;

import models.Livro.LivroStatus;
import models.Pessoa.Colaborador;
import models.Pessoa.Pessoa;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;

public class PessoaDAO implements PessoaDatabase {

    @Override
    public void cadastrarPessoa(String nome, String cpf, String email, String telefone, String logradouro, String cidade, String cep, Date dataCadastro, Date dataNascimento, String uf, String senha, boolean colaborador, String cargo, Date dataRegistro, String pis, String rg, boolean administrador) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            dataCadastro = new Date();

            Pessoa pessoa;

            if (colaborador) {
                pessoa = new Colaborador(nome, cpf, email, telefone, logradouro, cidade, cep, dataCadastro, dataNascimento, uf, null,
                        null,
                        null,
                        null,
                        senha,
                        administrador);
            } else {
                pessoa = new Pessoa(nome, cpf, email, telefone, logradouro, cidade, cep, dataCadastro, dataNascimento, uf, senha);
            }

            System.out.println(pessoa);

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
    public void editarPessoa(int idPessoa, String novoNome, String novoCpf, String novoEmail, String novoTelefone, String novoLogradouro, String novaCidade, String novoCep, Date novaDataCadastro, Date novaDataNascimento, String novoUf, String novaSenha, boolean colaborador, String novoCargo, Date novaDataRegistro, String novoPis, String novoRg, boolean administrador) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Pessoa pessoa = session.get(Pessoa.class, idPessoa);

            System.out.println(!(pessoa instanceof Colaborador));
            System.out.println(colaborador);

            if (colaborador && !(pessoa instanceof Colaborador)) {
                session.delete(pessoa);
                transaction.commit();

                transaction = session.beginTransaction();
                Colaborador novoColaborador = new Colaborador(novoNome, novoCpf, novoEmail, novoTelefone, novoLogradouro, novaCidade, novoCep, novaDataCadastro, novaDataNascimento, novoUf, null,
                        null,
                        null,
                        null,
                        novaSenha,
                        administrador);

                novoColaborador.setIdPessoa(pessoa.getIdPessoa());
                pessoa = novoColaborador;
                session.save(pessoa);
            } else {
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

                if (pessoa instanceof Colaborador pessoaColaborador) {
                    pessoaColaborador.setCargo(novoCargo);
                    pessoaColaborador.setDataRegistro(novaDataRegistro);
                    pessoaColaborador.setPis(novoPis);
                    pessoaColaborador.setRg(novoRg);
                    pessoaColaborador.setAdministrador(administrador);
                }
            }

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
                if (idPessoa != 0) {
                    query = session.createQuery("from Pessoa where idPessoa = :idPessoa", Pessoa.class);
                    query.setParameter("idPessoa", idPessoa);
                } else if (!nome.equals("")) {
                    query = session.createQuery("from Pessoa where lower(nome) like :nome", Pessoa.class);
                    query.setParameter("nome", "%" + nome.toLowerCase() + "%");
                } else if (!cpf.equals("")) {
                    query = session.createQuery("from Pessoa where cpf = :cpf", Pessoa.class);
                    query.setParameter("cpf", cpf);
                } else {
                    query = session.createQuery("from Pessoa where telefone = :telefone", Pessoa.class);
                    query.setParameter("telefone", telefone);
                }

                return query.getSingleResult();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    @Override
    public ArrayList pesquisarPessoas(String nome, String cpf, String telefone) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query<Pessoa> query;
            if (!nome.equals("")) {
                query = session.createQuery("from Pessoa where lower(nome) like :nome", Pessoa.class);
                query.setParameter("nome", "%" + nome.toLowerCase() + "%");
            } else if (!cpf.equals("")) {
                query = session.createQuery("from Pessoa where cpf = :cpf", Pessoa.class);
                query.setParameter("cpf", cpf);
            } else if (!cpf.equals("")) {
                query = session.createQuery("from Pessoa where telefone = :telefone", Pessoa.class);
                query.setParameter("telefone", telefone);
            } else {
                query = session.createQuery("from Pessoa", Pessoa.class);
            }

            ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) query.getResultList();

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

    @Override
    public boolean login(String cpf, String senha) {
        Session session = null;
        Transaction transaction = null;
        boolean loginSuccessful = false;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query<Pessoa> query = session.createQuery("from Pessoa where cpf = :cpf and senha = :senha", Pessoa.class);
            query.setParameter("cpf", cpf);
            query.setParameter("senha", senha);

            Pessoa pessoa = query.uniqueResult();

            transaction.commit();

            if (pessoa != null) {
                loginSuccessful = true;
                System.out.println("Login efetuado com sucesso!");
            } else {
                System.out.println("CPF ou senha incorretos!");
            }

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

        return loginSuccessful;
    }

    @Override
    public void excluirPessoa(int idPessoa) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Pessoa pessoa = session.get(Pessoa.class, idPessoa);
            session.delete(pessoa);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
