package models.Database;

import models.Livro.Livro;
import models.Livro.LivroCategoria;
import models.Livro.LivroEmprestimo;
import models.Livro.LivroStatus;
import models.Pessoa.Colaborador;
import models.Pessoa.Pessoa;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseManager {

    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        if(sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Livro.class)
                    .addAnnotatedClass(LivroCategoria.class)
                    .addAnnotatedClass(LivroStatus.class)
                    .addAnnotatedClass(LivroEmprestimo.class)
                    .addAnnotatedClass(Pessoa.class)
                    .addAnnotatedClass(Colaborador.class)
                    .buildMetadata()
                    .buildSessionFactory();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}