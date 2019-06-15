import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Bullets {
    private int originX;
    private int originY;
    private int targetX;
    private int targetY;
    private double speed;
    LinkedList<Bullet> positions = new LinkedList<>();

    public Bullets() {

    }

    public void shoot(Graphics g, int originX, int originY, int targetX, int targetY, Boolean shooting) {
        int xDiff = targetX - originX;
        int yDiff = targetY - originY;
        speed = Powerlevel.getInstance().getBulletSpeed();
        int length = (int) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        int deltaX = (int) (xDiff * speed / length);
        int deltaY = (int) (yDiff * speed / length);

        if(shooting) {
            int amount = Powerlevel.getInstance().getBulletAmount();
            for(int j = amount; j > 0; j--){
                Bullet first = new Bullet(originX - deltaX * j/amount, originY - deltaY * j/amount); //originX - deltaX * j/amount, originY - deltaY * j/amount
                first.setDeltaX(deltaX);
                first.setDeltaY(deltaY);
                positions.add(0, first);
            }
        }
        if (positions.size() > 200) {
            for(int i = 0; i < Powerlevel.getInstance().getBulletAmount();i++){
                positions.removeLast();
            }
        }
        for (Bullet elem : positions) {
            elem.move();
            elem.draw(g,originX*2, originY*2);
        }
    }


}
