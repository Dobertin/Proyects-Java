import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> body;
    private boolean growing;

    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(10, 10));  // Starting position
        growing = false;
    }

    public List<Point> getBody() {
        return body;
    }

    public void moveTo(Point point) {
        body.add(0, point);
        if (!growing) {
            body.remove(body.size() - 1);
        } else {
            growing = false;
        }
    }

    public void grow() {
        growing = true;
    }

    public Point getHead() {
        return body.get(0);
    }

    public void reset() {
        body.clear();
        body.add(new Point(10, 10));
    }
}
