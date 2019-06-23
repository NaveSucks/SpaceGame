import java.awt.*;

public class GameOverScreen {


    private static int highScoreButtonX;
    private static int highScoreButtonY;
    private static int newGameButtonX;
    private static int newGameButtonY;
    private static int buttonHeight;
    private static int buttonWidth;

    public static void draw(Graphics g, int width, int height, boolean mouseOnHighscores, boolean mouseOnNewGame){
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.PLAIN, height/5);
        g.setFont(font);
        g.drawString("GAME OVER", (int)(width/5), height/3);
        if(mouseOnHighscores){
            g.setColor(Color.yellow);
        }
        highScoreButtonX = (width/7);
        highScoreButtonY = (int)(height*0.7);

        buttonWidth = (width/7)*2;
        buttonHeight = (int)(height*0.2);

        g.drawRect(highScoreButtonX, highScoreButtonY, buttonWidth, buttonHeight);
        font = new Font("Arial", Font.PLAIN, (int)(height*0.1));
        g.setFont(font);
        g.drawString("highscores",(int)(width/7*1.1),(int)(height*0.85) );

        g.setColor(Color.white);
        newGameButtonX = highScoreButtonX*4;
        newGameButtonY = highScoreButtonY;
        if(mouseOnNewGame){
            g.setColor(Color.yellow);
        }
        g.drawRect(newGameButtonX, newGameButtonY, buttonWidth, buttonHeight);
        font = new Font("Arial", Font.PLAIN, (int)(height*0.1));
        g.setFont(font);
        g.drawString("try again",(int)(width/7*4.3),(int)(height*0.85));
    }
}
