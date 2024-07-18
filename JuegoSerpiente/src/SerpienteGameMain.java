import javax.swing.*;

public class SerpienteGameMain extends JFrame {
    private PaneldeJuego paneldeJuego;

    public SerpienteGameMain() {
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
            SerpienteGameMain game = new SerpienteGameMain();
            game.setVisible(true);
        });
    }
}
