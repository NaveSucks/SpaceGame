import java.awt.*;

//bullets only exist as eye candy. they dont actually exist in the logic of the gameSystem. they just look nice and give the player an idea of what he is doing

public class Powerlevel {
    private static final Powerlevel powerlevel= new Powerlevel();
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
        if(level<19) {
            level++;
            bulletSpeed = bulletSpeed + 3 + 1 * level;
            bulletColor = bulletColors[level / 4];
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

    public void reset(){
        bulletSpeed = 40;
        bulletSize = 3;
        bulletAmount = 1;
        damage = 4;
        level = 1;
    }

    public Color getBulletColor() {
        return bulletColor;
    }
}
