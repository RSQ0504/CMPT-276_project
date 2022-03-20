import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * class to implement and manage vaccine rewards
 */
public class Vaccine extends Reward {

  /**
   * increase vaccine count that player has collected
   * @param m
   */
  public void increaseVaccine(MainCharacter m) {
    m.setVaccines(m.getVaccines() + 1);
  }


  /**
   * class constructor
   * @param frame
   * @param x
   * @param y
   * @throws IOException
   */
  public Vaccine(GameFrame frame, int x, int y) throws IOException {
    setFrame(frame);
    this.x = x;
    this.y = y;
    image = ImageIO.read(new File("src/main/java/picture/Rewards/Vaccine.png"));
    this.width = 25;
    this.height = 25;
    hitAreaStatic = new Rectangle(this.x,this.y-10,this.width,this.height);
  }

}
