// Das Spielfeld ist 1000*1000 Logische Koordinaten groß. Der Spieler soll aber nur auf den inneren 800*800 bewegen. Darüber hinaus nur Mob-spawns "off-screen"

import java.awt.*;

public class GameSystem {

    private boolean running = true;
    private Player p1 = new Player();
    private Mobs gameMobs = new Mobs();
    private int tickNumber;
    private double spawnMultiplier = 0.015;
    private int offsetX = 0;
    private int offsetY = 0;
    private int levelRequirement = 4;

    public GameSystem() {

    }

    public void tick(boolean shooting, int xCoordinate, int yCoordinate) {

        if (running) {
            int playerX = p1.getPosX();
            int playerY = p1.getPosY();
            offsetX = p1.getPosX() - 500;
            offsetY = p1.getPosY() - 500;

            tickNumber++;

            //regen Player life

            if (p1.getHealth() < 100) {
                p1.damage(-p1.getLifeRegen()); //heal 1 hp per tick
            }

            //mob spawns

            if (Math.random() > 1 - spawnMultiplier * Math.log(tickNumber)) {
                gameMobs.add();
            }

            //enemy movement

            gameMobs.moveAllMobs(playerX, playerY);
            gameMobs.crystalPickup(playerX, playerY);
            levelPower(gameMobs.getCollectedCrystals());
            p1.damage(gameMobs.damageOnPlayer(playerX, playerY));
            if (p1.getHealth() <= 0) {
                running = false;
            }
            if (shooting) shoot(xCoordinate, yCoordinate);
            //check for game loss
        }
    }

    private void levelPower(int collectedCrystals) {
        if (collectedCrystals > levelRequirement) {
            Powerlevel.getInstance().incrementPowerlevel();
            levelRequirement += levelRequirement;
        }
    }

    public void drawPowerLevel(Graphics g, int resX, int resY) {

        g.drawString(String.valueOf(gameMobs.getCollectedCrystals()), (int) (resX * 0.8), (int) (resY * 0.8));
        g.drawString("    of " + levelRequirement, (int) (resX * 0.8), (int) (resY * 0.8));
        g.setColor(Color.gray);
        g.fillRect((int)(resX * 0.7), (int) (resY * 0.85), (int) (resX * 0.3), (int) (resY * 0.15));
        g.setColor(Color.cyan);

        double zahler = gameMobs.getCollectedCrystals()-levelRequirement/2;
        double nenner = levelRequirement/2;
        for (int i = 0; i < (int)(10*(zahler/nenner)); i++) {
            g.fillRect((int)(0.705*resX + i*0.029*resX), (int)(resY*0.855), (int)(resX*0.026),(int)(resY*0.14));
        }
    }

    public void movePlayerLeft() {
        p1.move(-1, 0);
    }

    public void movePlayerRight() {
        p1.move(1, 0);
    }

    public void movePlayerUp() {
        p1.move(0, -1);
    }

    public void movePlayerDown() {
        p1.move(0, 1);
    }

    public void drawPlayer(Graphics g, int resX, int resY, double angle) {
        p1.draw(g, resX, resY);
        p1.setDir(p1.getDirection(angle));
    }

    public void drawMobs(Graphics g, int resX, int resY) {
        gameMobs.drawAllMobs(g, resX, resY, offsetX, offsetY, p1.getPosX(), p1.getPosY());
    }

    public void shoot(int mouseX, int mouseY) {
        int x1 = p1.getPosX();
        int y1 = p1.getPosY();
        int x2 = mouseX;
        int y2 = mouseY;
        gameMobs.shootMobs(x1, y1, x2, y2);
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public boolean lost() {
        return !running;
    }


}
