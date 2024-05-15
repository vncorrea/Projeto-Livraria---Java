package models.Database;

import models.Livro.Livro;
import models.Livro.LivroCategoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class DatabaseManager {
    private static SessionFactory sessionFactory;

    public static void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void criarLivro(String titulo, String autor, String editora, String sinopse,
                                  int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var livro = new Livro(titulo, autor, editora, sinopse, paginas, id_livro_categoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro, id_livro_status);
            session.persist(livro);
            session.getTransaction().commit();
        }
    }

    public static void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora,
                                   String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn,
                                   int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var livro = session.get(Livro.class, idLivro);
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
            session.getTransaction().commit();
        }
    }

    public static void excluirLivro(int idLivro) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var livro = session.get(Livro.class, idLivro);
            session.delete(livro);
            session.getTransaction().commit();
        }
    }

    public static void pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("from Livro where titulo like :titulo and autor like :autor and categoria like :categoria and isbn like :isbn");
            query.setParameter("titulo", "%" + titulo + "%");
            query.setParameter("autor", "%" + autor + "%");
            query.setParameter("categoria", "%" + categoria + "%");
            query.setParameter("isbn", "%" + isbn + "%");
            var livros = query.list();
            session.getTransaction().commit();
        }
    }

    public static void buscarLivroPorId(int idLivro) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var livro = session.get(Livro.class, idLivro);
            session.getTransaction().commit();
        }
    }

    public static int criarLivroCategoria(String descricao) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var livroCategoria = new LivroCategoria(descricao);
            int id = (int) session.save(livroCategoria);
            session.getTransaction().commit();
            return id;
        }
    }
}