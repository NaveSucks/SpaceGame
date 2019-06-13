import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    public int attack = Powerlevel.getInstance().getDamage();
    private int lifeRegen = 1;
    private int playerSpeed = 5;
    private BufferedImage N;
    private BufferedImage NNE;
    private BufferedImage NE;
    private BufferedImage NEE;
    private BufferedImage E;
    private BufferedImage SEE;
    private BufferedImage SE;
    private BufferedImage SSE;
    private BufferedImage S;
    private BufferedImage SSW;
    private BufferedImage SW;
    private BufferedImage SWW;
    private BufferedImage W;
    private BufferedImage NWW;
    private BufferedImage NW;
    private BufferedImage NNW;


    public Player() {
        setPosX(500);
        setPosY(500);
        try {
            N = ImageIO.read(Enemy.class.getResource("N.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            NNE = ImageIO.read(Enemy.class.getResource("NNE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            NE = ImageIO.read(Enemy.class.getResource("NE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            NEE = ImageIO.read(Enemy.class.getResource("NEE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            E = ImageIO.read(Enemy.class.getResource("E.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            SEE = ImageIO.read(Enemy.class.getResource("SEE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            SE = ImageIO.read(Enemy.class.getResource("SE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            SSE = ImageIO.read(Enemy.class.getResource("SSE.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            S = ImageIO.read(Enemy.class.getResource("S.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            SSW = ImageIO.read(Enemy.class.getResource("SSW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            SW = ImageIO.read(Enemy.class.getResource("SW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            SWW = ImageIO.read(Enemy.class.getResource("SWW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            W = ImageIO.read(Enemy.class.getResource("W.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            NWW = ImageIO.read(Enemy.class.getResource("NWW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            NW = ImageIO.read(Enemy.class.getResource("NW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            NNW = ImageIO.read(Enemy.class.getResource("NNW.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public boolean xPositionLegal() {
        return getPosX() >= 100 && getPosX() <= 900;
    }

    public boolean yPositionLegal() {
        return getPosY() >= 100 && getPosY() <= 900;
    }

    public void fixPlayerPos() {
        if (getPosX() < 100) {
            setPosX(100);
        } else if (getPosX() > 900) {
            setPosX(900);
        }
        if (getPosY() < 100) {
            setPosY(100);
        } else if (getPosY() > 900) {
            setPosY(900);
        }
    }

    public int getLifeRegen() {
        return lifeRegen;
    }

    public void setLifeRegen(int lifeRegen) {
        this.lifeRegen = lifeRegen;
    }


    public void move(int xVel, int yVel) {

        if (xVel != 0 && xPositionLegal()) {

            moveX(xVel * playerSpeed);
        }


        if (yVel != 0 && yPositionLegal()) {

            moveY(yVel * playerSpeed);

        }
        if (!xPositionLegal() || !yPositionLegal()) {
            fixPlayerPos();
        }

    }

    //@Override
    public void draw(Graphics g, int resX, int resY) {
        BufferedImage playerImage;
        //System.out.println(getDir());

        switch (getDir()) {
            case N:
                playerImage = N;
                break;
            case NNE:
                playerImage = NNE;
                break;
            case NE:
                playerImage = NE;
                break;
            case NEE:
                playerImage = NEE;
                break;
            case E:
                playerImage = E;
                break;
            case SEE:
                playerImage = SEE;
                break;
            case SE:
                playerImage = SE;
                break;
            case SSE:
                playerImage = SSE;
                break;
            case S:
                playerImage = S;
                break;
            case SSW:
                playerImage = SSW;
                break;
            case SW:
                playerImage = SW;
                break;
            case SWW:
                playerImage = SWW;
                break;
            case W:
                playerImage = W;
                break;
            case NWW:
                playerImage = NWW;
                break;
            case NW:
                playerImage = NW;
                break;
            case NNW:
                playerImage = NNW;
                break;
            default:
                playerImage = S;
        }
        g.drawImage(playerImage, resX / 2 - resX/20, resY / 2 - resX/20, resX/10, resX/10, null);
    }

}



