package views;

import models.Database.LivroDatabase;
import models.Livro.Livro;
import models.Livro.LivroStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivroView extends JFrame implements ActionListener {
    private JButton btnCadastrar, btnVoltar;
    JLabel labelNome, labelAutor, labelIdLivro, labelEditora, labelSinopse, labelPagina, labelIsbn, labelPrazoEmprestimo, labelCategoria, labelStatus;
    JTextField textFieldNome, textFieldAutor, textFieldIdLivro, textFieldEditora, textFieldSinopse, textFieldPagina, textFieldIsbn, textFieldPrazoEmprestimo, textFieldCategoria;
    JComboBox comboBoxStatus;

    public CadastroLivroView(Livro livro) {
        setTitle("Cadastro de Livros");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
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

        labelCategoria = new JLabel("Categoria do livro:");
        formPanel.add(labelCategoria);
        textFieldCategoria = new JTextField();
        formPanel.add(textFieldCategoria);

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

        labelStatus = new JLabel("Prazo de empréstimo do livro:");
        formPanel.add(labelStatus);
        comboBoxStatus = new JComboBox<>(LivroStatus.listarDescricaoStatus(LivroStatus.listarStatus()).toArray());
        formPanel.add(comboBoxStatus);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("voltar");
        btnVoltar.addActionListener(this);
        buttonPanel.add(btnVoltar);

        if (livro != null) {
            textFieldNome.setText(livro.getTitulo());
            textFieldAutor.setText(livro.getAutor());
            textFieldIdLivro.setText(String.valueOf(livro.getIdLivro()));
            textFieldEditora.setText(livro.getEditora());
            textFieldSinopse.setText(livro.getSinopse());
            textFieldPagina.setText(String.valueOf(livro.getPaginas()));
            textFieldCategoria.setText(livro.getCategoria());
            textFieldIsbn.setText(livro.getIsbn());
            textFieldPrazoEmprestimo.setText(String.valueOf(livro.getPrazoEmprestimo()));
            btnCadastrar = new JButton("Editar");
            btnCadastrar.setActionCommand("editar");
            btnCadastrar.addActionListener(this);
        } else {
            btnCadastrar = new JButton("Cadastrar");
            btnCadastrar.setActionCommand("cadastrar");
            btnCadastrar.addActionListener(this);
        }

        buttonPanel.add(btnCadastrar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public LivroStatus getLivroStatus() {
        return LivroStatus.buscarStatusPorDescricao(comboBoxStatus.getSelectedItem().toString());
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
                    textFieldCategoria.getText(),
                    textFieldIsbn.getText(),
                    Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                    null,
                    null, getLivroStatus());

            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroView pesquisaLivroView = new PesquisaLivroView();
                pesquisaLivroView.setVisible(true);
                this.dispose();
            });
        } else if (e.getActionCommand().equals("editar")) {
            LivroDatabase.editarLivro(
                    Integer.parseInt(textFieldIdLivro.getText()),
                    textFieldNome.getText(),
                    textFieldAutor.getText(),
                    textFieldEditora.getText(),
                    textFieldSinopse.getText(),
                    Integer.parseInt(textFieldPagina.getText()),
                    textFieldCategoria.getText(),
                    textFieldIsbn.getText(),
                    Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                    null, getLivroStatus());

            JOptionPane.showMessageDialog(this, "Livro editado com sucesso!");
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroView pesquisaLivroView = new PesquisaLivroView();
                pesquisaLivroView.setVisible(true);
                this.dispose();
            });
        } else if (e.getActionCommand().equals("voltar")) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroView pesquisaLivroView = new PesquisaLivroView();
                pesquisaLivroView.setVisible(true);
                this.dispose();
            });
        }
    }
}