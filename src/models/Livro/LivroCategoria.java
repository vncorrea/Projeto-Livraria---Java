package models.Livro;

import java.util.ArrayList;
import java.util.Date;

public class LivroCategoria {
    private int idLivroCategoria;
    private String descricao;

    public ArrayList <LivroCategoria> categorias = new ArrayList<>();

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

    public void setIdLivroCategoria(int idLivroCategoria) {
        this.idLivroCategoria = idLivroCategoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void adicionarCategoria(LivroCategoria categoria) {
        categorias.add(categoria);
    }
}
