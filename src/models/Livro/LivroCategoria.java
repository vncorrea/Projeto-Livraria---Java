package models.Livro;

import java.util.Date;

public class LivroCategoria {
    private int idLivroCategoria;
    private String descricao;

    public LivroCategoria(int idLivroCategoria, String descricao) {
        this.idLivroCategoria = idLivroCategoria;
        this.descricao = descricao;
    }

    public int getIdLivroCategoria() {
        return idLivroCategoria;
    }

    public String getDescricao() {
        return descricao;
    }
}
