import javax.swing.*;

public class JuegoSerpiente extends JFrame {
    private PaneldeJuego paneldeJuego;

    public JuegoSerpiente() {
        paneldeJuego = new PaneldeJuego();
        add(paneldeJuego);
        pack();
        setTitle("Juego de la Serpiente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JuegoSerpiente game = new JuegoSerpiente();
            game.setVisible(true);
        });
    }
}
