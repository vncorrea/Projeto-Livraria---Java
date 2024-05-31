package di;

import controller.LivroController;
import controller.LivroControllerImpl;
import models.Database.LivroDAO;
import models.Database.LivroDatabase;
import views.CadastroLivroView;
import views.CadastroLivroViewImpl;

public class ServiceLocator {

    // Instancia para o singleton
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if(instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    private LivroDAO livroDAO;

    private LivroDAO getTaskDao() {
        if(livroDAO == null) {
            livroDAO = new LivroDAO();
        }

        return livroDAO;
    }

    public LivroDatabase getLivroDatabase() {
        return getTaskDao();
    }

    public LivroController getLivroController() {
        return new LivroControllerImpl(getLivroDatabase());
    }

    public CadastroLivroView getCadastroLivroView() {
        return new CadastroLivroViewImpl(getLivroController());
    }
}