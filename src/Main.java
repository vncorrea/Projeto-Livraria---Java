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
        
        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }
}