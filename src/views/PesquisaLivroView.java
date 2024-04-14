package views;
import javax.swing.*;
import java.awt.*;
import models.Database.LivroDatabase;
import models.Livro.Livro;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PesquisaLivroView extends JFrame implements ActionListener {

    private JTextField textFieldPesquisa;
    private JPanel resultadosPanel;

    public PesquisaLivroView() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Ações");
        JMenuItem menuItem = new JMenuItem("Adicionar Livro");
        menuItem.setActionCommand("adicionarLivro");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);
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

    private void pesquisarLivros() {
        String pesquisa = textFieldPesquisa.getText();
        List<Livro> livrosEncontrados = LivroDatabase.pesquisarLivro(pesquisa, pesquisa, pesquisa, pesquisa);
        JPanel novoPanel = new JPanel();
        novoPanel.setLayout(new BoxLayout(novoPanel, BoxLayout.Y_AXIS));

        ImageIcon lapisIcon = new ImageIcon(getClass().getResource("/media/lapis.png"));
        ImageIcon lixeiraIcon = new ImageIcon(getClass().getResource("/media/lixeira.png"));

        JPanel escritaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEscritaTitulo = new JLabel("Título");
        JLabel lblEscritaCategoria = new JLabel("Categoria");
        JLabel lblEscritaAutor = new JLabel("Autor");
        JLabel lblEscritaEditor = new JLabel("Editora");
        JLabel lblEscritaAcoes = new JLabel("Ações");

        lblEscritaTitulo.setPreferredSize(new Dimension(100, 20));
        lblEscritaCategoria.setPreferredSize(new Dimension(100, 20));
        lblEscritaAutor.setPreferredSize(new Dimension(100, 20));
        lblEscritaEditor.setPreferredSize(new Dimension(100, 20));
        lblEscritaAcoes.setPreferredSize(new Dimension(100, 20));

        escritaPanel.add(lblEscritaTitulo);
        escritaPanel.add(lblEscritaCategoria);
        escritaPanel.add(lblEscritaAutor);
        escritaPanel.add(lblEscritaEditor);
        escritaPanel.add(lblEscritaAcoes);
        escritaPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        novoPanel.add(escritaPanel);

        for (Livro livro : livrosEncontrados) {
            JPanel livroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblTitulo = new JLabel(livro.getTitulo());
            JLabel lblCategoria = new JLabel(livro.getCategoria());
            JLabel lblAutor = new JLabel(livro.getAutor());
            JLabel lblEditora = new JLabel(livro.getEditora());

            // Definir a largura fixa dos elementos de livro
            lblTitulo.setPreferredSize(new Dimension(100, 15));
            lblCategoria.setPreferredSize(new Dimension(100, 15));
            lblAutor.setPreferredSize(new Dimension(100, 15));
            lblEditora.setPreferredSize(new Dimension(100, 15));

            livroPanel.add(lblTitulo);
            livroPanel.add(lblCategoria);
            livroPanel.add(lblAutor);
            livroPanel.add(lblEditora);

            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            JButton btnEditar = new JButton(lapisIcon);
            JButton btnExcluir = new JButton(lixeiraIcon);

            btnEditar.setActionCommand("editar:" + livro.getIdLivro());
            btnExcluir.setActionCommand("excluir:" + livro.getIdLivro());

            // Definir a largura fixa dos botões
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

        resultadosPanel.removeAll();
        resultadosPanel.add(novoPanel);
        resultadosPanel.revalidate();
        resultadosPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "pesquisar":
                pesquisarLivros();
                break;
            case "adicionarLivro":
                SwingUtilities.invokeLater(() -> {
                    CadastroLivroView cadastroLivroView = new CadastroLivroView(null);
                    cadastroLivroView.setVisible(true);
                    this.dispose();
                });
                break;
            default:
                if (e.getActionCommand().startsWith("editar:")) {
                    String idLivroStr = e.getActionCommand().substring("editar:".length());
                    int idLivroEditar = Integer.parseInt(idLivroStr);
                    Livro livroEditar = LivroDatabase.buscarLivroPorId(idLivroEditar);

                    if (livroEditar != null) {
                        SwingUtilities.invokeLater(() -> {
                            CadastroLivroView cadastroLivroView = new CadastroLivroView(livroEditar);
                            cadastroLivroView.setVisible(true);
                            this.dispose();
                        });
                    }
                } else if (e.getActionCommand().startsWith("excluir:")) {
                    String idLivroStr = e.getActionCommand().substring("excluir:".length());
                    int idLivroExcluir = Integer.parseInt(idLivroStr);
                    LivroDatabase.excluirLivro(idLivroExcluir);
                    pesquisarLivros();
                }
        }
    }
}