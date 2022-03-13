import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48; //industry standard 48x48
//        String[] levels = {"EASY", "MEDIUM", "HARD"};
//        int gameLevel = 0;
//        JButton startBtn = new JButton("START GAME");
//        JButton optionBtn = new JButton("CHANGE LEVEL");
//        JButton exitBtn = new JButton("EXIT");
//        JLabel levelDisplay = new JLabel(levels[gameLevel]);
//        JPanel titleScreen = new JPanel();

        JFrame frame = new JFrame("Survive in the end");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        TitleScreen titleScreen = new TitleScreen(colm, rows, pacSize);
        frame.add(titleScreen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        // display game screen when user clicks 'START'
//        click.addActionListener(this);
        if(false) {
            GameFrame gameFrame = new GameFrame(colm, rows, pacSize);
            frame.add(gameFrame);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            gameFrame.beginThread();
        }
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);


        // GameMap maze = new GameMap(sizeY, sizeX, pacSize);
        // maze.drawFrame();
    }


}
