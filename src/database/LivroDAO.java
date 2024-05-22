package database;

import models.Database.DatabaseManager;
import models.Livro.Livro;

import java.util.Date;

public class LivroDAO {
    @Override
    public void criarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var livro = new Livro(titulo, autor, editora, sinopse, paginas, id_livro_categoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro, id_livro_status);
               session.persist(livro);
            });
            System.out.println("Livro criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar livro: " + e.getMessage());
        }
    }

    @Override
    public void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
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
            });
            System.out.println("Livro editado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao editar livro: " + e.getMessage());
        }
    }

    @Override
    public void excluirLivro(int idLivro) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var livro = session.get(Livro.class, idLivro);
                session.delete(livro);
            });
            System.out.println("Livro excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }
}
