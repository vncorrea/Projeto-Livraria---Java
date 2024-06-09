package views.Login;

import controller.LivroController;
import controller.PessoaController;
import models.Pessoa.Pessoa;
import views.CadastroLivro.CadastroLivroViewImpl;
import views.Login.LoginSucessoViewImpl;
import views.LoginSucesso.LoginSucessoView;
import views.PesquisaLivro.PesquisaLivroViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPessoaViewImpl extends JFrame implements ActionListener, LoginPessoaView {

    private final PessoaController pessoaController;
    private final LivroController livroController;
    private JLabel labelCpf, labelSenha;
    private JTextField textFieldCpf;
    private JPasswordField passwordFieldSenha;
    private JButton btnLogin;

    public LoginPessoaViewImpl(PessoaController pessoaController, LivroController livroController) {
        this.pessoaController = pessoaController;
        this.livroController = livroController;

        initializeUI();
    }

    void initializeUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelCpf = new JLabel("CPF:");
        textFieldCpf = new JTextField();
        textFieldCpf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        labelSenha = new JLabel("Senha:");
        passwordFieldSenha = new JPasswordField();
        passwordFieldSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        btnLogin = new JButton("Login");
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnLogin);

        formPanel.add(labelCpf);
        formPanel.add(textFieldCpf);
        formPanel.add(labelSenha);
        formPanel.add(passwordFieldSenha);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    @Override
    public void abrir() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login")) {
            boolean sucesso = pessoaController.login(textFieldCpf.getText(), new String(passwordFieldSenha.getPassword()));
            Pessoa pessoa = pessoaController.pesquisarPessoa(0, "", textFieldCpf.getText(), "");


            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");

                this.dispose();

                SwingUtilities.invokeLater(() -> {
                    LoginSucessoViewImpl loginSucessoView = new LoginSucessoViewImpl(pessoaController, livroController, pessoa);
                    loginSucessoView.setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "CPF ou senha incorretos!");
            }
        }
    }
}
