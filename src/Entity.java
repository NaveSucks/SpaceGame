import java.awt.*;

public abstract class Entity {


    private int posX;
    private int posY;
    private int size;
    private int hitpoints = 100;

    public enum Direction {
        N, NNE, NE, NEE, E, SEE, SE, SSE, S, SSW, SW, SWW, W, NWW, NW, NNW;
    }
    Direction direction;

    public Entity(){
        posX = 0;
        posY = 0;
        size = 50;
        direction = Direction.S;
    }

    public Direction getDir(){
        return direction;
    }
    public void setDir(Direction direction){
        this.direction = direction;
    }
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void moveX(int i) {
        posX += i;
    }

    public void moveY(int i) {
        posY += i;
    }

    public int getHealth(){
        return hitpoints;
    }

    public void damage(int amount){
        hitpoints -= amount;
    }

    public void drawHealth(Graphics g, int resX, int resY, int offsetX, int offsetY, int visualPosX, int visualPosY){


        if (getHealth() < 20){
           g.setColor(Color.black);
           g.fillRect(visualPosX, visualPosY - getSize()/2, getSize(), getSize()/5);
           g.setColor(Color.red);
           g.fillRect(visualPosX, visualPosY - getSize()/2, getSize()/5, getSize()/5);
       }
       else if (getHealth() < 50) {
           g.setColor(Color.black);
           g.fillRect(visualPosX, visualPosY - getSize()/2, getSize(), getSize()/5);
           g.setColor(Color.orange);
           g.fillRect(visualPosX, visualPosY - getSize()/2, (getSize()/5) * 2, getSize()/5);
       }
       else if (getHealth() < 75) {
           g.setColor(Color.black);
           g.fillRect(visualPosX, visualPosY - getSize()/2, getSize(), getSize()/5);
           g.setColor(Color.yellow);
           g.fillRect(visualPosX, visualPosY - getSize()/2, (getSize()/5) * 3, getSize()/5);
       }
       else if (getHealth() < 100) {
           g.setColor(Color.black);
           g.fillRect(visualPosX, visualPosY - getSize()/2, getSize(), getSize()/5);
           g.setColor(Color.green);
           g.fillRect(visualPosX, visualPosY - getSize()/2, (getSize()/5) * 4, getSize()/5);
       }
   }
}
