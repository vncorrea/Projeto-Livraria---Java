package controller;
import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import views.CadastroLivro.CadastroLivroView;
import views.EmprestimoLivro.EmprestimoLivroView;
import views.PesquisaLivro.PesquisaLivroView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface LivroController {
    void cadastrarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status);

    void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus);

    void excluirLivro(int idLivro);

    ArrayList pesquisarLivro(String titulo, String autor, String categoria, String isbn);

    void setCadastroView(CadastroLivroView view, int idLivro);

    void setPesquisaView(PesquisaLivroView view);
    void setEmprestimoLivroView(EmprestimoLivroView view, int idLivro);

    List pesquisarCategorias();
    List pesquisarStatus();

    LivroCategoria pesquisarCategoria(int idLivroCategoria, String descricao);
    Livro pesquisarLivro(int idLivro);

    LivroStatus pesquisarUmStatus(int idLivroStatus, String descricao);

    void emprestarLivro(int idLivro, int idPessoa, Date dataEmprestimo, Date dataDevolucao, String observacao);
    void devolverLivro(int idLivro);
}
