import java.awt.*;

public class Crystal extends Entity {

    public Crystal(int x, int y) {
        setPosX(x);
        setPosY(y);
    }

    public void draw(Graphics g, int resX, int resY, int offsetX, int offsetY) {
        int visualPosX = (getPosX() - offsetX) * resX / 1000 - resX / 30;
        int visualPosY = (getPosY() - offsetY) * resY / 1000 - resY / 30;
        g.setColor(Color.cyan);
        g.fillOval(visualPosX, visualPosY, resX / 50, resX / 50);
    }
}
