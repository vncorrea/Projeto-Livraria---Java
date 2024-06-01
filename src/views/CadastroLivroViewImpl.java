package views;

import controller.LivroController;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivroViewImpl extends JFrame implements ActionListener, CadastroLivroView {

    private final LivroController livroController;
    private JButton btnCadastrar, btnVoltar;
    JLabel labelNome, labelAutor, labelIdLivro, labelEditora, labelSinopse, labelPagina, labelIsbn, labelPrazoEmprestimo, labelCategoria, labelStatus;
    JTextField textFieldNome, textFieldAutor, textFieldIdLivro, textFieldEditora, textFieldSinopse, textFieldPagina, textFieldIsbn, textFieldPrazoEmprestimo;
    JComboBox comboBoxStatus, comboBoxCategoria;

    public CadastroLivroViewImpl(LivroController livroController) {
        initializeUI();

        this.livroController = livroController;
        livroController.setCadastroView(this);
    }

    public LivroStatus getLivroStatus() {
        return LivroStatus.buscarStatusPorDescricao(comboBoxStatus.getSelectedItem().toString());
    }

    public LivroCategoria getLivroCategoria() {
        return LivroCategoria.buscarCategoriaPorDescricao(comboBoxCategoria.getSelectedItem().toString());
    }

    private void initializeUI() {
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

        labelCategoria = new JLabel("Categoria do livro:");
        formPanel.add(labelCategoria);
        comboBoxCategoria = new JComboBox<>(LivroCategoria.listarDescricaoCategorias(LivroCategoria.listarCategorias()).toArray());
        formPanel.add(comboBoxCategoria);

        labelStatus = new JLabel("Status do livro:");
        formPanel.add(labelStatus);
        comboBoxStatus = new JComboBox<>(LivroStatus.listarDescricaoStatus(LivroStatus.listarStatus()).toArray());
        formPanel.add(comboBoxStatus);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("voltar");
        btnVoltar.addActionListener(this);
        buttonPanel.add(btnVoltar);

        buttonPanel.add(btnCadastrar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cadastrar")) {
            if (camposPreenchidos()) {
                // cria um livro com o DatabaseManager
                livroController.cadastrarLivro(
                        textFieldNome.getText(),
                        textFieldAutor.getText(),
                        textFieldEditora.getText(),
                        textFieldSinopse.getText(),
                        Integer.parseInt(textFieldPagina.getText()),
                        LivroCategoria.getIdCategoria(getLivroCategoria()),
                        textFieldIsbn.getText(),
                        Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                        null, null, 1);

                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                SwingUtilities.invokeLater(() -> {
                    PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController);
                    PesquisaLivroViewImpl.setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos antes de editar o livro.");
            }
        } else if (e.getActionCommand().equals("editar")) {
            if (camposPreenchidos()) {
                livroController.editarLivro(
                        1,
                        textFieldNome.getText(),
                        textFieldAutor.getText(),
                        textFieldEditora.getText(),
                        textFieldSinopse.getText(),
                        Integer.parseInt(textFieldPagina.getText()),
                        LivroCategoria.getIdCategoria(getLivroCategoria()),
                        textFieldIsbn.getText(),
                        Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                        null, LivroStatus.getIdStatus(getLivroStatus()));

                JOptionPane.showMessageDialog(this, "Livro editado com sucesso!");
                SwingUtilities.invokeLater(() -> {
                    PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController);
                    PesquisaLivroViewImpl.setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos antes de editar o livro.");
            }
        } else if (e.getActionCommand().equals("voltar")) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController);
                PesquisaLivroViewImpl.setVisible(true);
                this.dispose();
            });
        }
    }

    private boolean camposPreenchidos() {
        return !textFieldNome.getText().isEmpty() &&
                !textFieldAutor.getText().isEmpty() &&
                !textFieldEditora.getText().isEmpty() &&
                !textFieldSinopse.getText().isEmpty() &&
                !textFieldPagina.getText().isEmpty() &&
                !textFieldIsbn.getText().isEmpty() &&
                !textFieldPrazoEmprestimo.getText().isEmpty();
    }

    public void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void abrir() {
        setVisible(true);
    }
}