package views;

import controller.LivroController;
import controller.PessoaController;
import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import com.toedter.calendar.JDateChooser;

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


    public EmprestimoLivroViewImpl(LivroController livroController, PessoaController pessoaController, int idLivro) {
        this.livroController = livroController;
        this.pessoaController = pessoaController;
        this.idLivroAtual = idLivro;
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
        comboBoxPessoa = new JComboBox<>(LivroStatus.listarDescricaoStatus(livroController.pesquisarStatus()).toArray());
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
//            livroController.emprestarLivro(idLivroAtual, comboBoxStatus.getSelectedIndex(), jDateEmprestimo.getDate());
        } else if ("voltar".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl PesquisaLivroViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController);
                PesquisaLivroViewImpl.setVisible(true);
                this.dispose();
            });
        }
    }
}
