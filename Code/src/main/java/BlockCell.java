import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class BlockCell extends JPanel {
    private int tileSize = 48; //industry standard 48x48
    private int col = 16;
    private int row = 12;
    private int width = tileSize*col;
    private int height = tileSize*row;

    public BlockCell() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.CYAN);
    }

}
