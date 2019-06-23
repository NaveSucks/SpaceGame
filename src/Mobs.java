import java.awt.*;
import java.util.*;
import java.util.List;

public class Mobs {
    List<Enemy> mobList = new ArrayList<Enemy>();
    List<Crystal> crystalList = new ArrayList<>();
    Random random = new Random();
    private int collectedCrystals;

    public void add() {
        Enemy e = new Enemy();
        mobList.add(e);

    }

    //moves all mobs closer to the player

    public void moveAllMobs(int xPosPlayer, int yPosPlayer) {
        for (int i = 0; i < mobList.size(); i++) {
            mobList.get(i).move(xPosPlayer, yPosPlayer, mobList);
        }
    }

    //returns how much damage the player takes

    public int damageOnPlayer(int xPosPlayer, int yPosPlayer) {
        int damage = 0;
        for (int i = 0; i < mobList.size(); i++) {
            if (mobList.get(i).getPosX() < xPosPlayer + 40 && mobList.get(i).getPosY() < yPosPlayer + 40
                    && mobList.get(i).getPosX() > xPosPlayer - 40 && mobList.get(i).getPosY() > yPosPlayer - 40) {
                damage += mobList.get(i).getAttack();
            }
        }
        return damage;
    }

    //checks if player picks up crystals (blue circles)

    public void crystalPickup(int xPosPlayer, int yPosPlayer) {
        for (int i = 0; i < crystalList.size(); i++) {
            if (crystalList.get(i).getPosX() < xPosPlayer + 60 && crystalList.get(i).getPosY() < yPosPlayer + 60
                    && crystalList.get(i).getPosX() > xPosPlayer - 60 && crystalList.get(i).getPosY() > yPosPlayer - 60) {
                crystalList.remove(i);
                collectedCrystals++;
            }
        }
    }

    //draws all enemy mobs RELATIVE to the player, therefore offsets are involved

    public void drawAllMobs(Graphics g, int resX, int resY, int offsetX, int offsetY, int playerX, int playerY ) {
        for (int i = 0; i < crystalList.size(); i++) {
            crystalList.get(i).draw(g, resX, resY, offsetX, offsetY);
        }
        for (int i = 0; i < mobList.size(); i++) {
            mobList.get(i).draw(g, resX, resY, offsetX, offsetY, playerX, playerY);
        }

    }

    //damages hit enemies, adds crystals with a 10% chance, despawsn dead enemies

    public void shootMobs(int x1, int y1, int x2, int y2) {
        for (int i = 0; i < mobList.size(); i++) {
            int x0 = mobList.get(i).getPosX();
            int y0 = mobList.get(i).getPosY();
            int r = mobList.get(i).getSize();
            if (checkHit(x1, y1, x2, y2, x0, y0, r)) {
                if (mobList.get(i).getHealth() <= 0) {
                    int rng = random.nextInt(100);
                    if(rng > 90)
                        crystalList.add(new Crystal(mobList.get(i).getPosX(), mobList.get(i).getPosY()));
                    mobList.remove(i);

                } else {
                    mobList.get(i).damage(Powerlevel.getInstance().getDamage());
                }
            }
        }
    }

    //checks if an enemy is on the imaginary line from the player through logical mouse cursor position,

    private boolean checkHit(int x1, int y1, int x2, int y2, int x0, int y0, int r) {
        boolean pointToLine = (r > Math.abs((y2 - y1) * x0 - (x2 - x1) * y0 + x2 * y1 - y2 * x1) / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2)));
        boolean correctSector = (
                (x1 < x2 && x1 < x0) ||
                        (y1 < y2 && y1 < y0) ||
                        (x1 > x2 && x1 > x0) ||
                        (y1 > y2 && y1 > y0));

        return (pointToLine && correctSector);

    }

    public int getCollectedCrystals() {
        return collectedCrystals;
    }
}





