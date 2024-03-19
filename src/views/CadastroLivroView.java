package views;

import javax.swing.*;
import java.awt.*;

public class CadastroLivroView extends JFrame {

    public CadastroLivroView() {
        setTitle("Cadastro de Livros");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome do livro:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Autor do livro:"));
        panel.add(new JTextField());
        panel.add(new JLabel());
        panel.add(new JButton("Cadastrar"));

        add(panel);
    }
}