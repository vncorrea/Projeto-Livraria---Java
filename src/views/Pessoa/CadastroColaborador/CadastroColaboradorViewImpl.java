package views.Pessoa.CadastroColaborador;

import com.toedter.calendar.JDateChooser;
import controller.LivroController;
import controller.PessoaController;
import models.Pessoa.Pessoa;
import views.Livro.PesquisaLivro.PesquisaLivroViewImpl;
import views.Pessoa.PesquisaPessoa.PesquisaPessoaViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroColaboradorViewImpl extends JFrame implements ActionListener, CadastroColaboradorView {
    private final LivroController livroController;
    private final PessoaController pessoaController;
    private JLabel labelPessoa, labelCargo, labelDataRegistro, labelPis, labelRg, labelAdministrador;
    JComboBox comboBoxPessoa;
    Pessoa pessoa;
    private JTextField textFieldCargo, textFieldPis, textFieldRg;
    private JCheckBox checkBoxAdministrador;
    private JButton btnCadastrar, btnVoltar;
    private JDateChooser dateChooserDataRegistro;

    public CadastroColaboradorViewImpl(LivroController livroController, PessoaController pessoaController, Pessoa pessoa) {
        this.livroController = livroController;
        this.pessoaController = pessoaController;
        this.pessoa = pessoa;

        initializeUI(pessoa);
    }

    void initializeUI(Pessoa pessoa) {
        setTitle("Cadastro de colaborador");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelPessoa = new JLabel("Pessoa:");
        formPanel.add(labelPessoa);
        comboBoxPessoa = new JComboBox<>(Pessoa.listarNomePessoas(pessoaController.pesquisarPessoas("", "", "")).toArray());
        formPanel.add(comboBoxPessoa);

        labelCargo = new JLabel("Cargo:");
        formPanel.add(labelCargo);
        textFieldCargo = new JTextField();
        formPanel.add(textFieldCargo);

        labelDataRegistro = new JLabel("Data de registro:");
        formPanel.add(labelDataRegistro);
        dateChooserDataRegistro = new JDateChooser();
        formPanel.add(dateChooserDataRegistro);

        labelPis = new JLabel("Pis:");
        formPanel.add(labelPis);
        textFieldPis = new JTextField();
        formPanel.add(textFieldPis);

        labelRg = new JLabel("RG:");
        formPanel.add(labelRg);
        textFieldRg = new JTextField();
        formPanel.add(textFieldRg);

        labelAdministrador = new JLabel("Administrador:");
        formPanel.add(labelAdministrador);
        checkBoxAdministrador = new JCheckBox();
        formPanel.add(checkBoxAdministrador);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("voltar");
        btnVoltar.addActionListener(this);
        buttonPanel.add(btnVoltar);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setActionCommand("cadastrar");
        btnCadastrar.addActionListener(this);
        buttonPanel.add(btnCadastrar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        if(pessoa != null) {
            comboBoxPessoa.setSelectedItem(pessoa.getNome());
        }

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("voltar")) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl pesquisaPessoaViewImpl = new PesquisaLivroViewImpl(livroController, pessoaController);
                pesquisaPessoaViewImpl.setVisible(true);
                this.dispose();
            });
        } else if (e.getActionCommand().equals("cadastrar")) {
            pessoaController.editarPessoa(pessoa.getIdPessoa(),
                    pessoa.getNome(),
                    pessoa.getCpf(),
                    pessoa.getEmail(),
                    pessoa.getTelefone(),
                    pessoa.getLogradouro(),
                    pessoa.getCidade(),
                    pessoa.getCep(),
                    pessoa.getDataCadastro(),
                    pessoa.getDataNascimento(),
                    pessoa.getUf(),
                    pessoa.getSenha(),
                    true,
                    textFieldCargo.getText(),
                    dateChooserDataRegistro.getDate(),
                    textFieldPis.getText(),
                    textFieldRg.getText(),
                    checkBoxAdministrador.isSelected());

            JOptionPane.showMessageDialog(this, "Colaborador cadastrado com sucesso!");

            SwingUtilities.invokeLater(() -> {
                PesquisaPessoaViewImpl pesquisaPessoaViewImpl = new PesquisaPessoaViewImpl(livroController, pessoaController);
                pesquisaPessoaViewImpl.setVisible(true);
                this.dispose();
            });
        }
    }

    @Override
    public void abrir() {
        setVisible(true);
    }
}
