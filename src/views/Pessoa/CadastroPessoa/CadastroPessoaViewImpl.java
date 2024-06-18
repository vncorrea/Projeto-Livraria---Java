package views.Pessoa.CadastroPessoa;

import com.toedter.calendar.JDateChooser;
import controller.LivroController;
import controller.PessoaController;
import models.Pessoa.Colaborador;
import models.Pessoa.Pessoa;
import views.Livro.PesquisaLivro.PesquisaLivroViewImpl;
import views.Pessoa.CadastroColaborador.CadastroColaboradorViewImpl;
import views.Pessoa.PesquisaPessoa.PesquisaPessoaViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPessoaViewImpl extends JFrame implements CadastroPessoaView, ActionListener {
    private final PessoaController pessoaController;
    private final LivroController livroController;
    private int idPessoaAtual;
    private JButton btnCadastrar, btnEditar, btnVoltar;
    JLabel labelNome, labelCpf, labelEmail, labelTelefone, labelLogradouro, labelCidade, labelCep, labelDataNascimento, labelUf, labelColaborador;
    JTextField textFieldNome, textFieldCpf, textFieldEmail, textFieldTelefone, textFieldLogradouro, textFieldCidade, textFieldCep, textFieldUf;
    private JDateChooser jDateNascimento;
    private JCheckBox checkboxColaborador;
    private Pessoa colaboradorLogado;

    public CadastroPessoaViewImpl(PessoaController pessoaController, LivroController livroController, int idPessoa, Pessoa colaboradorLogado) {
        this.pessoaController = pessoaController;
        this.livroController = livroController;
        this.colaboradorLogado = colaboradorLogado;
        this.idPessoaAtual = idPessoa;

        initializeUI(idPessoa, colaboradorLogado);
    }

    public void initializeUI(int idPessoa, Pessoa colaboradorLogado) {
        Colaborador colaborador = null;

        if (colaboradorLogado instanceof Colaborador) {
            colaborador = (Colaborador) colaboradorLogado;
        }

        setTitle("Cadastro de pessoa");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelNome = new JLabel("Nome:");
        formPanel.add(labelNome);
        textFieldNome = new JTextField();
        formPanel.add(textFieldNome);

        labelCpf = new JLabel("CPF:");
        formPanel.add(labelCpf);
        textFieldCpf = new JTextField();
        formPanel.add(textFieldCpf);

        labelEmail = new JLabel("Email:");
        formPanel.add(labelEmail);
        textFieldEmail = new JTextField();
        formPanel.add(textFieldEmail);

        labelTelefone = new JLabel("Telefone:");
        formPanel.add(labelTelefone);
        textFieldTelefone = new JTextField();
        formPanel.add(textFieldTelefone);

        labelLogradouro = new JLabel("Logradouro:");
        formPanel.add(labelLogradouro);
        textFieldLogradouro = new JTextField();
        formPanel.add(textFieldLogradouro);

        labelCidade = new JLabel("Cidade:");
        formPanel.add(labelCidade);
        textFieldCidade = new JTextField();
        formPanel.add(textFieldCidade);

        labelUf = new JLabel("UF:");
        formPanel.add(labelUf);
        textFieldUf = new JTextField();
        formPanel.add(textFieldUf);

        labelCep = new JLabel("CEP:");
        formPanel.add(labelCep);
        textFieldCep = new JTextField();
        formPanel.add(textFieldCep);

        labelDataNascimento = new JLabel("Data de nascimento:");
        formPanel.add(labelDataNascimento);
        jDateNascimento = new JDateChooser();
        formPanel.add(jDateNascimento);

        if(colaborador != null && colaborador.isAdministrador()) {
            labelColaborador = new JLabel("Colaborador:");
            formPanel.add(labelColaborador);
            checkboxColaborador = new JCheckBox();
            formPanel.add(checkboxColaborador);
        }

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("voltar");
        btnVoltar.addActionListener(this);
        buttonPanel.add(btnVoltar);


        if (idPessoa != 0) {
            carregarPessoa(idPessoa);

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

    public void carregarPessoa(int idPessoa) {
        Pessoa pessoa = pessoaController.pesquisarPessoa(idPessoa, "", "", "");

        textFieldNome.setText(pessoa.getNome());
        textFieldCpf.setText(pessoa.getCpf());
        textFieldEmail.setText(pessoa.getEmail());
        textFieldTelefone.setText(pessoa.getTelefone());
        textFieldLogradouro.setText(pessoa.getLogradouro());
        textFieldCidade.setText(pessoa.getCidade());
        textFieldUf.setText(pessoa.getUf());
        textFieldCep.setText(pessoa.getCep());
        jDateNascimento.setDate(pessoa.getDataNascimento());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cadastrar")) {
            if (this.checkboxColaborador == null) {
                this.checkboxColaborador = new JCheckBox();
                this.checkboxColaborador.setSelected(false);
            }

            pessoaController.cadastrarPessoa(
                    textFieldNome.getText(),
                    textFieldCpf.getText(),
                    textFieldEmail.getText(),
                    textFieldTelefone.getText(),
                    textFieldLogradouro.getText(),
                    textFieldCidade.getText(),
                    textFieldCep.getText(),
                    new java.util.Date(),
                    jDateNascimento.getDate(),
                    textFieldUf.getText(),
                    "",
                    checkboxColaborador.isSelected(),
                    "",
                    null,
                    "",
                    "",
                    false);

            JOptionPane.showMessageDialog(this, "Pessoa cadastrada com sucesso!");

            if(checkboxColaborador.isSelected()) {
                Pessoa pessoa = pessoaController.pesquisarPessoa(0, "", textFieldCpf.getText(), "");
                SwingUtilities.invokeLater(() -> {
                    CadastroColaboradorViewImpl cadastroColaboradorView = new CadastroColaboradorViewImpl(livroController, pessoaController, pessoa, colaboradorLogado);
                    cadastroColaboradorView.setVisible(true);
                    this.dispose();
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    PesquisaPessoaViewImpl pesquisaPessoaView = new PesquisaPessoaViewImpl(livroController, pessoaController, colaboradorLogado);
                    pesquisaPessoaView.setVisible(true);
                    this.dispose();
                });
            }
        } else if (e.getActionCommand().equals("editar")) {
            pessoaController.editarPessoa(
                    idPessoaAtual,
                    textFieldNome.getText(),
                    textFieldCpf.getText(),
                    textFieldEmail.getText(),
                    textFieldTelefone.getText(),
                    textFieldLogradouro.getText(),
                    textFieldCidade.getText(),
                    textFieldCep.getText(),
                    new java.util.Date(),
                    jDateNascimento.getDate(),
                    textFieldUf.getText(),
                    "",
                    checkboxColaborador.isSelected(),
                    "",
                    null,
                    "",
                    "",
                    false);

            JOptionPane.showMessageDialog(this, "Pessoa editada com sucesso!");


            if(checkboxColaborador.isSelected()) {
                Pessoa pessoa = pessoaController.pesquisarPessoa(0, "", textFieldCpf.getText(), "");
                SwingUtilities.invokeLater(() -> {
                    CadastroColaboradorViewImpl cadastroColaboradorView = new CadastroColaboradorViewImpl(livroController, pessoaController, pessoa, colaboradorLogado);
                    cadastroColaboradorView.setVisible(true);
                    this.dispose();
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    PesquisaPessoaViewImpl pesquisaPessoaView = new PesquisaPessoaViewImpl(livroController, pessoaController, colaboradorLogado);
                    pesquisaPessoaView.setVisible(true);
                    this.dispose();
                });
            }
        } else if (e.getActionCommand().equals("voltar")) {
                SwingUtilities.invokeLater(() -> {
                    PesquisaPessoaViewImpl pesquisaPessoaView = new PesquisaPessoaViewImpl(livroController, pessoaController, colaboradorLogado);
                    pesquisaPessoaView.setVisible(true);
                    this.dispose();
                });
        }
    }

    @Override
    public void abrir() {
        setVisible(true);
    }
}
