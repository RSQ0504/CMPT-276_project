import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TitleScreen extends JPanel{
    // screen size
    private int width;
    private int height;

    // screen display content
    String[] levels = {"EASY", "MEDIUM", "HARD"};
    int gameLevel = 0;
    JButton startBtn = new JButton("START GAME");
    JButton optionBtn = new JButton("CHANGE LEVEL");
    JButton exitBtn = new JButton("EXIT");

    // constructor
    TitleScreen(int colm, int rows,  int cellSize){
        this.width = cellSize*colm;
        this.height = cellSize*rows;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.DARK_GRAY);// change color later
        this.add(startBtn);
        this.add(optionBtn);
        this.add(exitBtn);
    }

    // user click action



//    @Override
//    protected void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        dbImage = ImageIO.read(new File("titleScreenBg.jpg"));
//        g.drawImage(bgImage, 0, 0, null);
//
//    }

}
