package app.controller;

import app.model.Solicitacao;
import app.service.EmailService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoController {
    private List<Solicitacao> solicitacoes;
    private static final String ARQUIVO = "solicitacoes.dat";
    private EmailService emailService;
    public SolicitacaoController() {
        this.solicitacoes = carregarSolicitacoes();
        this.emailService = new EmailService();
    }

    public void adicionarSolicitacao(Solicitacao s) {
        solicitacoes.add(s);
        salvarSolicitacoes();
    }

    public List<Solicitacao> listarSolicitacoes() {
        return solicitacoes;
    }

    public void aprovarSolicitacao(Long id) {
        for (Solicitacao s : solicitacoes) {
            if (s.getId().equals(id)) {
                s.setStatus("Aprovado");
                salvarSolicitacoes();
                break;
            }
        }
    }

    public void rejeitarSolicitacao(Long id) {
        for (Solicitacao s : solicitacoes) {
            if (s.getId().equals(id)) {
                s.setStatus("Rejeitado");
                salvarSolicitacoes();
                break;
            }
        }
    }

    public void aprovarSolicitacaoComEmail(Long id) {
    aprovarSolicitacao(id);
    Solicitacao s = buscarPorId(id);
    if (s != null && s.getAluno() != null) {
        String email = s.getAluno().getEmail();
        emailService.enviarEmail(email, "Solicitação Aprovada", "Sua solicitação foi aprovada.");
    }
}

public void rejeitarSolicitacaoComEmail(Long id) {
    rejeitarSolicitacao(id);
    Solicitacao s = buscarPorId(id);
    if (s != null && s.getAluno() != null) {
        String email = s.getAluno().getEmail();
        emailService.enviarEmail(email, "Solicitação Rejeitada", "Sua solicitação foi rejeitada.");
    }
}

    private void salvarSolicitacoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(solicitacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Solicitacao buscarPorId(Long id) {
    for (Solicitacao s : listarSolicitacoes()) {
        if (s.getId().equals(id)) {
            return s;
        }
    }
    return null;
}

    @SuppressWarnings("unchecked")
    private List<Solicitacao> carregarSolicitacoes() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists())
            return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Solicitacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
