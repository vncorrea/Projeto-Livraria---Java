package views;

import javax.swing.*;
import java.awt.*;

import models.Database.LivroDatabase;
import models.Livro.Livro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PesquisaLivroView extends JFrame implements ActionListener {

    private JTextField textFieldPesquisa;
    private JTextArea textAreaResultados;

    public PesquisaLivroView() {
        setTitle("Pesquisar Livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout());
        textFieldPesquisa = new JTextField();
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setActionCommand("pesquisar");
        btnPesquisar.addActionListener(this);
        searchPanel.add(textFieldPesquisa, BorderLayout.CENTER);
        searchPanel.add(btnPesquisar, BorderLayout.EAST);

        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textAreaResultados), BorderLayout.CENTER);

        add(panel);
    }

    private void pesquisarLivros() {
        String pesquisa = textFieldPesquisa.getText();

        List<Livro> livrosEncontrados = LivroDatabase.pesquisarLivro(pesquisa, pesquisa, pesquisa, pesquisa);

        StringBuilder resultados = new StringBuilder();
        resultados.append("Resultados para: ").append(pesquisa).append("\n");
        for (Livro livro : livrosEncontrados) {
            resultados.append(livro.getTitulo()).append("\n");
        }

        textAreaResultados.setText(resultados.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            pesquisarLivros();
        } else if (e.getActionCommand().equals("editar")) {
            // implementar edição
        }
    }
}