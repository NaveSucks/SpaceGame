import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
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
    private Background background = new Background();
    private Bullets playerBullets = new Bullets();


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
        background.draw(g,getWidth(),getHeight(),gameSystem.getOffsetX(),gameSystem.getOffsetY());
        int width = this.getWidth();
        int height = this.getHeight();
        playerBullets.shoot(g, getWidth()/2,getHeight()/2, mousePosX, mousePosY, shooting);

        gameSystem.drawMobs(g, width, height);
        gameSystem.drawPlayer(g, width, height, playerDirection());
        g.setColor(Color.gray);
        g.drawRect((100 - gameSystem.getOffsetX()) * width / 1000, (100 - gameSystem.getOffsetY()) * height / 1000, 800 * width / 1000, 800 * height / 1000);
        if (gameSystem.lost()) {
            g.setColor(Color.red);
            g.fillRect(0,0,getWidth(),getHeight());
        }
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

        if (angle < 0) {
            angle += 360;
        }

    }

    private Entity.Direction playerDirection() {

        if(angle > 348.75 || angle < 11.25)return Entity.Direction.E;
        else if(angle < 33.75)return Entity.Direction.SEE;
        else if(angle < 56.25)return Entity.Direction.SE;
        else if(angle < 78.75)return Entity.Direction.SSE;
        else if(angle < 101.25)return Entity.Direction.S;
        else if(angle < 123.75)return Entity.Direction.SSW;
        else if(angle < 146.25)return Entity.Direction.SW;
        else if(angle < 168.75)return Entity.Direction.SWW;
        else if(angle < 191.25)return Entity.Direction.W;
        else if(angle < 213.75)return Entity.Direction.NWW;
        else if(angle < 236.25)return Entity.Direction.NW;
        else if(angle < 258.75)return Entity.Direction.NNW;
        else if(angle < 281.25)return Entity.Direction.N;
        else if(angle < 303.75)return Entity.Direction.NNE;
        else if(angle < 326.25)return Entity.Direction.NE;
        else if(angle < 348.75)return Entity.Direction.NEE;
        return Entity.Direction.S;
    }
}















