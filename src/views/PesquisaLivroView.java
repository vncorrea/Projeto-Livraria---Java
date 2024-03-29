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

        JButton btnMais = new JButton("Adicionar Livro");
        btnMais.setActionCommand("adicionarLivro");
        btnMais.addActionListener(this);
        searchPanel.add(btnMais, BorderLayout.NORTH);

        resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10)); // Layout FlowLayout com orientação horizontal
        resultadosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultadosPanel), BorderLayout.CENTER);

        add(panel);
    }

    private void pesquisarLivros() {
        String pesquisa = textFieldPesquisa.getText();

        // Criar um novo JPanel para armazenar os livros encontrados
        JPanel novoPanel = new JPanel();
        novoPanel.setLayout(new BoxLayout(novoPanel, BoxLayout.Y_AXIS)); // Layout vertical para os livros

        List<Livro> livrosEncontrados = LivroDatabase.pesquisarLivro(pesquisa, pesquisa, pesquisa, pesquisa);

        for (Livro livro : livrosEncontrados) {
            JPanel livroPanel = new JPanel(new BorderLayout());
            JLabel lblTitulo = new JLabel(livro.getTitulo());

            // Painel para os botões
            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnEditar = new JButton(new ImageIcon("caminho/para/o/icone/lapis.png"));
            JButton btnExcluir = new JButton(new ImageIcon("caminho/para/o/icone/lixeira.png"));
            btnEditar.setPreferredSize(new Dimension(20, 20));
            btnExcluir.setPreferredSize(new Dimension(20, 20));
            btnEditar.addActionListener(this);
            btnExcluir.addActionListener(this);

            btnPanel.add(btnEditar);
            btnPanel.add(btnExcluir);

            livroPanel.add(lblTitulo, BorderLayout.CENTER);
            livroPanel.add(btnPanel, BorderLayout.WEST);

            novoPanel.add(livroPanel); // Adicionar o livro ao novo painel
        }
        
        resultadosPanel.removeAll();
        resultadosPanel.add(novoPanel);
        resultadosPanel.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PesquisaLivroView().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "pesquisar":
                pesquisarLivros();
                break;
            case "editar":
                // implementar edição
                break;
            case "excluir":
                // implementar exclusão
                break;
            case "adicionarLivro":
                SwingUtilities.invokeLater(() -> {
                    CadastroLivroView cadastroLivroView = new CadastroLivroView();
                    cadastroLivroView.setVisible(true);
                    this.dispose();
                });
                break;
        }
    }
}