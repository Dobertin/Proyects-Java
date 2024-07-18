import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class PaneldeJuego extends JPanel {
    private static final int CELL_SIZE = 20;
    private static final int GRID_SIZE = 20;
    private static final int PANEL_SIZE = GRID_SIZE * CELL_SIZE + 40; // Adding margin for double-line border
    private static final int DELAY = 100;

    private Serpiente serpiente;
    private Comida comida;
    private Timer timer;
    private int score;
    private int losses;

    private Sonido eatSonido;
    private Sonido hitWallSonido;

    public PaneldeJuego() {
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBackground(Color.BLACK);

        serpiente = new Serpiente();
        comida = new Comida();
        score = 0;
        losses = 0;

        eatSonido = new Sonido("eat.wav");
        hitWallSonido = new Sonido("hit_wall.wav");

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point newPoint = new Point((e.getX() - 20) / CELL_SIZE, (e.getY() - 20) / CELL_SIZE);
                if (newPoint.x >= 0 && newPoint.x < GRID_SIZE && newPoint.y >= 0 && newPoint.y < GRID_SIZE) {
                    if (!serpiente.getBody().contains(newPoint)) {
                        serpiente.moveTo(newPoint);
                    }
                    checkCollisions(newPoint);
                }
                repaint();
            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                comida.generateNewPosition();
                repaint();
            }
        }, 0, 5000);

        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (true) {
            repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollisions(Point mousePoint) {
        // Manejar colision con la "comida"
        if (serpiente.getHead().equals(comida.getPosition())) {
            serpiente.grow();
            score++;
            comida.generateNewPosition();
            eatSonido.play();  // Reproducir sonido al comer
        }

        // Manejar Colision con los muros
        Point head = serpiente.getHead();
        if (head.x < 0 || head.y < 0 || head.x >= GRID_SIZE || head.y >= GRID_SIZE) {
            losses++;
            serpiente.reset();
            hitWallSonido.play();  // Reproducir sonido al chocar con la pared
            return;  // No es necesario realizar más comprobaciones si ya chocó con la pared.
        }

        // Manejar Colision consigo mismo
        if (serpiente.getBody().subList(1, serpiente.getBody().size()).contains(mousePoint)) {
            losses++;
            serpiente.reset();
            hitWallSonido.play();  // Reproducir sonido al chocar con su cuerpo
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the double-line border
        g.setColor(Color.WHITE);
        g.drawRect(19, 19, GRID_SIZE * CELL_SIZE + 2, GRID_SIZE * CELL_SIZE + 2);
        g.drawRect(18, 18, GRID_SIZE * CELL_SIZE + 4, GRID_SIZE * CELL_SIZE + 4);

        // Draw the comida
        g.setColor(Color.RED);
        Point foodPos = comida.getPosition();
        g.fillRect(20 + foodPos.x * CELL_SIZE, 20 + foodPos.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw the serpiente
        g.setColor(Color.GREEN);
        for (Point point : serpiente.getBody()) {
            g.fillRect(20 + point.x * CELL_SIZE, 20 + point.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        // Draw the score and losses
        g.setColor(Color.WHITE);
        g.drawString("Puntuación: " + score, 30, 10);
        g.drawString("Pérdidas: " + losses, 150, 10);
    }
}
