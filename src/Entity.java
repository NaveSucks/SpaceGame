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
    public Entity.Direction getDirection(double angle) {
  /*     angle -= 11.5;
        double correspondingAngle = 270;
        for (Direction direction : Direction.values()) {
            if (angle < 0) {
                angle += 360;
            }
            else if (angle > 360) {
                angle -= 360;
            }
            if(correspondingAngle -22.5 < angle && angle < correspondingAngle){
                System.out.println(correspondingAngle -22.5 + " < " + angle + " < " + correspondingAngle + " therefore "+ direction);
                return direction;
            }
            correspondingAngle += 22.5;
        }
        return Direction.S;
*/
        if (angle < 0) {
            angle += 360;
        }
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
