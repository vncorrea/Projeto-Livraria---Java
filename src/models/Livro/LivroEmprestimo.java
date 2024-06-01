package models.Livro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivroEmprestimo {
    private int idLivroEmprestimo;
    private int idLivro;
    private int idPessoa;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private boolean multa;

    public LivroEmprestimo(int idLivroEmprestimo, int idLivro, int idPessoa, Date dataEmprestimo, Date dataDevolucao, boolean multa) {
        this.idLivroEmprestimo = idLivroEmprestimo;
        this.idLivro = idLivro;
        this.idPessoa = idPessoa;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.multa = multa;
    }

    private static List<LivroEmprestimo> livroEmprestimos = new ArrayList<>();

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
}
