package models.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroCategoria {
    private int idLivroCategoria;
    private String descricao;

    private static List<LivroCategoria> categorias = new ArrayList<>();

    public LivroCategoria(int idLivroCategoria, String descricao) {
        this.idLivroCategoria = idLivroCategoria;
        this.descricao = descricao;
    }


    public static String getDescricao(LivroCategoria categoria) {
        return categoria.descricao;
    }

    public static void criarCategoria(int idLivroCategoria, String descricao) {
        LivroCategoria novaCategoria = new LivroCategoria(idLivroCategoria, descricao);
        categorias.add(novaCategoria);
    }

    public static List<LivroCategoria> listarCategorias() {
        return categorias;
    }

    public static List<String> listarDescricaoCategorias(List<LivroCategoria> listaCategorias) {
        List<String> descricaoCategorias = new ArrayList<>();
        for (LivroCategoria categoria : listaCategorias) {
            descricaoCategorias.add(getDescricao(categoria));
        }
        return descricaoCategorias;
    }

  public static LivroCategoria buscarCategoriaPorDescricao(String descricao) {
        for (LivroCategoria categoria : categorias) {
            if (getDescricao(categoria).equals(descricao)) {
                return categoria;
            }
        }
        return null;
    }
}
