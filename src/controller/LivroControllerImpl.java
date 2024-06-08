package controller;


import models.Database.LivroDatabase;
import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import views.CadastroLivroView;
import views.EmprestimoLivroView;
import views.PesquisaLivroView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivroControllerImpl implements LivroController {
    private CadastroLivroView cadastroLivroView;
    private PesquisaLivroView pesquisaLivroView;
    private EmprestimoLivroView emprestimoLivroView;
    private final LivroDatabase livroDatabase;

    @Override
    public void setCadastroView(CadastroLivroView view, int idLivro) {
        this.cadastroLivroView = view;
    }

    @Override
    public void setPesquisaView(PesquisaLivroView view) {
        this.pesquisaLivroView = view;
    }

    @Override
    public void setEmprestimoLivroView(EmprestimoLivroView view, int idLivro) {
        this.emprestimoLivroView = view;
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

    public ArrayList pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
       return livroDatabase.pesquisarLivros(titulo, autor, categoria, isbn);
    }

    public List pesquisarCategorias() {
        return livroDatabase.pesquisarCategorias();
    }

    public LivroCategoria pesquisarCategoria(int idLivroCategoria, String descricao) {
        return livroDatabase.pesquisarCategoria(idLivroCategoria, descricao);
    }

    public Livro pesquisarLivro(int idLivro) {
        return livroDatabase.pesquisarLivro(idLivro);
    }

    public LivroStatus pesquisarUmStatus(int idLivroStatus, String descricao) {
        return livroDatabase.pesquisarUmStatus(idLivroStatus, descricao);
    }
    public List pesquisarStatus() {
        return livroDatabase.pesquisarStatus();
    }
}
