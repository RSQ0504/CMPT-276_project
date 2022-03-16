import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48; //industry standard 48x48
        int lvl = 1;

        JFrame frame = new JFrame("Walking till the end");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GameFrame gameFrame = new GameFrame(colm, rows, pacSize, lvl);
        frame.add(gameFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gameFrame.beginThread();


        // GameMap maze = new GameMap(sizeY, sizeX, pacSize);
        // maze.drawFrame();
    }


}
