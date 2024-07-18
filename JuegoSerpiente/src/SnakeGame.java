import javax.swing.*;

public class SnakeGame extends JFrame {
    private GamePanel gamePanel;

    public SnakeGame() {
        gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setTitle("Juego de la Culebra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
