package br.com.starlog.amador;

import br.com.starlog.controller.TransmissaoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TELA REFATORADA COM PADRÃO MVC • ESTAÇÃO STARLOG-ZERO
 * 
 * Agora a View atua apenas como Casca Visual (Garçom): coleta os dados brutos
 * dos campos de texto, delega o processamento para o TransmissaoController
 * e exibe o resultado ou mensagem de erro amigável via JOptionPane.
 */
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

        btnDespacharCifrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pacoteFinal = controller.processarTransmissao(
                            txtRemetente.getText(),
                            txtMensagem.getText(),
                            txtChaveCifra.getText()
                    );
                    areaLog.append(pacoteFinal + "\n");
                    JOptionPane.showMessageDialog(TelaCaoticaLegada.this, "Transmissão efetuada com sucesso!");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(TelaCaoticaLegada.this, "ERRO: " + ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCaoticaLegada().setVisible(true));
    }
}
