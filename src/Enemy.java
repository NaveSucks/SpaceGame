import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity {

    public int attack = 5;
    private int enemySpeed = 2;
    private int safeZone = 40;
    private static Random rng = new Random();
    public BufferedImage[] enemyOneImages = ImageLoader.getInstance().enemyOneImages;


    public Enemy() {
        randomSpawnPos();
    }

    public int getAttack() {
        return attack;
    }

    private void randomSpawnPos() {
        int x = 0;
        int y = 0;

        switch (rng.nextInt(4)) {
            case 0:
                x = rng.nextInt(100);
                y = rng.nextInt(1000);
                break;
            case 1:
                x = 900 + rng.nextInt(100);
                y = rng.nextInt(1000);
                break;
            case 2:
                x = rng.nextInt(1000);
                y = rng.nextInt(100);
                break;
            case 3:
                x = rng.nextInt(1000);
                y = 900 + rng.nextInt(100);
                break;
            default:
                System.out.println("Ich putze hier nur");
        }
        setPosX(x);
        setPosY(y);
    }

    public void move(int xPosPlayer, int yPosPlayer, List<Enemy> mobList) {
        //move enemy closer to player
        if (freeToMove(mobList)) {
            if (getPosX() < xPosPlayer - 20) {
                moveX(enemySpeed);
            } else if (getPosX() > xPosPlayer + 20) {
                moveX(-enemySpeed);
            }
            if (getPosY() < yPosPlayer - 20) {
                moveY(enemySpeed);
            } else if (getPosY() > yPosPlayer + 20) {
                moveY(-enemySpeed);
            }
        }
        //move in random direction to get away from illegal position
        else {
            switch (rng.nextInt(4)) {
                case 0:
                    moveX(enemySpeed *4);
                    break;
                case 1:
                    moveX(-enemySpeed *4);
                    break;
                case 2:
                    moveY(enemySpeed *4);
                    break;
                case 3:
                    moveY(-enemySpeed *4);
                    break;
                default:
                    System.out.println("Ich putze hier nur");
            }
        }
    }

    //checks if an enemy ist free to move
    public boolean freeToMove(List<Enemy> mobList) {
        for (int i = 0; i < mobList.size(); i++) {
            if ((mobList.get(i).getPosX() > this.getPosX() - safeZone && mobList.get(i).getPosX() < this.getPosX() + safeZone) &&
                    mobList.get(i).getPosY() > this.getPosY() - safeZone && mobList.get(i).getPosY() < this.getPosY() + safeZone
                    && mobList.get(i) != this) {
                return false;
            }

        }
        return true;
    }

    public void draw(Graphics g, int resX, int resY, int offsetX, int offsetY, int playerX, int playerY){
        //logical position converted to visual position. explanation here: https://imgur.com/jVNKqU2
        int visualPosX = (getPosX() - offsetX)* resX/1000 - resX/30;
        int visualPosY = (getPosY() - offsetY)* resY/1000 - resY/30;
        double angle = 0;
        //angle between player and enemy
       if(true ||getPosX()-playerX != 0){
           angle = Math.toDegrees(Math.atan2(playerY - getPosY(), playerX - getPosX()));
       }
       BufferedImage image;

        int i = 0;
        image = enemyOneImages[0];
        for (Direction direction : Direction.values()) {    //iteration of the 16 elements of the Direction enum
            if(getDirection(angle) == direction){
                image = enemyOneImages[i];
                break;
            }
            i++;
        }
        //draw said image
        g.drawImage(image, visualPosX, visualPosY, resX/15,resX/15,null);
        drawHealth(g,resX,resY,offsetX,offsetY,visualPosX,visualPosY);
    }
}
