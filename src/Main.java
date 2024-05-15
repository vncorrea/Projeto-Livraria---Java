import models.Database.DatabaseManager;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import views.PesquisaLivroView;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseManager.init();

        DatabaseManager.criarLivroCategoria("Romance");
        DatabaseManager.criarLivroCategoria("Ficção Científica");
        DatabaseManager.criarLivroCategoria("Terror");
        DatabaseManager.criarLivroCategoria("Fantasia");
        DatabaseManager.criarLivroCategoria("Biografia");
        DatabaseManager.criarLivroCategoria("Ação");
        DatabaseManager.criarLivroCategoria("Aventura");
        DatabaseManager.criarLivroCategoria("Comédia");
        DatabaseManager.criarLivroCategoria("Drama");
        DatabaseManager.criarLivroCategoria("Suspense");

        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }
}