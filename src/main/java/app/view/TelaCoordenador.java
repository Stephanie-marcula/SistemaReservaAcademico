package app.view;

import app.controller.SolicitacaoController;
import app.model.Solicitacao;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaCoordenador extends JFrame {
    private SolicitacaoController controller;
    private JTable tabela;
    private DefaultTableModel tableModel;

    public TelaCoordenador() {
        controller = new SolicitacaoController();
        setTitle("Área do Coordenador");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitulo = new JLabel("Solicitações dos Alunos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Modelo de tabela com nova coluna de descrição
        tableModel = new DefaultTableModel(new String[]{"ID", "Aluno", "Data", "Status", "Descrição"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(tableModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Botões de ação
        JButton btnAprovar = new JButton("Aprovar");
        btnAprovar.addActionListener(e -> aprovarSelecionada());

        JButton btnRejeitar = new JButton("Rejeitar");
        btnRejeitar.addActionListener(e -> rejeitarSelecionada());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnAprovar);
        panel.add(btnRejeitar);
        add(panel, BorderLayout.SOUTH);

        carregarTabela();
    }

   private void aprovarSelecionada() {
    int row = tabela.getSelectedRow();
    if (row >= 0) {
        Long id = (Long) tableModel.getValueAt(row, 0);
        controller.aprovarSolicitacaoComEmail(id);
        carregarTabela();
        JOptionPane.showMessageDialog(this, "Solicitação aprovada com sucesso.");
    } else {
        JOptionPane.showMessageDialog(this, "Selecione uma solicitação para aprovar.");
    }
}

private void rejeitarSelecionada() {
    int row = tabela.getSelectedRow();
    if (row >= 0) {
        Long id = (Long) tableModel.getValueAt(row, 0);
        controller.rejeitarSolicitacaoComEmail(id);
        carregarTabela();
        JOptionPane.showMessageDialog(this, "Solicitação rejeitada com sucesso.");
    } else {
        JOptionPane.showMessageDialog(this, "Selecione uma solicitação para rejeitar.");
    }
}


    private void carregarTabela() {
        tableModel.setRowCount(0);
        for (Solicitacao s : controller.listarSolicitacoes()) {
           String emailAluno = s.getAluno().getEmail(); // evita colocar o objeto inteiro
            tableModel.addRow(new Object[]{
                s.getId(),
                emailAluno,
                s.getData(),
                s.getStatus(),
                s.getDescricao()
            });
        }
    }
}
