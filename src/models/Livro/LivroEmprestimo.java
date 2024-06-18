package models.Livro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LivroEmprestimo")
public class LivroEmprestimo {
    @Id
    @GeneratedValue
    private int idLivroEmprestimo;
    private int idLivro;
    private int idPessoa;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private boolean multa;
    private String observacao;
    private boolean ativo;

    public LivroEmprestimo(int idLivro, int idPessoa, Date dataEmprestimo, Date dataDevolucao, boolean multa, String observacao, boolean ativo) {
        this.idLivro = idLivro;
        this.idPessoa = idPessoa;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.multa = multa;
        this.observacao = observacao;
        this.ativo = ativo;
    }

    private static List<LivroEmprestimo> livroEmprestimos = new ArrayList<>();

    public LivroEmprestimo() {

    }

    public int getIdLivroEmprestimo() {
        return idLivroEmprestimo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean getMulta() {
        return multa;
    }

public void setIdLivroEmprestimo(int idLivroEmprestimo) {
        this.idLivroEmprestimo = idLivroEmprestimo;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setMulta(boolean multa) {
        this.multa = multa;
    }

    public static List<LivroEmprestimo> getLivroEmprestimos() {
        return livroEmprestimos;
    }

    public static void setLivroEmprestimos(List<LivroEmprestimo> livroEmprestimos) {
        LivroEmprestimo.livroEmprestimos = livroEmprestimos;
    }

    public static void adicionarLivroEmprestimo(LivroEmprestimo livroEmprestimo) {
        livroEmprestimos.add(livroEmprestimo);
    }

    public static void removerLivroEmprestimo(LivroEmprestimo livroEmprestimo) {
        livroEmprestimos.remove(livroEmprestimo);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
