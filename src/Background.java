import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {
    private BufferedImage b1;
    private BufferedImage b2;
    private BufferedImage b3;
    private BufferedImage b4;
    private BufferedImage b5;

    public Background(){
        try {
            b1 = ImageIO.read(Enemy.class.getResource("b1.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            b2 = ImageIO.read(Enemy.class.getResource("b2.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            b3 = ImageIO.read(Enemy.class.getResource("b3.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            b4 = ImageIO.read(Enemy.class.getResource("b4.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            b5 = ImageIO.read(Enemy.class.getResource("b5.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void draw(Graphics g, int resX, int resY, int offsetX, int offsetY){

        g.drawImage(b5,(int)(-offsetX*0.05), (int)(-offsetY*0.05), resX,resX, null);
       // g.drawImage(b4,(int)(offsetX*0.1), (int)(offsetY*0.1), resX,resX, null);
        g.drawImage(b3,(int)(-offsetX*0.1), (int)(-offsetY*0.2), resX,resX, null);
       // g.drawImage(b2,(int)(offsetX*0.3), (int)(offsetY*0.3), resX,resX, null);
        g.drawImage(b1,(int)(-offsetX*0.2), (int)(-offsetY*0.4), resX,resX, null);


    }
}
