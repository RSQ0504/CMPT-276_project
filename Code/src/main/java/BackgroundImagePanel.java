import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundImagePanel extends JPanel {
    public BufferedImage bgImage;
    int width = 0;
    int height = 0;
    public  BackgroundImagePanel(BufferedImage bg, int colm, int rows, int cellSize) {
        bgImage = bg;
        width = cellSize*colm;
        height = cellSize*rows;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D)g;
//        g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        System.out.println("[1] Attempting to draw background image.");
        g.drawImage(bgImage, 0, 0, null);
        System.out.println("[2] Attempting to draw background image.");
    }
}
