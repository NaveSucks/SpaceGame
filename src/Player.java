import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public int attack = Powerlevel.getInstance().getDamage();
    private int lifeRegen = 1;
    private int playerSpeed = 5;
    private BufferedImage[] playerImages = ImageLoader.getInstance().playerImages;


    public Player() {
        setPosX(500);
        setPosY(500);
    }

    //check if player is in bounds of arena
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

    public void draw(Graphics g, int resX, int resY) {
        BufferedImage image;
        int i = 0;
        image = playerImages[0];
        for (Direction direction : Direction.values()) {
            if (getDir() == direction) {
                image = playerImages[i];
                break;
            }
            i++;
        }
        g.drawImage(image, resX / 2 - resX / 20, resY / 2 - resX / 20, resX / 10, resX / 10, null);
        drawHealth(g,resX,resY);
    }

    private void drawHealth(Graphics g, int resX, int resY){
        g.setColor(Color.gray);
        g.fillRect(0, (int)(resY*0.85), (int)(resX*0.3), (int)(resY*0.15));
        g.setColor(Color.red);
        for(int i = 0; i < (int)getHealth()/10; i++){
            g.fillRect((int)(0.005*resX + i*0.029*resX), (int)(resY*0.855), (int)(resX*0.026),(int)(resY*0.14));
        }
    }
}



