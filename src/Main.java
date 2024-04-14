import models.Database.LivroDatabase;
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

        //cria um livro ai
        LivroDatabase.criarLivro(1, "O Senhor dos Anéis", "J.R.R. Tolkien", "HarperCollins", "O Senhor dos Anéis é um livro de alta fantasia escrito pelo autor britânico J. R. R. Tolkien. A história começa como sequência de um livro anterior de Tolkien, O Hobbit, e logo se desenvolve numa história muito maior.", 1200, "Fantasia", "978-85-01-10892-6", 14, null, null, LivroStatus.buscarStatusPorId(1));

        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }
}