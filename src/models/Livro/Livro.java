package models.Livro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue
    private int idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private String sinopse;
    private int paginas;
    private int idLivroStatus;
    private int idLivroCategoria;
    private String isbn;
    private int prazoEmprestimo;
    private Date dataPublicacao;
    private Date dataCadastro;

    private static List<Livro> livros = new ArrayList<>();

    public Livro(String titulo, String autor, String editora, String sinopse, int paginas, int idLivroCategoria, String isbn, int prazoEmprestimo, Date dataPublicacao,
                 Date dataCadastro, int idLivroStatus) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.sinopse = sinopse;
        this.paginas = paginas;
        this.idLivroCategoria = idLivroCategoria;
        this.isbn = isbn;
        this.prazoEmprestimo = prazoEmprestimo;
        this.dataPublicacao = dataPublicacao;
        this.dataCadastro = dataCadastro;
        this.idLivroStatus = idLivroStatus;
    }

    public Livro() {

    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getIdLivroStatus() {
        return idLivroStatus;
    }


    public void setIdLivroCategoria(int idLivroCategoria) {
        this.idLivroCategoria = idLivroCategoria;
    }

    public int getIdLivroCategoria() {
        return idLivroCategoria;
    }

    public void setIdLivroStatus(int idLivroStatus) {
        this.idLivroStatus = idLivroStatus;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrazoEmprestimo() {
        return prazoEmprestimo;
    }

    public void setPrazoEmprestimo(int prazoEmprestimo) {
        this.prazoEmprestimo = prazoEmprestimo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
