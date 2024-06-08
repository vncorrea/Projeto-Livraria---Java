package models.Database;

import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

public class LivroDAO implements LivroDatabase {
    @Override
    public void criarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Livro livro = new Livro(titulo, autor, editora, sinopse, paginas, id_livro_categoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro, id_livro_status);
            session.save(livro);

            transaction.commit();
            System.out.println("Livro criado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao criar livro: ");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Livro livro = session.get(Livro.class, idLivro);
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setEditora(novaEditora);
            livro.setSinopse(novaSinopse);
            livro.setPaginas(novasPaginas);
            livro.setIdLivroCategoria(novaCategoria);
            livro.setIsbn(novoIsbn);
            livro.setPrazoEmprestimo(novoPrazoEmprestimo);
            livro.setDataPublicacao(novaDataPublicacao);
            livro.setIdLivroStatus(novoStatus);

            transaction.commit();
            System.out.println("Livro editado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao criar livro: ");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void excluirLivro(int idLivro) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            Livro livro = session.get(Livro.class, idLivro);
            session.delete(livro);

            transaction.commit();
            System.out.println("Livro excluído com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao criar livro: ");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // No método pesquisarLivro na classe LivroDAO
    @Override
    public ArrayList<Livro> pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            livros = (ArrayList<Livro>) DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                Query<Livro> query = session.createQuery("from Livro where titulo = :titulo or autor = :autor or isbn = :isbn", Livro.class);
                query.setParameter("titulo", titulo);
                query.setParameter("autor", autor);
                query.setParameter("isbn", isbn);
                return query.getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return livros;
    }

    @Override
    public void criarCategoria(String descricao) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            LivroCategoria categoria = new LivroCategoria(descricao);
            session.save(categoria);

            transaction.commit();
            System.out.println("Categoria criada com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Erro ao criar categoria: ");
            e.printStackTrace();
        }
    }

    @Override
    public void criarStatus(String descricao) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DatabaseManager.getDatabaseSessionFactory().openSession();
            transaction = session.beginTransaction();

            LivroStatus status = new LivroStatus(descricao);
            session.save(status);

            transaction.commit();
            System.out.println("Status criada com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Erro ao criar Status: ");
            e.printStackTrace();
        }
    }

    @Override
    public List<LivroCategoria> pesquisarCategorias() {
        List<LivroCategoria> categorias = new ArrayList<>();
        try {
            categorias = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                Query<LivroCategoria> query = session.createQuery("from LivroCategoria", LivroCategoria.class);
                return query.getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categorias;
    }

    @Override
    public List<LivroStatus> pesquisarStatus() {
        List<LivroStatus> status = new ArrayList<>();
        try {
            status = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                Query<LivroStatus> query = session.createQuery("from LivroStatus", LivroStatus.class);
                return query.getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
