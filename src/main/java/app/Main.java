package app;

import javax.swing.SwingUtilities;
import app.view.TelaLogin;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
