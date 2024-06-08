package controller;
import models.Livro.LivroCategoria;
import views.CadastroLivroView;
import views.PesquisaLivroView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface LivroController {
    void cadastrarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status);

    void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus);

    void excluirLivro(int idLivro);

    ArrayList pesquisarLivro(String titulo, String autor, String categoria, String isbn);

    void setCadastroView(CadastroLivroView view);

    void setPesquisaView(PesquisaLivroView view);

    List pesquisarCategorias();
    List pesquisarStatus();

    LivroCategoria pesquisarCategoria(int idLivroCategoria);
}
