package models.Livro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static int getIdCategoria(String descricao) {
        for (LivroCategoria categoria : categorias) {
            if (categoria.getDescricao().equals(descricao)) {
                return categoria.idLivroCategoria;
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

    public static List<String> listarDescricaoCategorias(List<LivroCategoria> categorias) {
        return categorias.stream()
                .map(LivroCategoria::getDescricao)
                .collect(Collectors.toList());
    }
}
