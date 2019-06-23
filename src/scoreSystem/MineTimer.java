package scoreSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MineTimer implements ActionListener {

    private static MineTimer instance;
    private Timer timer = new Timer(100, this);
    private double spielzeit;
    DecimalFormat df = new DecimalFormat();
    Font font;


    private MineTimer(){
        timer.start();
        df.setMaximumFractionDigits(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spielzeit += 0.1;
    }
    public static MineTimer getInstance () {
        if (MineTimer.instance == null) {
            MineTimer.instance = new MineTimer();
        }
        return MineTimer.instance;
    }
    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    public String spielzeit(){
        return ""+spielzeit;
    }
    public void reset(){
        spielzeit = 0;
    }

    public void draw(Graphics g, int resX, int resY){
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.PLAIN, resY/25);
        g.setFont(font);
        g.drawString(df.format(spielzeit), resX/2, resY/7);

    }

}
