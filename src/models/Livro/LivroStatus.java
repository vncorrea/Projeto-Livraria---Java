package models.Livro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "LivroStatus")
public class LivroStatus {
    @Id
    @GeneratedValue
    private int idLivroStatus;
    private String descricao;

    private static List<LivroStatus> status = new ArrayList<>();

    public LivroStatus(String descricao) {
        this.descricao = descricao;
    }

    public LivroStatus() {

    }

    public static String getStatus(int idLivroStatus) {
        for (LivroStatus livroStatus : status) {
            if (livroStatus.idLivroStatus == idLivroStatus) {
                return livroStatus.descricao;
            }
        }
        return null;
    }

    public int getIdLivroStatus() {
        return idLivroStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<LivroStatus> listarStatus() {
        return status;
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

    public static List<String> listarDescricaoStatus(List<LivroStatus> status) {
        return status.stream()
                .map(LivroStatus::getDescricao)
                .collect(Collectors.toList());
    }

    public static int getIdStatus(String descricao) {
        for (LivroStatus status : status) {
            if (status.getDescricao().equals(descricao)) {
                return status.idLivroStatus;
            }
        }
        return 0;
    }
}
