package models.Livro;

import java.util.Date;

public class LivroStatus {
    private int idLivroStatus;
    private String descricao;

    public LivroStatus(int idLivroStatus, String descricao) {
        this.idLivroStatus = idLivroStatus;
        this.descricao = descricao;
    }

    public int getIdLivroStatus() {
        return idLivroStatus;
    }

    public String getDescricao() {
        return descricao;
    }
}
