package app.controller;

import app.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios;

    public UsuarioController() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "admin@teste.com", "1234", "coordenador"));
        usuarios.add(new Usuario("aluno", "aluno@teste.com", "abcd", "aluno"));
    }

    public Usuario autenticar(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
