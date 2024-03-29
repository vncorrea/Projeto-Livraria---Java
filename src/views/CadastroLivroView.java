package views;

import models.Database.LivroDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivroView extends JFrame implements ActionListener {
    private JButton btnCadastrar;
    JLabel labelNome, labelAutor, labelIdLivro, labelEditora, labelSinopse, labelPagina, labelIsbn, labelPrazoEmprestimo;
    JTextField textFieldNome, textFieldAutor, textFieldIdLivro, textFieldEditora, textFieldSinopse, textFieldPagina, textFieldIsbn, textFieldPrazoEmprestimo;

    public CadastroLivroView() {
        setTitle("Cadastro de Livros");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout()); // Painel principal com layout BorderLayout
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10)); // Painel para o formulário com layout GridLayout
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelNome = new JLabel("Nome do livro:");
        formPanel.add(labelNome);
        textFieldNome = new JTextField();
        formPanel.add(textFieldNome);

        labelAutor = new JLabel("Autor do livro:");
        formPanel.add(labelAutor);
        textFieldAutor = new JTextField();
        formPanel.add(textFieldAutor);

        labelIdLivro = new JLabel("ID do livro:");
        formPanel.add(labelIdLivro);
        textFieldIdLivro = new JTextField();
        formPanel.add(textFieldIdLivro);

        labelEditora = new JLabel("Editora do livro:");
        formPanel.add(labelEditora);
        textFieldEditora = new JTextField();
        formPanel.add(textFieldEditora);

        labelSinopse = new JLabel("Sinopse do livro:");
        formPanel.add(labelSinopse);
        textFieldSinopse = new JTextField();
        formPanel.add(textFieldSinopse);

        labelPagina = new JLabel("Páginas do livro:");
        formPanel.add(labelPagina);
        textFieldPagina = new JTextField();
        formPanel.add(textFieldPagina);

        labelIsbn = new JLabel("ISBN do livro:");
        formPanel.add(labelIsbn);
        textFieldIsbn = new JTextField();
        formPanel.add(textFieldIsbn);

        labelPrazoEmprestimo = new JLabel("Prazo de empréstimo do livro:");
        formPanel.add(labelPrazoEmprestimo);
        textFieldPrazoEmprestimo = new JTextField();
        formPanel.add(textFieldPrazoEmprestimo);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setActionCommand("cadastrar");
        btnCadastrar.addActionListener(this);
        buttonPanel.add(btnCadastrar, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cadastrar")) {
            LivroDatabase.criarLivro(
                    Integer.parseInt(textFieldIdLivro.getText()),
                    textFieldNome.getText(),
                    textFieldAutor.getText(),
                    textFieldEditora.getText(),
                    textFieldSinopse.getText(),
                    Integer.parseInt(textFieldPagina.getText()),
                    1,
                    1,
                    textFieldIsbn.getText(),
                    Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                    null,
                    null);

            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroView pesquisaLivroView = new PesquisaLivroView();
                pesquisaLivroView.setVisible(true);
                this.dispose();
            });
        }
    }
}