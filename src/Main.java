import models.Database.DatabaseManager;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import views.PesquisaLivroView;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseManager.createDatabase();
        DatabaseManager.criarStatus("Disponível");
        DatabaseManager.criarStatus("Emprestado");
        DatabaseManager.criarStatus("Atrasado");

        DatabaseManager.criarCategoria("Romance");
        DatabaseManager.criarCategoria("Ficção Científica");
        DatabaseManager.criarCategoria("Terror");
        DatabaseManager.criarCategoria("Fantasia");
        DatabaseManager.criarCategoria("Biografia");
        DatabaseManager.criarCategoria("Ação");
        DatabaseManager.criarCategoria("Aventura");
        DatabaseManager.criarCategoria("Comédia");
        DatabaseManager.criarCategoria("Drama");
        DatabaseManager.criarCategoria("Suspense");

        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }
}