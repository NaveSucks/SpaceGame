import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        MousePanel m = new MousePanel();
        this.add(m);
        this.setSize(1920,1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        new GameFrame();
    }

}
