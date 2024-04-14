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
    private DefaultTableModel modeloTabelaExibicaoLivros;
    private JTable tabelaLivros;

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

        resultadosPanel = new JPanel(new BorderLayout());
        resultadosPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultadosPanel), BorderLayout.CENTER);

        String[] colunas = {"Título", "Autor", "Editora", "Status", "Ações"};
        modeloTabelaExibicaoLivros = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaLivros = new JTable(modeloTabelaExibicaoLivros);
        resultadosPanel.add(new JScrollPane(tabelaLivros), BorderLayout.CENTER);

        add(panel);

        add(panel);
    }

    private void pesquisarLivros() {
        String pesquisa = textFieldPesquisa.getText();
        List<Livro> livrosEncontrados = LivroDatabase.pesquisarLivro(pesquisa, pesquisa, pesquisa, pesquisa);

        modeloTabelaExibicaoLivros.setRowCount(0);

        for (Livro livro : livrosEncontrados) {
            modeloTabelaExibicaoLivros.addRow(new Object[]{
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getEditora(),
                    livro.getLivroStatusDescricao()
            });

            JButton btnEditar = new JButton(new ImageIcon("media/lapis.png"));
            JButton btnExcluir = new JButton(new ImageIcon("media/lixeira.png"));

            btnEditar.setActionCommand("editar:" + livro.getIdLivro());
            btnExcluir.setActionCommand("excluir:" + livro.getIdLivro());

            btnEditar.setPreferredSize(new Dimension(20, 20));
            btnExcluir.setPreferredSize(new Dimension(20, 20));
            btnEditar.addActionListener(this);
            btnExcluir.addActionListener(this);
        }

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