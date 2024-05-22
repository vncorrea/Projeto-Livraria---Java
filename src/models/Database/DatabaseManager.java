package models.Database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseManager {
    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        return sessionFactory;
    }

    public static void createSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();

        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(models.Livro.Livro.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}