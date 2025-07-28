package app.view;

import app.controller.SolicitacaoController;
import app.model.Solicitacao;
import app.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaPrincipal extends JFrame {
    private Usuario usuario;
    private SolicitacaoController controller;
    private JTable tabela;
    private DefaultTableModel tableModel;

    public TelaPrincipal(Usuario usuario) {
        this.usuario = usuario;
        this.controller = new SolicitacaoController();

        setTitle("Área do Aluno");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitulo = new JLabel("Bem-vindo, " + usuario.getNome(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Data", "Status"}, 0);
        tabela = new JTable(tableModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JButton btnNovaSolicitacao = new JButton("Nova Solicitação");
        btnNovaSolicitacao.addActionListener(e -> novaSolicitacao());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnNovaSolicitacao);
        add(bottomPanel, BorderLayout.SOUTH);

        carregarTabela();
    }

    private void novaSolicitacao() {
        JTextField descricaoField = new JTextField();
        String[] opcoes = {"Equipamentos", "Laboratórios"};
        JComboBox<String> tipoBox = new JComboBox<>(opcoes);
        JSpinner dataSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataSpinner, "yyyy-MM-dd");
        dataSpinner.setEditor(dateEditor);
        dataSpinner.setValue(new java.util.Date()); // default hoje

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Tipo de Solicitação:"));
        panel.add(tipoBox);
        panel.add(new JLabel("Descrição:"));
        panel.add(descricaoField);
        panel.add(new JLabel("Data de Uso:"));
        panel.add(dataSpinner);

        int result = JOptionPane.showConfirmDialog(this, panel, "Nova Solicitação",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Date dataSelecionada = (Date) dataSpinner.getValue();
            Date hoje = new Date();

            if (dataSelecionada.before(hoje)) {
                JOptionPane.showMessageDialog(this, "A data de uso deve ser futura.");
                return;
            }

            long id = System.currentTimeMillis();
            String tipo = (String) tipoBox.getSelectedItem();
            String descricao = descricaoField.getText();
            String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataSelecionada);

            String resumo = tipo + " - " + descricao;

            // Agora passamos o objeto Usuario
            Solicitacao nova = new Solicitacao(id, usuario, dataFormatada, "Pendente");
            nova.setDescricao(resumo);

            controller.adicionarSolicitacao(nova);
            carregarTabela();
        }
    }

    private void carregarTabela() {
        tableModel.setRowCount(0);
        for (Solicitacao s : controller.listarSolicitacoes()) {
            // Comparando o e-mail do usuário logado com o da solicitação
            if (s.getAluno().getEmail().equals(usuario.getEmail())) {
                tableModel.addRow(new Object[]{s.getId(), s.getData(), s.getStatus()});
            }
        }
    }
}
