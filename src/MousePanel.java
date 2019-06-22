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

    private final Set<Character> pressed = new HashSet<Character>();
    private GameSystem gameSystem;
    private Timer timer = new Timer(25, this);
    private int mousePosX = 0;
    private int mousePosY = 0;
    private boolean shooting = false;
    private double angle = 0;
    private Bullets playerBullets = new Bullets();
    private BufferedImage[] backgroundImages = ImageLoader.getInstance().backgroundImages;


    public MousePanel() {
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        gameSystem = new GameSystem();
        timer.start();
    }

    public void paint(Graphics g) {

        super.paint(g);
        setBackground(Color.gray);
        drawBackground(g,getWidth(),getHeight(),gameSystem.getOffsetX(),gameSystem.getOffsetY());
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.gray);
        g.drawRect((100 - gameSystem.getOffsetX()) * width / 1000, (100 - gameSystem.getOffsetY()) * height / 1000, 800 * width / 1000, 800 * height / 1000);
        playerBullets.shoot(g, getWidth()/2,getHeight()/2, mousePosX, mousePosY, shooting);
        gameSystem.drawMobs(g, width, height);

        gameSystem.drawPowerLevel(g, width, height);
        gameSystem.drawPlayer(g, width, height, angle);

        if (gameSystem.lost()) {
            g.setColor(Color.red);
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
    public void drawBackground(Graphics g, int resX, int resY, int offsetX, int offsetY){

        g.drawImage(backgroundImages[5],(int)(-offsetX*0.05), (int)(-offsetY*0.05), (int)(resX*1.5),(int)(resX*1.5), null);
        // g.drawImage(backgroundImages[4],(int)(offsetX*0.1), (int)(offsetY*0.1), resX,resX, null);
        g.drawImage(backgroundImages[3],(int)(-offsetX*0.1), (int)(-offsetY*0.2), (int)(resX*1.5),(int)(resX*1.5), null);
        // g.drawImage(backgroundImages[2],(int)(offsetX*0.3), (int)(offsetY*0.3), resX,resX, null);
        g.drawImage(backgroundImages[1],(int)(-offsetX*0.2), (int)(-offsetY*0.4), (int)(resX*1.5),(int)(resX*1.5), null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        int xCoordinate = (mousePosX * 1000 / getWidth()) + gameSystem.getOffsetX();
        int yCoordinate = (mousePosY * 1000 / getHeight()) + gameSystem.getOffsetY();
        gameSystem.tick(shooting, xCoordinate, yCoordinate);

        playerAngle();
        repaint();
    }
    private void playerAngle(){
        angle = Math.toDegrees(Math.atan2(mousePosY - getHeight() / 2, mousePosX - getWidth() / 2));
    }
}















