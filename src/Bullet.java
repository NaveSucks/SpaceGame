import java.awt.*;
import java.util.Random;

public class Bullet extends Point {

    int deltaX;
    int deltaY;
    int size = 5;
    Random random = new Random();

    public Bullet(int x, int y, int deltaX, int deltaY) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaX;
    }
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDeltaX(int deltaX) {
        int next = random.nextInt(3 * Powerlevel.getInstance().getBulletSpeed()/40) - 2;
        this.deltaX = deltaX + next;
    }

    public void setDeltaY(int deltaY) {
        int next = random.nextInt(3 * (Powerlevel.getInstance().getBulletSpeed()/40)) - 2;
        this.deltaY = deltaY + next;
    }

    public void move() {
        x += deltaX;
        y += deltaY;
    }

    public void draw(Graphics g, int resX,int resY) {
        g.setColor(Powerlevel.getInstance().getBulletColor());
        size = Powerlevel.getInstance().getBulletSize();
        g.fillOval(x - size/2 -20, y - size/2, size, size);
        g.fillOval(x - size/2 +20, y - size/2, size, size);
        g.setColor(Color.white);
        g.fillOval(x - size/2 -19, y - size/2 + 1, size - 2, size - 2);
        g.fillOval(x - size/2 + 21, y - size/2 + 1, size - 2, size - 2);
    }

}
