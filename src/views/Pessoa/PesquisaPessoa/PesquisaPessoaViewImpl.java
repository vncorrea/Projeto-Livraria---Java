package views.Pessoa.PesquisaPessoa;

import controller.LivroController;
import controller.PessoaController;
import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import models.Pessoa.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PesquisaPessoaViewImpl extends JFrame implements PesquisaPessoaView, ActionListener {
    private LivroController livroController;
    private PessoaController pessoaController;

    private JTextField textFieldPesquisa;

    private JPanel resultadosPanel;

    public PesquisaPessoaViewImpl(LivroController livroController, PessoaController pessoaController) {
        this.livroController = livroController;
        this.pessoaController = pessoaController;

        initializeUI();
    }

    private void initializeUI() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuLivro = new JMenu("Livro");
        JMenu menuPessoa = new JMenu("Pessoa");
        JMenuItem menuItemAdicionarLivro = new JMenuItem("Adicionar Livro");
        JMenuItem menuItemAdicionarPessoa = new JMenuItem("Cadastrar Pessoa");
        menuItemAdicionarLivro.setActionCommand("adicionarLivro");
        menuItemAdicionarPessoa.setActionCommand("adicionarPessoa");
        menuItemAdicionarLivro.addActionListener(this);
        menuItemAdicionarPessoa.addActionListener(this);
        menuLivro.add(menuItemAdicionarLivro);
        menuPessoa.add(menuItemAdicionarPessoa);
        menuBar.add(menuLivro);
        menuBar.add(menuPessoa);
        setJMenuBar(menuBar);

        setTitle("Pesquisar Pessoas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout());
        textFieldPesquisa = new JTextField();
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setActionCommand("pesquisarPessoa");
        btnPesquisar.addActionListener(this);
        searchPanel.add(textFieldPesquisa, BorderLayout.CENTER);
        searchPanel.add(btnPesquisar, BorderLayout.EAST);

        resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        resultadosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultadosPanel), BorderLayout.CENTER);

        add(panel);
    }

    private void pesquisarPessoas() {
        String pesquisa = textFieldPesquisa.getText();

        ArrayList<Pessoa> pessoasEncontradas = pessoaController.pesquisarPessoas(pesquisa, pesquisa, pesquisa);
        JPanel novoPanel = new JPanel();
        novoPanel.setLayout(new BoxLayout(novoPanel, BoxLayout.Y_AXIS));

        if (pessoasEncontradas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma pessoa encontrada.");
            resultadosPanel.removeAll();
            resultadosPanel.add(novoPanel);
            resultadosPanel.revalidate();
            resultadosPanel.repaint();
            return;
        }

        JPanel escritaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEscritaNome = new JLabel("Nome");
        JLabel lblEscritaEmail = new JLabel("Email");
        JLabel lblEscritaTelefone = new JLabel("Telefone");
        JLabel lblEscritaColaborador = new JLabel("Colaborador");
        JLabel lblEscritaAcoes = new JLabel("Ações");

        lblEscritaNome.setPreferredSize(new Dimension(100, 20));
        lblEscritaEmail.setPreferredSize(new Dimension(100, 20));
        lblEscritaTelefone.setPreferredSize(new Dimension(100, 20));
        lblEscritaColaborador.setPreferredSize(new Dimension(100, 20));
        lblEscritaAcoes.setPreferredSize(new Dimension(100, 20));

        escritaPanel.add(lblEscritaNome);
        escritaPanel.add(lblEscritaEmail);
        escritaPanel.add(lblEscritaTelefone);
        escritaPanel.add(lblEscritaColaborador);
        escritaPanel.add(lblEscritaAcoes);
        escritaPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        novoPanel.add(escritaPanel);

        for (Pessoa pessoa : pessoasEncontradas) {
            carregarPessoa(pessoa, novoPanel);
        }

        resultadosPanel.removeAll();
        resultadosPanel.add(novoPanel);
        resultadosPanel.revalidate();
        resultadosPanel.repaint();
    }

    public void carregarPessoa(Pessoa pessoa, JPanel novoPanel) {
        ImageIcon lapisIcon = new ImageIcon(getClass().getResource("/media/lapis.png"));
        ImageIcon lixeiraIcon = new ImageIcon(getClass().getResource("/media/lixeira.png"));

        JPanel livroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNome = new JLabel(pessoa.getNome());
        JLabel lblEmail = new JLabel(pessoa.getEmail());
        JLabel lblTelefone = new JLabel(pessoa.getTelefone());
        JLabel lblColaborador = new JLabel(pessoa.isColaborador() ? "Sim" : "Não");

        lblNome.setPreferredSize(new Dimension(100, 15));
        lblEmail.setPreferredSize(new Dimension(100, 15));
        lblTelefone.setPreferredSize(new Dimension(100, 15));
        lblColaborador.setPreferredSize(new Dimension(100, 15));

        livroPanel.add(lblNome);
        livroPanel.add(lblEmail);
        livroPanel.add(lblTelefone);
        livroPanel.add(lblColaborador);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnEditar = new JButton(lapisIcon);
        JButton btnExcluir = new JButton(lixeiraIcon);

        btnEditar.setActionCommand("editar:" + pessoa.getIdPessoa());
        btnExcluir.setActionCommand("excluir:" + pessoa.getIdPessoa());

        btnEditar.setPreferredSize(new Dimension(20, 20));
        btnExcluir.setPreferredSize(new Dimension(20, 20));

        btnEditar.addActionListener(this);
        btnExcluir.addActionListener(this);

        btnPanel.add(btnEditar);
        btnPanel.add(btnExcluir);

        livroPanel.add(btnPanel);
        novoPanel.add(livroPanel);

        novoPanel.add(Box.createRigidArea(new Dimension(0, 1)));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ("pesquisarPessoa".equals(e.getActionCommand())) {
            pesquisarPessoas();
        } else if (e.getActionCommand().startsWith("editar:")) {
//            int idPessoa = Integer.parseInt(e.getActionCommand().split(":")[1]);
//            Pessoa pessoa = pessoaController.getPessoa(idPessoa);
//            SwingUtilities.invokeLater(() -> {
//                CadastrarPessoaViewImpl cadastrarPessoaView = new CadastrarPessoaViewImpl(pessoaController, pessoa);
//                cadastrarPessoaView.setVisible(true);
//                this.dispose();
//            });
        } else if (e.getActionCommand().startsWith("excluir:")) {
//            int idPessoa = Integer.parseInt(e.getActionCommand().split(":")[1]);
//            pessoaController.excluirPessoa(idPessoa);
//            pesquisarPessoas();
        } else if ("adicionarLivro".equals(e.getActionCommand())) {
//            SwingUtilities.invokeLater(() -> {
//                CadastrarLivroViewImpl cadastrarLivroView = new CadastrarLivroViewImpl(livroController, pessoaController);
//                cadastrarLivroView.setVisible(true);
//                this.dispose();
//            });
        } else if ("adicionarPessoa".equals(e.getActionCommand())) {
//            SwingUtilities.invokeLater(() -> {
//                CadastrarPessoaViewImpl cadastrarPessoaView = new CadastrarPessoaViewImpl(pessoaController);
//                cadastrarPessoaView.setVisible(true);
//                this.dispose();
//            });
        }
    }

    @Override
    public void abrir() {
        this.setVisible(true);
    }
}
