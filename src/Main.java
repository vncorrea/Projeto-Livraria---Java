import models.Database.LivroDatabase;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import views.CadastroLivroView;
import views.PesquisaLivroView;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LivroStatus.criarStatus(1, "Disponível");
        LivroStatus.criarStatus(2, "Emprestado");
        LivroStatus.criarStatus(3, "Atrasado");

        LivroCategoria.criarCategoria(1, "Romance");
        LivroCategoria.criarCategoria(2, "Ficção Científica");
        LivroCategoria.criarCategoria(3, "Terror");
        LivroCategoria.criarCategoria(4, "Fantasia");
        LivroCategoria.criarCategoria(5, "Biografia");
        LivroCategoria.criarCategoria(6, "Ação");
        LivroCategoria.criarCategoria(7, "Aventura");
        LivroCategoria.criarCategoria(8, "Comédia");
        LivroCategoria.criarCategoria(9, "Drama");
        LivroCategoria.criarCategoria(10, "Suspense");

        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }
}