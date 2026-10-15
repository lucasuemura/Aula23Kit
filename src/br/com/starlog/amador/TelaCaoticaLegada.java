package br.com.starlog.amador;

import br.com.starlog.controller.TransmissaoController;
import javax.swing.*;
import java.awt.*;

public class TelaCaoticaLegada extends JFrame {

    private JTextField txtRemetente;
    private JTextField txtMensagem;
    private JTextField txtChaveCifra;
    private JButton btnDespacharCifrado;
    private JTextArea areaLog;

    private final TransmissaoController controller = new TransmissaoController();

    public TelaCaoticaLegada() {
        super("SISTEMA LEGADO CORROMPIDO - STARLOG");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Remetente da Frota:"));
        txtRemetente = new JTextField(20);
        add(txtRemetente);

        add(new JLabel("Mensagem Secreta:"));
        txtMensagem = new JTextField(20);
        add(txtMensagem);

        add(new JLabel("Chave de Deslocamento (César 1 a 25):"));
        txtChaveCifra = new JTextField(5);
        add(txtChaveCifra);

        btnDespacharCifrado = new JButton("Cifrar e Transmitir");
        add(btnDespacharCifrado);

        areaLog = new JTextArea(10, 35);
        add(new JScrollPane(areaLog));

        // Botão enxuto seguindo o padrão MVC: apenas delega ao Controller e exibe o resultado!
        btnDespacharCifrado.addActionListener(e -> {
            try {
                String pacote = controller.processarTransmissao(
                        txtRemetente.getText(),
                        txtMensagem.getText(),
                        txtChaveCifra.getText()
                );
                areaLog.append(pacote + "\n");
                JOptionPane.showMessageDialog(this, "Transmissão efetuada com sucesso!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "ERRO: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCaoticaLegada().setVisible(true));
    }
}