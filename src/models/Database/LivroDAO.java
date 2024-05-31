package models.Database;

import models.Livro.Livro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

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

    @Override
    public void pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
        try {
            Session session = DatabaseManager.getDatabaseSessionFactory().openSession();
            session.beginTransaction();

            String query = "SELECT * FROM Livro WHERE titulo = :titulo AND autor = :autor AND id_livro_categoria = :categoria AND isbn = :isbn";
            session.createSQLQuery(query)
                    .setParameter("titulo", titulo)
                    .setParameter("autor", autor)
                    .setParameter("categoria", categoria)
                    .setParameter("isbn", isbn)
                    .list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar livro: ");
            e.printStackTrace();
        } finally {
            DatabaseManager.getDatabaseSessionFactory().close();
        }
    }
}
