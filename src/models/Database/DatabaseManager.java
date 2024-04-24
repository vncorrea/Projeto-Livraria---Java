package models.Database;

import models.Livro.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:biblioteca.db";

    public static void createDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS livro_status (id_livro_status INTEGER PRIMARY KEY, descricao TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS livro_categoria (id_livro_categoria INTEGER PRIMARY KEY, descricao TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS livro (id_livro INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, editora TEXT, sinopse TEXT, paginas INTEGER, isbn TEXT, data_publicacao DATE, data_cadastro DATE, id_livro_categoria INTEGER, id_livro_status INTEGER, FOREIGN KEY (id_livro_categoria) REFERENCES livro_categoria(id_livro_categoria), FOREIGN KEY (id_livro_status) REFERENCES livro_status(id_livro_status))");
            statement.execute("CREATE TABLE IF NOT EXISTS pessoa (id_pessoa INTEGER PRIMARY KEY, nome TEXT, logradouro TEXT, cpf TEXT, cidade TEXT, estado TEXT, uf TEXT, email TEXT, telefone TEXT, data_nascimento DATE, data_cadastro DATE)");
            statement.execute("CREATE TABLE IF NOT EXISTS livro_emprestimo (id_livro_emprestimo INTEGER PRIMARY KEY, id_livro INTEGER, id_pessoa INTEGER, multa BOOLEAN, data_emprestimo DATE, data_devolucao DATE, FOREIGN KEY (id_livro) REFERENCES livro(id_livro), FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa))");
            statement.execute("CREATE TABLE IF NOT EXISTS colaborador (id_colaborador INTEGER PRIMARY KEY, cargo TEXT, id_pessoa INTEGER, data_registro DATE, pis TEXT, rg TEXT, senha TEXT, FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa))");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Database created successfully");
        }
    }

    public static void criarLivro(String titulo, String autor, String editora,
                                  String sinopse, int paginas, int idLivroCategoria,
                                  String isbn, int idLivroStatus) {
        try {
            Connection connection = DriverManager.getConnection(URL);
            String sql = "INSERT INTO livro (id_livro, titulo, autor, editora, sinopse, paginas, " +
                    "id_livro_categoria, isbn, id_livro_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, titulo);
            statement.setString(3, autor);
            statement.setString(4, editora);
            statement.setString(5, sinopse);
            statement.setInt(6, paginas);
            statement.setInt(7, idLivroCategoria);
            statement.setString(8, isbn);
            statement.setInt(9, idLivroStatus);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void criarCategoria(String descricao) {
        try {
            Connection connection = DriverManager.getConnection(URL);
            String sql = "INSERT INTO livro_categoria (id_livro_categoria, descricao) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, descricao);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void criarStatus(String descricao) {
        try {
            Connection connection = DriverManager.getConnection(URL);
            String sql = "INSERT INTO livro_status (id_livro_status, descricao) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, descricao);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarLivro(int idLivro, String titulo, String autor, String editora,
                                   String sinopse, int paginas, int idLivroCategoria,
                                   String isbn, int prazoEmprestimo, Date dataPublicacao,
                                   int idLivroStatus) {
        try {
            Connection connection = DriverManager.getConnection(URL);
            String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, sinopse = ?, paginas = ?, " +
                    "id_livro_categoria = ?, isbn = ?, prazo_emprestimo = ?, data_publicacao = ?, id_livro_status = ? WHERE id_livro = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setString(3, editora);
            statement.setString(4, sinopse);
            statement.setInt(5, paginas);
            statement.setInt(6, idLivroCategoria);
            statement.setString(7, isbn);
            statement.setInt(8, prazoEmprestimo);
            statement.setDate(9, dataPublicacao);
            statement.setInt(10, idLivroStatus);
            statement.setInt(11, idLivro);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Livro> pesquisarLivros(String titulo, String autor, String editora, String isbn, int idLivroCategoria, int idLivroStatus) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM livro WHERE titulo LIKE ? AND autor LIKE ? AND editora LIKE ? AND isbn LIKE ? AND id_livro_categoria = ? AND id_livro_status = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + titulo + "%");
                statement.setString(2, "%" + autor + "%");
                statement.setString(3, "%" + editora + "%");
                statement.setString(4, "%" + isbn + "%");
                statement.setInt(5, idLivroCategoria);
                statement.setInt(6, idLivroStatus);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Livro livro = new Livro(
                                resultSet.getInt("id_livro"),
                                resultSet.getString("titulo"),
                                resultSet.getString("autor"),
                                resultSet.getString("editora"),
                                resultSet.getString("sinopse"),
                                resultSet.getInt("paginas"),
                                resultSet.getInt("id_livro_categoria"),
                                resultSet.getString("isbn"),
                                resultSet.getInt("prazo_emprestimo"),
                                resultSet.getDate("data_publicacao"),
                                resultSet.getDate("data_cadastro"),
                                resultSet.getInt("id_livro_status")
                        );

                        livrosEncontrados.add(livro);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosEncontrados;
    }
}
