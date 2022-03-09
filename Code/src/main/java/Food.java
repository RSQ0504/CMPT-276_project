
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Food extends Reward {
  private int HP_increase;

  public Food(int x,int y,String path) throws IOException {
    this.x = x;
    this.y = y;
    image = ImageIO.read(new File(path));
    this.width =10;
    this.height = 10;
    draw.repaint();
  }


  public void increaseHP(Maincharacter m){
    m.setHP(m.getHP()+1);
  }
}
