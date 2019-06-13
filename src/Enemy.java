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
    private BufferedImage enemyNE;
    private BufferedImage enemyE;
    private BufferedImage enemySE;
    private BufferedImage enemyS;
    private BufferedImage enemySW;
    private BufferedImage enemyW;
    private BufferedImage enemyNW;


    public Enemy() {
        randomSpawnPos();
        try {
            enemyN = ImageIO.read(Enemy.class.getResource("enemyN.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNE = ImageIO.read(Enemy.class.getResource("enemyNE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyE = ImageIO.read(Enemy.class.getResource("enemyE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySE = ImageIO.read(Enemy.class.getResource("enemySE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyS = ImageIO.read(Enemy.class.getResource("enemyS.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemySW = ImageIO.read(Enemy.class.getResource("enemySW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyW = ImageIO.read(Enemy.class.getResource("enemyW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            enemyNW = ImageIO.read(Enemy.class.getResource("enemyNW.png"));
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
        boolean movedEast = false;
        boolean movedWest = false;
        boolean movedSouth = false;
        boolean movedNorth = false;

        if (freeToMove(mobList)) {



            if (getPosX() < xPosPlayer - 20) {
                moveX(enemySpeed);
                movedEast = true;
            } else if (getPosX() > xPosPlayer + 20) {
                moveX(-enemySpeed);
                movedWest = true;
            }
            if (getPosY() < yPosPlayer - 20) {
                moveY(enemySpeed);
                movedSouth = true;
            } else if (getPosY() > yPosPlayer + 20) {
                moveY(-enemySpeed);
                movedNorth = true;
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
        if(movedNorth && !movedEast && !movedWest) setDir(Direction.N);
        else if(movedEast && !movedNorth && !movedSouth) setDir(Direction.E);
        else if (movedSouth && !movedEast && !movedWest) setDir(Direction.S);
        else if (movedWest && !movedNorth && !movedSouth) setDir(Direction.W);
        else if(movedEast && movedNorth) setDir(Direction.NE);
        else if(movedEast && movedSouth) setDir(Direction.SE);
        else if(movedWest && movedNorth) setDir(Direction.NW);
        else if(movedWest && movedSouth) setDir(Direction.SW);
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

    public void draw(Graphics g, int resX, int resY, int offsetX, int offsetY){
        int visualPosX = (getPosX() - offsetX)* resX/1000 - resX/30;
        int visualPosY = (getPosY() - offsetY)* resY/1000 - resY/30;
        BufferedImage image;
        switch (getDir()){
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
            default:
                image = enemyS;

        }
        //g.drawImage(image, visualPosX, visualPosY, null);
        g.drawImage(image, visualPosX, visualPosY, resX/15,resX/15,null);
        drawHealth(g,resX,resY,offsetX,offsetY,visualPosX,visualPosY);
    }
}
