package controller;

import database.LivroDAO;
import models.Database.DatabaseManager;
import views.CadastroLivroView;

import java.util.Date;

public class LivroControllerImpl implements LivroController {
    private final CadastroLivroView cadastroLivroView;
    private final LivroDAO livroDAO;
    public LivroControllerImpl(CadastroLivroView cadastroLivroView, LivroDAO livroDAO) {
        this.cadastroLivroView = cadastroLivroView;
        this.livroDAO = livroDAO;
    }
    public void cadastrarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status) {
        DatabaseManager.criarLivro(titulo, autor, editora, sinopse, paginas, id_livro_categoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro, id_livro_status);
    }

    public void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus) {
        DatabaseManager.editarLivro(idLivro, novoTitulo, novoAutor, novaEditora, novaSinopse, novasPaginas, novaCategoria, novoIsbn, novoPrazoEmprestimo, novaDataPublicacao, novoStatus);
    }

    public void excluirLivro(int idLivro) {
        DatabaseManager.excluirLivro(idLivro);
    }

    public void pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
        DatabaseManager.pesquisarLivro(titulo, autor, categoria, isbn);
    }
}
