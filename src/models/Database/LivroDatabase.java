package models.Database;

import java.util.Date;

public interface LivroDatabase {
    void criarLivro(String titulo, String autor, String editora, String sinopse, int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status);

    void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora, String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn, int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus);

    void excluirLivro(int idLivro);

    void pesquisarLivro(String titulo, String autor, String categoria, String isbn);
}
