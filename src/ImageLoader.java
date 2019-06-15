import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    private static final ImageLoader instance = new ImageLoader();

    public BufferedImage enemyN;
    public BufferedImage enemyNNE;
    public BufferedImage enemyNE;
    public BufferedImage enemyNEE;
    public BufferedImage enemyE;
    public BufferedImage enemySEE;
    public BufferedImage enemySE;
    public BufferedImage enemySSE;
    public BufferedImage enemyS;
    public BufferedImage enemySSW;
    public BufferedImage enemySW;
    public BufferedImage enemySWW;
    public BufferedImage enemyW;
    public BufferedImage enemyNWW;
    public BufferedImage enemyNW;
    public BufferedImage enemyNNW;

    private ImageLoader(){

        //enemy images

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

        //player images:



    }
    public static ImageLoader getInstance(){
        return instance;
    }
}
