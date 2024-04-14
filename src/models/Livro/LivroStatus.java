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

    public static String getStatus(LivroStatus status) {
        return status.descricao;
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

    public static List<LivroStatus> listarStatus() {
        return status;
    }

    public static List<String> listarDescricaoStatus(List<LivroStatus> listaStatus) {
        List<String> descricaoStatus = new ArrayList<>();
        for (LivroStatus status : listaStatus) {
            descricaoStatus.add(status.getDescricao());
        }
        return descricaoStatus;
    }

    public static LivroStatus buscarStatusPorId(int idLivroStatus) {
        for (LivroStatus status : status) {
            if (status.getIdLivroStatus() == idLivroStatus) {
                return status;
            }
        }
        return null;
    }

    public static LivroStatus buscarStatusPorDescricao(String descricao) {
        for (LivroStatus status : status) {
            if (status.getDescricao().equals(descricao)) {
                return status;
            }
        }
        return null;
    }
}
