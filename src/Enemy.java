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
    private Random rng = new Random();
    private BufferedImage enemyN;
    private BufferedImage enemyNNE;
    private BufferedImage enemyNE;
    private BufferedImage enemyNEE;
    private BufferedImage enemyE;
    private BufferedImage enemySEE;
    private BufferedImage enemySE;
    private BufferedImage enemySSE;
    private BufferedImage enemyS;
    private BufferedImage enemySSW;
    private BufferedImage enemySW;
    private BufferedImage enemySWW;
    private BufferedImage enemyW;
    private BufferedImage enemyNWW;
    private BufferedImage enemyNW;
    private BufferedImage enemyNNW;


    public Enemy() {
        randomSpawnPos();
        try {
            enemyN = ImageIO.read(Enemy.class.getResource("enemy2N.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNNE = ImageIO.read(Enemy.class.getResource("enemy2NNE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNE = ImageIO.read(Enemy.class.getResource("enemy2NE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNEE = ImageIO.read(Enemy.class.getResource("enemy2NEE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyE = ImageIO.read(Enemy.class.getResource("enemy2E.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySEE = ImageIO.read(Enemy.class.getResource("enemy2SEE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySE = ImageIO.read(Enemy.class.getResource("enemy2SE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySSE = ImageIO.read(Enemy.class.getResource("enemy2SSE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyS = ImageIO.read(Enemy.class.getResource("enemy2S.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySSW = ImageIO.read(Enemy.class.getResource("enemy2SSW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySW = ImageIO.read(Enemy.class.getResource("enemy2SW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySWW = ImageIO.read(Enemy.class.getResource("enemy2SWW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyW = ImageIO.read(Enemy.class.getResource("enemy2W.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNWW = ImageIO.read(Enemy.class.getResource("enemy2NWW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNW = ImageIO.read(Enemy.class.getResource("enemy2NW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNNW = ImageIO.read(Enemy.class.getResource("enemy2NNW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
