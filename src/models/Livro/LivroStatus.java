package models.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroStatus {
    private int idLivroStatus;
    private String descricao;

    private static List<LivroStatus> status = new ArrayList<>();

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

    public static void criarStatus(int idLivroStatus, String descricao) {
        LivroStatus novoStatus = new LivroStatus(idLivroStatus, descricao);
        status.add(novoStatus);
    }
}
