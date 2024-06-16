package views.Pessoa.LoginSucesso;

import controller.LivroController;
import controller.PessoaController;
import models.Pessoa.Colaborador;
import models.Pessoa.Pessoa;
import views.Livro.PesquisaLivro.PesquisaLivroViewImpl;
import views.Pessoa.PesquisaPessoa.PesquisaPessoaViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSucessoViewImpl extends JFrame implements ActionListener, LoginSucessoView {

    private final PessoaController pessoaController;
    private Pessoa colaboradorLogado;
    private final LivroController livroController;

    public LoginSucessoViewImpl(PessoaController pessoaController, LivroController livroController, Pessoa pessoa) {
        this.pessoaController = pessoaController;
        this.livroController = livroController;
        this.colaboradorLogado = pessoa;

        initializeUI(pessoa);
    }

    void initializeUI(Pessoa pessoa) {
        Colaborador colaborador = null;

        if (pessoa instanceof Colaborador) {
            colaborador = (Colaborador) pessoa;
        }

        setTitle("Tela principal");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Bem Vindo, " + pessoa.getNome() + "!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        if (colaborador != null && colaborador.isAdministrador()) {
            JButton pessoaButton = new JButton("Pessoa");
            pessoaButton.setFont(new Font("Arial", Font.BOLD, 24));
            pessoaButton.setActionCommand("pessoa");
            pessoaButton.addActionListener(this);
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            buttonPanel.add(pessoaButton, constraints);
        }

        JButton livroButton = new JButton("Livro");
        livroButton.setFont(new Font("Arial", Font.BOLD, 24));
        livroButton.setActionCommand("livro");
        livroButton.addActionListener(this);
        constraints.gridx = 1;
        buttonPanel.add(livroButton, constraints);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    @Override
    public void abrir() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("pessoa".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(() -> {
                PesquisaPessoaViewImpl pesquisaPessoaView = new PesquisaPessoaViewImpl(livroController, pessoaController, colaboradorLogado);
                pesquisaPessoaView.setVisible(true);
                this.dispose();
            });
        } else if ("livro".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(() -> {
                PesquisaLivroViewImpl pesquisaLivroView = new PesquisaLivroViewImpl(livroController, pessoaController, colaboradorLogado);
                pesquisaLivroView.setVisible(true);
                this.dispose();
            });
        }
    }
}
