import views.CadastroLivroView;
import views.PesquisaLivroView;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // quero que assim que acabe o cadastro, ele va para a pesquisa automaticamente
        SwingUtilities.invokeLater(() -> {
            CadastroLivroView cadastroLivroView = new CadastroLivroView(null);
            cadastroLivroView.setVisible(true);
        });
    }
}