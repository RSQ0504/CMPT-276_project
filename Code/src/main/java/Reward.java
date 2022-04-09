import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * class to implement and manage reward objects
 */
abstract class Reward extends GameObject{

  /**
   * set appear status
   * @param t
   */
  public void setAppear(boolean t){
    appearStatus = t;
  }

  /**
   * get appeat status
   * @return
   */
  public boolean getAppear(){
    return appearStatus;
  }

  /**
   * Draw rewards
   * @param g
   */
  public void draw(Graphics2D g){
    if(getAppear())
      g.drawImage(image,x,y,image.getWidth(), image.getHeight(), null);
  }

  /**
   * check if player intersects with reward
   * @param mc
   * @return
   */
  public boolean check(Rectangle mc){
    return hitAreaStatic.intersects(mc);
  }
}
