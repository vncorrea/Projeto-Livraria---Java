package models.Database;

import models.Livro.Livro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LivroDatabase {
    private static List<Livro> livros = new ArrayList<>();

    public static Livro criarLivro(int idLivro, String titulo, String autor, String editora, String sinopse,
                                   int paginas, int idLivroStatus, int idLivroCategoria, String isbn,
                                   int prazoEmprestimo, Date dataPublicacao, Date dataCadastro) {
        Livro novoLivro = new Livro(idLivro, titulo, autor, editora, sinopse, paginas, idLivroStatus,
                idLivroCategoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro);

        livros.add(novoLivro);
        return novoLivro;
    }

    public static void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora,
                                   String novaSinopse, int novasPaginas, int novoIdLivroStatus,
                                   int novoIdLivroCategoria, String novoIsbn, int novoPrazoEmprestimo,
                                   Date novaDataPublicacao) {
        for (Livro livro : livros) {
            if (livro.getIdLivro() == idLivro) {
                livro.setTitulo(novoTitulo);
                livro.setAutor(novoAutor);
                livro.setEditora(novaEditora);
                livro.setSinopse(novaSinopse);
                livro.setPaginas(novasPaginas);
                livro.setIdLivroStatus(novoIdLivroStatus);
                livro.setIdLivroCategoria(novoIdLivroCategoria);
                livro.setIsbn(novoIsbn);
                livro.setPrazoEmprestimo(novoPrazoEmprestimo);
                livro.setDataPublicacao(novaDataPublicacao);
                break;
            }
        }
    }

    public static void excluirLivro(int idLivro) {
        Livro livroParaRemover = null;
        for (Livro livro : livros) {
            if (livro.getIdLivro() == idLivro) {
                livroParaRemover = livro;
                break;
            }
        }
        if (livroParaRemover != null) {
            livros.remove(livroParaRemover);
        }
    }

    public static List<Livro> pesquisarLivro(String titulo, String autor, String editora, String isbn) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : livros) {
            if (titulo != null && !titulo.isEmpty() && !Objects.equals(livro.getTitulo(), titulo)) {
                continue;
            }
            if (autor != null && !autor.isEmpty() && !Objects.equals(livro.getAutor(), autor)) {
                continue;
            }
            if (editora != null && !editora.isEmpty() && !Objects.equals(livro.getEditora(), editora)) {
                continue;
            }
            if (isbn != null && !isbn.isEmpty() && !Objects.equals(livro.getIsbn(), isbn)) {
                continue;
            }


            livrosEncontrados.add(livro);
        }
        return livrosEncontrados;
    }
}
