package views.Livro.EmprestimoLivro;

import controller.LivroController;
import controller.PessoaController;
import com.toedter.calendar.JDateChooser;
import models.Pessoa.Pessoa;
import views.Livro.PesquisaLivro.PesquisaLivroViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmprestimoLivroViewImpl extends JFrame implements ActionListener, EmprestimoLivroView {
    private final LivroController livroController;
    private final PessoaController pessoaController;
    private int idLivroAtual;
    private JButton btnEmprestar, btnVoltar;
    JLabel labelPessoa, labelDataEmprestimo, labelDataDevolucao, labelObservacoes;
    JTextField textFieldObservacoes;
    JComboBox comboBoxPessoa;
    private JDateChooser jDateEmprestimo, jDateDevolucao;
    private Pessoa colaboradorLogado;


    public EmprestimoLivroViewImpl(LivroController livroController, PessoaController pessoaController, int idLivro, Pessoa colaboradorLogado) {
        this.livroController = livroController;
        this.pessoaController = pessoaController;
        this.idLivroAtual = idLivro;
        this.colaboradorLogado = colaboradorLogado;
        livroController.setEmprestimoLivroView(this, idLivro);

        initializeUI(idLivro);
    }

    public void initializeUI(int idLivro) {
        setTitle("Empréstimo de Livros");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelPessoa = new JLabel("Pessoa emprestada:");
        formPanel.add(labelPessoa);
        comboBoxPessoa = new JComboBox<>(Pessoa.listarNomePessoas(pessoaController.pesquisarPessoas("", "", "")).toArray());
        formPanel.add(comboBoxPessoa);

        labelDataEmprestimo = new JLabel("Data de empréstimo:");
        formPanel.add(labelDataEmprestimo);
        jDateEmprestimo = new JDateChooser();
        formPanel.add(jDateEmprestimo);

        labelDataDevolucao = new JLabel("Data de devolução:");
        formPanel.add(labelDataDevolucao);
        jDateDevolucao = new JDateChooser();
        formPanel.add(jDateDevolucao);

        labelObservacoes = new JLabel("Observações:");
        formPanel.add(labelObservacoes);
        textFieldObservacoes = new JTextField();
        formPanel.add(textFieldObservacoes);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("voltar");
        btnVoltar.addActionListener(this);
        buttonPanel.add(btnVoltar);

        btnEmprestar = new JButton("Emprestar");
        btnEmprestar.setActionCommand("emprestar");
        btnEmprestar.addActionListener(this);
        buttonPanel.add(btnEmprestar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }


    @Override
    public void abrir() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("emprestar".equals(e.getActionCommand())) {
            Pessoa pessoa = pessoaController.pesquisarPessoa(0, (String) comboBoxPessoa.getSelectedItem(), "", "");

            livroController.emprestarLivro(idLivroAtual, pessoa.getIdPessoa(), jDateEmprestimo.getDate(), jDateDevolucao.getDate(), textFieldObservacoes.getText());

            JOptionPane.showMessageDialog(this, "Emprestimo cadastrado com sucesso!");

            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController, colaboradorLogado);
                PesquisaLivroViewImpl.setVisible(true);
                this.dispose();
            });

        } else if ("voltar".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController, colaboradorLogado);
                PesquisaLivroViewImpl.setVisible(true);
                this.dispose();
            });
        }
    }
}
