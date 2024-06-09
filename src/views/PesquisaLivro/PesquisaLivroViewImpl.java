package views.PesquisaLivro;

import controller.LivroController;
import controller.PessoaController;
import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroStatus;
import views.CadastroLivro.CadastroLivroViewImpl;
import views.CadastroPessoa.CadastroPessoaViewImpl;
import views.EmprestimoLivro.EmprestimoLivroViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PesquisaLivroViewImpl extends JFrame implements ActionListener, PesquisaLivroView {

    private JTextField textFieldPesquisa;
    private final LivroController livroController;
    private final PessoaController pessoaController;
    private JPanel resultadosPanel;

    public PesquisaLivroViewImpl(LivroController livroController, PessoaController pessoaController) {
        initializeUI();

        this.livroController = livroController;
        this.pessoaController = pessoaController;

        livroController.setPesquisaView(this);
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

        setTitle("Pesquisar Livros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout());
        textFieldPesquisa = new JTextField();
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setActionCommand("pesquisar");
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

    private void pesquisarLivros(boolean validaBuscaDeLivros) {
        String pesquisa = textFieldPesquisa.getText();

        ArrayList<Livro> livrosEncontrados = livroController.pesquisarLivro(pesquisa, pesquisa, pesquisa, pesquisa);
        JPanel novoPanel = new JPanel();
        novoPanel.setLayout(new BoxLayout(novoPanel, BoxLayout.Y_AXIS));

        if (livrosEncontrados.isEmpty() && validaBuscaDeLivros) {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado.");
            resultadosPanel.removeAll();
            resultadosPanel.add(novoPanel);
            resultadosPanel.revalidate();
            resultadosPanel.repaint();
            return;
        }

        JPanel escritaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEscritaTitulo = new JLabel("Título");
        JLabel lblEscritaCategoria = new JLabel("Categoria");
        JLabel lblEscritaAutor = new JLabel("Autor");
        JLabel lblEscritaStatus = new JLabel("Status");
        JLabel lblEscritaAcoes = new JLabel("Ações");

        lblEscritaTitulo.setPreferredSize(new Dimension(100, 20));
        lblEscritaCategoria.setPreferredSize(new Dimension(100, 20));
        lblEscritaAutor.setPreferredSize(new Dimension(100, 20));
        lblEscritaStatus.setPreferredSize(new Dimension(100, 20));
        lblEscritaAcoes.setPreferredSize(new Dimension(100, 20));

        escritaPanel.add(lblEscritaTitulo);
        escritaPanel.add(lblEscritaCategoria);
        escritaPanel.add(lblEscritaAutor);
        escritaPanel.add(lblEscritaStatus);
        escritaPanel.add(lblEscritaAcoes);
        escritaPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        novoPanel.add(escritaPanel);

        for (Livro livro : livrosEncontrados) {
            carregarLivro(livro, novoPanel);
        }

        resultadosPanel.removeAll();
        resultadosPanel.add(novoPanel);
        resultadosPanel.revalidate();
        resultadosPanel.repaint();
    }

    public void carregarLivro(Livro livro, JPanel novoPanel) {
        ImageIcon lapisIcon = new ImageIcon(getClass().getResource("/media/lapis.png"));
        ImageIcon lixeiraIcon = new ImageIcon(getClass().getResource("/media/lixeira.png"));
        ImageIcon livroEmprestimoIcon = new ImageIcon(getClass().getResource("/media/emprestar-livro.png"));
        ImageIcon livroDevolucaoIcon = new ImageIcon(getClass().getResource("/media/devolver-livro.png"));

        LivroCategoria livroCategoria = livroController.pesquisarCategoria(livro.getIdLivroCategoria(), null);
        LivroStatus livrostatus = livroController.pesquisarUmStatus(livro.getIdLivroStatus(), null);

        JPanel livroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitulo = new JLabel(livro.getTitulo());
        JLabel lblCategoria = new JLabel(livroCategoria.getDescricao());
        JLabel lblAutor = new JLabel(livro.getAutor());
        JLabel lblStatus = new JLabel(livrostatus.getDescricao());

        lblTitulo.setPreferredSize(new Dimension(100, 15));
        lblCategoria.setPreferredSize(new Dimension(100, 15));
        lblAutor.setPreferredSize(new Dimension(100, 15));
        lblStatus.setPreferredSize(new Dimension(100, 15));

        livroPanel.add(lblTitulo);
        livroPanel.add(lblCategoria);
        livroPanel.add(lblAutor);
        livroPanel.add(lblStatus);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnEditar = new JButton(lapisIcon);
        JButton btnExcluir = new JButton(lixeiraIcon);
        JButton btnEmprestarLivro = new JButton(livroEmprestimoIcon);
        JButton btnDevolverLivro = new JButton(livroDevolucaoIcon);

        btnEditar.setActionCommand("editar:" + livro.getIdLivro());
        btnExcluir.setActionCommand("excluir:" + livro.getIdLivro());

        btnEditar.setPreferredSize(new Dimension(20, 20));
        btnExcluir.setPreferredSize(new Dimension(20, 20));

        btnEditar.addActionListener(this);
        btnExcluir.addActionListener(this);

        btnPanel.add(btnEditar);
        btnPanel.add(btnExcluir);

        if(livro.getIdLivroStatus() != 2) {
            btnEmprestarLivro.setPreferredSize(new Dimension(20, 20));
            btnEmprestarLivro.addActionListener(this);
            btnEmprestarLivro.setActionCommand("emprestar:" + livro.getIdLivro());
            btnPanel.add(btnEmprestarLivro);
        } else {
            btnDevolverLivro.setPreferredSize(new Dimension(20, 20));
            btnDevolverLivro.addActionListener(this);
            btnDevolverLivro.setActionCommand("devolver:" + livro.getIdLivro());
            btnPanel.add(btnDevolverLivro);
        }

        livroPanel.add(btnPanel);
        novoPanel.add(livroPanel);

        novoPanel.add(Box.createRigidArea(new Dimension(0, 1)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "pesquisar":
                pesquisarLivros(true);
                break;
            case "adicionarLivro":
                SwingUtilities.invokeLater(() -> {
                    CadastroLivroViewImpl cadastroLivroViewImpl = new CadastroLivroViewImpl(livroController, pessoaController, 0);
                    cadastroLivroViewImpl.setVisible(true);
                    this.dispose();
                });
                break;
            case "adicionarPessoa":
                SwingUtilities.invokeLater(() -> {
                    CadastroPessoaViewImpl cadastroPessoaViewImpl = new CadastroPessoaViewImpl(pessoaController, livroController, 0);
                    cadastroPessoaViewImpl.setVisible(true);
                    this.dispose();
                });
                break;
            default:
                if (e.getActionCommand().startsWith("editar:")) {
                    String idLivroStr = e.getActionCommand().substring("editar:".length());
                    int idLivroEditar = Integer.parseInt(idLivroStr);

                    SwingUtilities.invokeLater(() -> {
                        CadastroLivroViewImpl cadastroLivroViewImpl = new CadastroLivroViewImpl(livroController, pessoaController, idLivroEditar);
                        cadastroLivroViewImpl.setVisible(true);
                        this.dispose();
                    });
                } else if (e.getActionCommand().startsWith("excluir:")) {
                    String idLivroStr = e.getActionCommand().substring("excluir:".length());
                    int idLivroExcluir = Integer.parseInt(idLivroStr);

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (opcao == JOptionPane.YES_OPTION) {
                        livroController.excluirLivro(idLivroExcluir);
                        pesquisarLivros(false);
                    }
                } else if (e.getActionCommand().startsWith("emprestar:")) {
                    String idLivroStr = e.getActionCommand().substring("emprestar:".length());
                    int idLivroEmprestar = Integer.parseInt(idLivroStr);

                    SwingUtilities.invokeLater(() -> {
                        EmprestimoLivroViewImpl emprestimoLivroViewImpl = new EmprestimoLivroViewImpl(livroController, pessoaController, idLivroEmprestar);
                        emprestimoLivroViewImpl.setVisible(true);
                        this.dispose();
                    });
                } else if (e.getActionCommand().startsWith("devolver:")) {
                    String idLivroStr = e.getActionCommand().substring("devolver:".length());
                    int idLivroDevolver = Integer.parseInt(idLivroStr);

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja devolver o livro?", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (opcao == JOptionPane.YES_OPTION) {
                        livroController.devolverLivro(idLivroDevolver);
                        pesquisarLivros(false);
                    }
                }
        }
    }

    @Override
    public void abrir() {
        setVisible(true);
    }
}