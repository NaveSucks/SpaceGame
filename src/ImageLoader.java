import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    private static final ImageLoader instance = new ImageLoader();

    public BufferedImage[] enemyOneImages = new BufferedImage[16];
    public BufferedImage[] playerImages = new BufferedImage[16];

    private ImageLoader(){

        //enemy images:

        int i = 0;
        for (Entity.Direction direction : Entity.Direction.values()) {  //iteration of the 16 elements of the Direction enum
            String fileName = "enemy2" + direction +".png";
            try {
                enemyOneImages[i] = ImageIO.read(Enemy.class.getResource(fileName));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            i++;
        }

        //player images:

        int j = 0;
        for (Entity.Direction direction : Entity.Direction.values()) {  //iteration of the 16 elements of the Direction enum
            String fileName = direction +".png";
            try {
                playerImages[j] = ImageIO.read(Enemy.class.getResource(fileName));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            j++;
        }

    }
    public static ImageLoader getInstance(){
        return instance;
    }
}
