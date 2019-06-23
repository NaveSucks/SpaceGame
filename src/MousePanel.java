import scoreSystem.HighDialog;
import scoreSystem.HighScore;
import scoreSystem.MineTimer;
import scoreSystem.Score;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;


public class MousePanel extends JPanel
        implements KeyListener, MouseListener, ActionListener {

    //set of pressed keys, so diagonal movement is possible
    private final Set<Character> pressed = new HashSet<Character>();

    private GameSystem gameSystem;
    private Timer timer = new Timer(25, this);
    private int mousePosX = 0;
    private int mousePosY = 0;
    private boolean shooting = false;
    MineTimer m = MineTimer.getInstance();
    private Bullets playerBullets = new Bullets();
    private BufferedImage[] backgroundImages = ImageLoader.getInstance().backgroundImages;

    private boolean mouseOnHighscores;
    private boolean mouseOnNewGame;

    private boolean highScoreSaved = false;


    public MousePanel() {
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        gameSystem = new GameSystem();
        timer.start();
    }

    public void paint(Graphics g) {

        super.paint(g);
        int width = this.getWidth();
        int height = this.getHeight();
        drawBackground(g,width,height,gameSystem.getOffsetX(),gameSystem.getOffsetY());
        g.setColor(Color.gray);
        g.drawRect((100 - gameSystem.getOffsetX()) * width / 1000, (100 - gameSystem.getOffsetY()) * height / 1000, 800 * width / 1000, 800 * height / 1000);
        playerBullets.shoot(g, getWidth()/2,getHeight()/2, mousePosX, mousePosY, shooting);
        gameSystem.drawMobs(g, width, height);
        gameSystem.drawPowerLevel(g, width, height);
        gameSystem.drawPlayer(g, width, height, playerAngle());
        MineTimer.getInstance().draw(g,width,height);

        if (gameSystem.lost()) {
            GameOverScreen.draw(g, width, height, mouseOnHighscores, mouseOnNewGame);
        }
    }

    public void drawBackground(Graphics g, int resX, int resY, int offsetX, int offsetY){
        g.drawImage(backgroundImages[5],(int)(-offsetX*0.05), (int)(-offsetY*0.05), (int)(resX*1.5),(int)(resX*1.5), null);
        g.drawImage(backgroundImages[3],(int)(-offsetX*0.1), (int)(-offsetY*0.2), (int)(resX*1.5),(int)(resX*1.5), null);
        g.drawImage(backgroundImages[1],(int)(-offsetX*0.2), (int)(-offsetY*0.4), (int)(resX*1.5),(int)(resX*1.5), null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //checks which option gets chosen on the game over screen
        if(gameSystem.lost()){
            if(e.getX()<getWidth()/2){
                new HighDialog();
            }
            if(e.getX()>getWidth()/2){
                gameSystem = new GameSystem();
                highScoreSaved = false;
                MineTimer.getInstance().reset();
                MineTimer.getInstance().start();
                Powerlevel.getInstance().reset();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shooting = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shooting = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyChar());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyChar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        PointerInfo info = MouseInfo.getPointerInfo();
        Point point = info.getLocation();

        mousePosX = (int) point.getX();
        mousePosY = (int) point.getY();

        if (pressed.contains('w')) {
            gameSystem.movePlayerUp();
        }
        if (pressed.contains('a')) {
            gameSystem.movePlayerLeft();
        }
        if (pressed.contains('s')) {
            gameSystem.movePlayerDown();
        }
        if (pressed.contains('d')) {
            gameSystem.movePlayerRight();
        }
        //logische Koordinate der angezielten Position errechnen
        int xCoordinate = (mousePosX * 1000 / getWidth()) + gameSystem.getOffsetX();
        int yCoordinate = (mousePosY * 1000 / getHeight()) + gameSystem.getOffsetY();
        gameSystem.tick(shooting, xCoordinate, yCoordinate);
        repaint();
        if(gameSystem.lost()){
            if(!highScoreSaved) {
                String name = JOptionPane.showInputDialog("What's your name?");
                if (name.equals("")) {
                    name = "player X";
                }
                MineTimer.getInstance().stop();
                HighScore.getInstance().add(new Score(name, MineTimer.getInstance().spielzeit()));
                HighScore.getInstance().store();
                highScoreSaved = true;
            }
            System.out.println("test");
            mouseOnHighscores = mousePosX<getWidth()/2;
            mouseOnNewGame = mousePosX>getWidth()/2;
        }
    }
    //direction of player view relative to the panel in degrees
    private double playerAngle(){
        return Math.toDegrees(Math.atan2(mousePosY - getHeight() / 2, mousePosX - getWidth() / 2));
    }
}















