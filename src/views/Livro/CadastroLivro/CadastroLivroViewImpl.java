package views.Livro.CadastroLivro;

import controller.LivroController;
import controller.PessoaController;
import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import models.Pessoa.Pessoa;
import views.Livro.PesquisaLivro.PesquisaLivroViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivroViewImpl extends JFrame implements ActionListener, CadastroLivroView {

    private final LivroController livroController;
    private final PessoaController pessoaController;
    private int idLivroAtual;
    private JButton btnCadastrar, btnVoltar, btnEditar;
    JLabel labelNome, labelAutor, labelIdLivro, labelEditora, labelSinopse, labelPagina, labelIsbn, labelPrazoEmprestimo, labelCategoria, labelStatus;
    JTextField textFieldNome, textFieldAutor, textFieldIdLivro, textFieldEditora, textFieldSinopse, textFieldPagina, textFieldIsbn, textFieldPrazoEmprestimo;
    JComboBox comboBoxStatus, comboBoxCategoria;
    private Pessoa colaboradorLogado;

    public CadastroLivroViewImpl(LivroController livroController, PessoaController pessoaController, int idLivro, Pessoa colaboradorLogado) {
        this.livroController = livroController;
        this.pessoaController = pessoaController;
        this.idLivroAtual = idLivro;
        this.colaboradorLogado = colaboradorLogado;
        livroController.setCadastroView(this, idLivro);

        initializeUI(idLivro);
    }

    public LivroStatus getLivroStatus() {
        return LivroStatus.buscarStatusPorDescricao(comboBoxStatus.getSelectedItem().toString());
    }

    public LivroCategoria getLivroCategoria() {
        return LivroCategoria.buscarCategoriaPorDescricao(comboBoxCategoria.getSelectedItem().toString());
    }

    private void initializeUI(int idLivro) {
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
        comboBoxCategoria = new JComboBox<>(LivroCategoria.listarDescricaoCategorias(livroController.pesquisarCategorias()).toArray());
        formPanel.add(comboBoxCategoria);

        labelStatus = new JLabel("Status do livro:");
        formPanel.add(labelStatus);
        comboBoxStatus = new JComboBox<>(LivroStatus.listarDescricaoStatus(livroController.pesquisarStatus()).toArray());
        formPanel.add(comboBoxStatus);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("voltar");
        btnVoltar.addActionListener(this);
        buttonPanel.add(btnVoltar);



        if(idLivro != 0) {
            carregarLivroSelecionado(idLivro);

            btnEditar = new JButton("Editar");
            btnEditar.setActionCommand("editar");
            btnEditar.addActionListener(this);
            buttonPanel.add(btnEditar);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        } else {
            btnCadastrar = new JButton("Cadastrar");
            btnCadastrar.setActionCommand("cadastrar");
            btnCadastrar.addActionListener(this);
            buttonPanel.add(btnCadastrar);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        }

        add(mainPanel);
    }

    private void carregarLivroSelecionado(int idLivro) {
        Livro livro = livroController.pesquisarLivro(idLivro);
        LivroCategoria categoria = livroController.pesquisarCategoria(livro.getIdLivroCategoria(), null);
        LivroStatus status = livroController.pesquisarUmStatus(livro.getIdLivroStatus(), null);

        textFieldNome.setText(livro.getTitulo());
        textFieldAutor.setText(livro.getAutor());
        textFieldEditora.setText(livro.getEditora());
        textFieldSinopse.setText(livro.getSinopse());
        textFieldPagina.setText(String.valueOf(livro.getPaginas()));
        textFieldIsbn.setText(livro.getIsbn());
        textFieldPrazoEmprestimo.setText(String.valueOf(livro.getPrazoEmprestimo()));
        comboBoxCategoria.setSelectedItem(categoria.getDescricao());
        comboBoxStatus.setSelectedItem(status.getDescricao());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cadastrar")) {
            if (camposPreenchidos()) {
                LivroCategoria categoria = livroController.pesquisarCategoria(0, (String) comboBoxCategoria.getSelectedItem());
                LivroStatus status = livroController.pesquisarUmStatus(0, (String) comboBoxStatus.getSelectedItem());

                // cria um livro com o DatabaseManager
                livroController.criarLivro(
                        textFieldNome.getText(),
                        textFieldAutor.getText(),
                        textFieldEditora.getText(),
                        textFieldSinopse.getText(),
                        Integer.parseInt(textFieldPagina.getText()),
                        categoria.getId(),
                        textFieldIsbn.getText(),
                        Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                        null, null, status.getIdLivroStatus());

                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                SwingUtilities.invokeLater(() -> {
                    PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController, colaboradorLogado);
                    PesquisaLivroViewImpl.setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos antes de editar o livro.");
            }
        } else if (e.getActionCommand().equals("editar")) {
            if (camposPreenchidos()) {
                LivroCategoria categoria = livroController.pesquisarCategoria(0, (String) comboBoxCategoria.getSelectedItem());
                LivroStatus status = livroController.pesquisarUmStatus(0, (String) comboBoxStatus.getSelectedItem());

                livroController.editarLivro(
                        idLivroAtual,
                        textFieldNome.getText(),
                        textFieldAutor.getText(),
                        textFieldEditora.getText(),
                        textFieldSinopse.getText(),
                        Integer.parseInt(textFieldPagina.getText()),
                        categoria.getId(),
                        textFieldIsbn.getText(),
                        Integer.parseInt(textFieldPrazoEmprestimo.getText()),
                        null, status.getIdLivroStatus());

                JOptionPane.showMessageDialog(this, "Livro editado com sucesso!");
                SwingUtilities.invokeLater(() -> {
                    PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController, colaboradorLogado);
                    PesquisaLivroViewImpl.setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos antes de editar o livro.");
            }
        } else if (e.getActionCommand().equals("voltar")) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController, colaboradorLogado);
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