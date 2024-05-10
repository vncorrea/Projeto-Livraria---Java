package models.Database;

import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LivroDatabase {
    private static List<Livro> livros = new ArrayList<>();

    public static Livro criarLivro(int idLivro, String titulo, String autor, String editora, String sinopse,
                                   int paginas, int id_livro_categoria, String isbn, int prazoEmprestimo, Date dataPublicacao, Date dataCadastro, int id_livro_status) {

        Livro novoLivro = new Livro(idLivro, titulo, autor, editora, sinopse, paginas,
                id_livro_categoria, isbn, prazoEmprestimo, dataPublicacao, dataCadastro, id_livro_status);
        livros.add(novoLivro);
        return novoLivro;
    }

    public static void editarLivro(int idLivro, String novoTitulo, String novoAutor, String novaEditora,
                                   String novaSinopse, int novasPaginas, int novaCategoria, String novoIsbn,
                                   int novoPrazoEmprestimo, Date novaDataPublicacao, int novoStatus) {
        for (Livro livro : livros) {
            if (livro.getIdLivro() == idLivro) {
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

    public static List<Livro> pesquisarLivro(String titulo, String autor, String categoria, String isbn) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : livros) {
            if (titulo != null && !titulo.isEmpty() && livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            } else if (autor != null && !autor.isEmpty() && livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                livrosEncontrados.add(livro);
            } else if (categoria != null && !categoria.isEmpty() && LivroCategoria.buscarCategoriaPorDescricao(categoria) != null) {
                livrosEncontrados.add(livro);
            } else if (isbn != null && !isbn.isEmpty() && livro.getIsbn().toLowerCase().contains(isbn.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }

        return livrosEncontrados;
    }

    public static Livro buscarLivroPorId(int idLivro) {
        for (Livro livro : livros) {
            if (livro.getIdLivro() == idLivro) {
                return livro;
            }
        }
        return null;
    }
}
