package models.Livro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LivroCategoria")
public class LivroCategoria {
    @Id
    @GeneratedValue
    private int idLivroCategoria;
    private String descricao;

    private static List<LivroCategoria> categorias = new ArrayList<>();

    public LivroCategoria(String descricao) {
        this.descricao = descricao;
    }

    public LivroCategoria() {

    }

    public String getDescricao() {
        return descricao;
    }

    public static void criarCategoria(int idLivroCategoria, String descricao) {
        LivroCategoria novaCategoria = new LivroCategoria(descricao);
        categorias.add(novaCategoria);
    }

    public static List<LivroCategoria> listarCategorias() {
        return categorias;
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
            if (categoria.getDescricao().equals(descricao)) {
                return categoria;
            }
        }
        return null;
    }
}
