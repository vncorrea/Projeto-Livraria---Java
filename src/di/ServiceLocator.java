package di;

import controller.LivroController;
import controller.LivroControllerImpl;
import controller.PessoaController;
import controller.PessoaControllerImpl;
import models.Database.LivroDAO;
import models.Database.LivroDatabase;
import models.Database.PessoaDAO;
import models.Database.PessoaDatabase;
import views.Livro.CadastroLivro.CadastroLivroView;
import views.Livro.CadastroLivro.CadastroLivroViewImpl;
import views.Pessoa.CadastroPessoa.CadastroPessoaView;
import views.Pessoa.CadastroPessoa.CadastroPessoaViewImpl;
import views.Pessoa.Login.LoginPessoaView;
import views.Pessoa.Login.LoginPessoaViewImpl;
import views.Livro.PesquisaLivro.PesquisaLivroView;
import views.Livro.PesquisaLivro.PesquisaLivroViewImpl;

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

    private LivroDAO getLivroDAO() {
        if(livroDAO == null) {
            livroDAO = new LivroDAO();
        }

        return livroDAO;
    }

    private PessoaDAO pessoaDAO;
    private PessoaDAO getPessoaDAO() {
        if(pessoaDAO == null) {
            pessoaDAO = new PessoaDAO();
        }

        return pessoaDAO;
    }

    public LivroDatabase getLivroDatabase() {
        return getLivroDAO();
    }

    public PessoaDatabase getPessoaDatabase() {
        return getPessoaDAO();
    }

    public LivroController getLivroController() {
        return new LivroControllerImpl(getLivroDatabase());
    }

    public PessoaController getPessoaController() {
        return new PessoaControllerImpl(getPessoaDatabase());
    }

    public CadastroLivroView getCadastroLivroView() {
        return new CadastroLivroViewImpl(getLivroController(), getPessoaController(), 0);
    }

    public PesquisaLivroView getPesquisaLivroView() {
        return new PesquisaLivroViewImpl(getLivroController(), getPessoaController());
    }

    public CadastroPessoaView getCadastroPessoaView() {
        return new CadastroPessoaViewImpl(getPessoaController(), getLivroController(), 0);
    }

    public LoginPessoaView getLoginPessoaView() {
        return new LoginPessoaViewImpl(getPessoaController(), getLivroController());
    }
}