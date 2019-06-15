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
    private BufferedImage enemyN    = ImageLoader.getInstance().enemyN  ;
    private BufferedImage enemyNNE  = ImageLoader.getInstance().enemyNNE;
    private BufferedImage enemyNE   = ImageLoader.getInstance().enemyNE ;
    private BufferedImage enemyNEE  = ImageLoader.getInstance().enemyNEE;
    private BufferedImage enemyE    = ImageLoader.getInstance().enemyE  ;
    private BufferedImage enemySEE  = ImageLoader.getInstance().enemySEE;
    private BufferedImage enemySE   = ImageLoader.getInstance().enemySE ;
    private BufferedImage enemySSE  = ImageLoader.getInstance().enemySSE;
    private BufferedImage enemyS    = ImageLoader.getInstance().enemyS  ;
    private BufferedImage enemySSW  = ImageLoader.getInstance().enemySSW;
    private BufferedImage enemySW   = ImageLoader.getInstance().enemySW ;
    private BufferedImage enemySWW  = ImageLoader.getInstance().enemySWW;
    private BufferedImage enemyW    = ImageLoader.getInstance().enemyW  ;
    private BufferedImage enemyNWW  = ImageLoader.getInstance().enemyNWW;
    private BufferedImage enemyNW   = ImageLoader.getInstance().enemyNW ;
    private BufferedImage enemyNNW  = ImageLoader.getInstance().enemyNNW;

    public Enemy() {
        randomSpawnPos();
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
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
        } else {
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
        int visualPosX = (getPosX() - offsetX)* resX/1000 - resX/30;
        int visualPosY = (getPosY() - offsetY)* resY/1000 - resY/30;
        double angle = 0;
        //Winkel zwischen Spieler und enemy durch Vektor
       if(true ||getPosX()-playerX != 0){
           angle = Math.toDegrees(Math.atan2(playerY - getPosY(), playerX - getPosX()));
       }
       BufferedImage image;
       //System.out.println(angle + " " + getDirection(angle));

        switch (getDirection(angle)){
            case S:
                image = enemyS;
                break;
            case N:
                image = enemyN;
                break;
            case E:
                image = enemyE;
                break;
            case W:
                image = enemyW;
                break;
            case SE:
                image = enemySE;
                break;
            case NW:
                image = enemyNW;
                break;
            case NE:
                image = enemyNE;
                break;
            case SW:
                image = enemySW;
                break;
            case SEE:
                image = enemySEE;
                break;
            case NWW:
                image = enemyNWW;
                break;
            case NEE:
                image = enemyNEE;
                break;
            case SWW:
                image = enemySWW;
                break;
            case SSE:
                image = enemySSE;
                break;
            case NNW:
                image = enemyNNW;
                break;
            case NNE:
                image = enemyNNE;
                break;
            case SSW:
                image = enemySSW;
                break;
            default:
                image = enemyS;

        }
        g.drawImage(image, visualPosX, visualPosY, resX/15,resX/15,null);
        drawHealth(g,resX,resY,offsetX,offsetY,visualPosX,visualPosY);
    }
}
