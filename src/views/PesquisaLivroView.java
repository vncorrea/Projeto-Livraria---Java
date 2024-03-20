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
    private JPanel resultadosPanel;

    public PesquisaLivroView() {
        setTitle("Pesquisar Livros");
        setSize(600, 400);
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

        resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new BoxLayout(resultadosPanel, BoxLayout.Y_AXIS));

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultadosPanel), BorderLayout.CENTER);

        add(panel);
    }

    private void pesquisarLivros() {
        String pesquisa = textFieldPesquisa.getText();

        List<Livro> livrosEncontrados = LivroDatabase.pesquisarLivro(pesquisa, pesquisa, pesquisa, pesquisa);

        resultadosPanel.removeAll();

        for (Livro livro : livrosEncontrados) {
            JPanel livroPanel = new JPanel(new BorderLayout());
            JLabel lblTitulo = new JLabel(livro.getTitulo());
            JButton btnEditar = new JButton(new ImageIcon("caminho/para/o/icone/lapis.png"));
            JButton btnExcluir = new JButton(new ImageIcon("caminho/para/o/icone/lixeira.png"));
            btnEditar.setActionCommand("editar");
//            btnExcluir.setActionCommand("excluir-" + livro.getId());
            btnEditar.addActionListener(this);
            btnExcluir.addActionListener(this);

            livroPanel.add(lblTitulo, BorderLayout.CENTER);
            livroPanel.add(btnEditar, BorderLayout.EAST);
            livroPanel.add(btnExcluir, BorderLayout.WEST);

            resultadosPanel.add(livroPanel);
        }

        resultadosPanel.revalidate();
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
        } else if (e.getActionCommand().startsWith("excluir-")) {
            String livroId = e.getActionCommand().substring(8);
//            LivroDatabase.excluirLivro(livroId);
            pesquisarLivros();
        }
    }
}