package models.Database;

import models.Livro.Livro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            Session session = DatabaseManager.getDatabaseSessionFactory().openSession();
            session.beginTransaction();

            String query = "FROM Livro WHERE titulo = :titulo AND autor = :autor AND idLivroCategoria = :categoria AND isbn = :isbn";
            List result = session.createQuery(query)
                    .setParameter("titulo", titulo)
                    .setParameter("autor", autor)
                    .setParameter("categoria", Integer.parseInt(categoria))
                    .setParameter("isbn", isbn)
                    .list();

            System.out.println(result);

            for (Object o : result) {
                Livro livro = (Livro) o;
                livros.add(livro);
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar livro: ");
            e.printStackTrace();
        } finally {
            DatabaseManager.getDatabaseSessionFactory().close();
        }

        return livros;
    }
}
