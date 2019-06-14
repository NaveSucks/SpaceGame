import java.awt.*;

public class Powerlevel {
    private static final Powerlevel powerlevel = new Powerlevel();
    private int bulletSpeed = 40;
    private int bulletSize = 3;
    private int bulletAmount = 1;
    private int damage = 4;
    private int level = 1;
    private Color bulletColor = Color.red;
    private Color[] bulletColors = {Color.red, Color.orange, Color.yellow, Color.pink, Color.cyan};


    private Powerlevel() {

    }

    public static Powerlevel getInstance() {
        return powerlevel;
    }

    public void incrementPowerlevel() {
        if(level<14) {
            level++;
            bulletSpeed = bulletSpeed + 3 + 1 * level;
            bulletColor = bulletColors[level / 3];
            bulletAmount = bulletAmount + 1;
            bulletSize += 2;
            damage = damage + 1 + 1 * level / 3;
        }
    }
    public int getBulletSpeed() {
        return bulletSpeed;

    }

    public int getBulletSize() {
        return bulletSize;
    }

    public int getBulletAmount() {
        return bulletAmount;
    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public Color getBulletColor() {
        return bulletColor;
    }

}
