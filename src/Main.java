import di.ServiceLocator;
import models.Database.LivroDatabase;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServiceLocator.getInstance().getPesquisaLivroView().abrir();
        });
    }
}