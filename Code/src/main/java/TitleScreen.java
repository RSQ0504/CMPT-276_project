import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    BufferedImage bgImage;


    // constructor
    TitleScreen(int colm, int rows,  int cellSize){
        this.width = cellSize*colm;
        this.height = cellSize*rows;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0x123456));// change color later
        this.add(startBtn);
        this.add(optionBtn);
        this.add(exitBtn);
        try {
            bgImage = ImageIO.read(new File("src/main/java/picture/GUI_image/titleScreenBg.jpg"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(bgImage, 0, 0, this);
    }

    // user click action


}
