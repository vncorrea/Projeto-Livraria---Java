package models.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    public static void createDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS livro_status (id_livro_status INTEGER PRIMARY KEY, descricao TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS livro_categoria (id_livro_categoria INTEGER PRIMARY KEY, descricao TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS livro (id_livro INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, editora TEXT, sinopse TEXT, paginas INTEGER, isbn TEXT, data_publicacao DATE, data_cadastro DATE id_livro_categoria INTEGER, id_livro_status INTEGER, FOREIGN KEY (id_livro_categoria) REFERENCES livro_categoria(id_livro_categoria), FOREIGN KEY (id_livro_status) REFERENCES livro_status(id_livro_status))");
            statement.execute("CREATE TABLE IF NOT EXISTS pessoa (id_pessoa INTEGER PRIMARY KEY, nome TEXT, logradouro TEXT, cpf TEXT, cidade TEXT, estado TEXT, uf TEXT, email TEXT, telefone TEXT, data_nascimento DATE, data_cadastro DATE)");
            statement.execute("CREATE TABLE IF NOT EXISTS livro_emprestimo (id_livro_emprestimo INTEGER PRIMARY KEY, id_livro INTEGER, id_pessoa INTEGER, multa BOOLEAN, data_emprestimo DATE, data_devolucao DATE, FOREIGN KEY (id_livro) REFERENCES livro(id_livro), FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa))");
            statement.execute("CREATE TABLE IF NOT EXISTS colaborador (id_colaborador INTEGER PRIMARY KEY, cargo TEXT, id_pessoa INTEGER  data_registro DATE, pis TEXT, rg TEXT, senha TEXT, FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa))");
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
