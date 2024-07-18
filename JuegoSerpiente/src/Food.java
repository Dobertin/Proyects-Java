import java.awt.*;
import java.util.Random;

public class Food {
    private Point position;
    private static final int MAX_X = 20;
    private static final int MAX_Y = 20;

    public Food() {
        position = new Point();
        generateNewPosition();
    }

    public Point getPosition() {
        return position;
    }

    public void generateNewPosition() {
        Random random = new Random();
        position.x = random.nextInt(MAX_X);
        position.y = random.nextInt(MAX_Y);
    }
}
