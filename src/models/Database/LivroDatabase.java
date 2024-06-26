package models.Database;

import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface LivroDatabase {
    void criarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status);

    void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus);

    void excluirLivro(int idLivro);

    ArrayList pesquisarLivros(String titulo, String autor, String categoria, String isbn);

    void criarCategoria(String descricao);

    void criarStatus(String descricao);

    List pesquisarStatus();

    List pesquisarCategorias();

    LivroCategoria pesquisarCategoria(int idLivroCategoria, String descricao);

    Livro pesquisarLivro(int idLivro);

    LivroStatus pesquisarUmStatus(int idLivroStatus, String descricao);

    void emprestarLivro(int idLivro, int idPessoa, Date dataEmprestimo, Date dataDevolucao, String observacao);

    void devolverLivro(int idLivro);
}