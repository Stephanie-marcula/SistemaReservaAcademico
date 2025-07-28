package app.view;

import app.controller.UsuarioController;
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private UsuarioController usuarioController;

    public TelaLogin() {
        usuarioController = new UsuarioController();
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        panel.add(txtSenha);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.addActionListener(e -> autenticarUsuario());
        panel.add(new JLabel()); // espaço vazio
        panel.add(btnLogin);

        add(panel);
    }

    private void autenticarUsuario() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        Usuario usuario = usuarioController.autenticar(email, senha);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

            dispose(); // fecha tela de login

            if ("coordenador".equalsIgnoreCase(usuario.getTipo())) {
                new TelaCoordenador().setVisible(true);
            } else {
                new TelaPrincipal(usuario).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha inválidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
