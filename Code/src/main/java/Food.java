import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Food extends Reward {
  private int HP_increase;

  public Food(GameFrame frame,int x,int y) throws IOException {
    setFrame(frame);
    this.x = x;
    this.y = y;
    image = ImageIO.read(new File("src/main/java/picture/Rewards/Food2_24x24.png"));
    this.width = 25;
    this.height = 25;
    hitAreaStatic = new Rectangle(this.x,this.y-10,this.width,this.height);
  }

  public void increaseHP(MainCharacter m){
    m.setHP(m.getHP()+1);
  }
}
