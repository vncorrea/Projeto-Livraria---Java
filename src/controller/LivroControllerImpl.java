package controller;


import models.Database.LivroDatabase;
import views.CadastroLivroView;
import views.CadastroLivroViewImpl;
import views.PesquisaLivroView;

import java.util.Date;

public class LivroControllerImpl implements LivroController {
    private CadastroLivroView cadastroLivroView;
    private PesquisaLivroView pesquisaLivroView;
    private final LivroDatabase livroDatabase;

    @Override
    public void setCadastroView(CadastroLivroView view) {
        this.cadastroLivroView = view;
    }

    @Override
    public void setPesquisaView(PesquisaLivroView view) {
        this.pesquisaLivroView = view;
    }

    public LivroControllerImpl(LivroDatabase livroDatabase) {
        this.livroDatabase = livroDatabase;
    }

    public void cadastrarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status) {
        livroDatabase.criarLivro(titulo, autor, editora, sinopse, paginas, id_livro_categoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro, id_livro_status);
    }

    public void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus) {
        livroDatabase.editarLivro(idLivro, novoTitulo, novoAutor, novaEditora, novaSinopse, novasPaginas, novaCategoria, novoIsbn, novoPrazoEmprestimo, novaDataPublicacao, novoStatus);
    }

    public void excluirLivro(int idLivro) {
        livroDatabase.excluirLivro(idLivro);
    }

    public void pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
        livroDatabase.pesquisarLivro(titulo, autor, categoria, isbn);
    }
}
