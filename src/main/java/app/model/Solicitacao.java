package app.model;

import java.io.Serializable;

public class Solicitacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Usuario aluno; // agora é um objeto Usuario
    private String data;
    private String status;
    private String descricao;

    public Solicitacao(Long id, Usuario aluno, String data, String status) {
        this.id = id;
        this.aluno = aluno;
        this.data = data;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getAluno() { return aluno; }
    public void setAluno(Usuario aluno) { this.aluno = aluno; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
