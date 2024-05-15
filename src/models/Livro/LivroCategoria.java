package models.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroCategoria {
    private int idLivroCategoria;
    private String descricao;

    private static List<LivroCategoria> categorias = new ArrayList<>();

    public LivroCategoria(String descricao) {
        this.descricao = descricao;
    }


    public static String getDescricao(int idLivroCategoria) {
        for (LivroCategoria categoria : categorias) {
            if (categoria.idLivroCategoria == idLivroCategoria) {
                return categoria.descricao;
            }
        }
        return null;
    }

    public static void criarCategoria(int idLivroCategoria, String descricao) {
        LivroCategoria novaCategoria = new LivroCategoria(descricao);
        categorias.add(novaCategoria);
    }

    public static List<LivroCategoria> listarCategorias() {
        return categorias;
    }

    public static List<String> listarDescricaoCategorias(List<LivroCategoria> listaCategorias) {
        List<String> descricaoCategorias = new ArrayList<>();
        for (LivroCategoria categoria : listaCategorias) {
            descricaoCategorias.add(getDescricao(categoria.idLivroCategoria));
        }
        return descricaoCategorias;
    }

    public static int getIdCategoria(LivroCategoria categoria) {
        for (LivroCategoria cat : categorias) {
            if (cat.idLivroCategoria == categoria.idLivroCategoria) {
                return cat.idLivroCategoria;
            }
        }
        return 0;
    }

  public static LivroCategoria buscarCategoriaPorDescricao(String descricao) {
        for (LivroCategoria categoria : categorias) {
            if (getDescricao(categoria.idLivroCategoria).equals(descricao)) {
                return categoria;
            }
        }
        return null;
    }
}
