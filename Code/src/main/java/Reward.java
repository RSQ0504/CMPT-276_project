
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract class Reward extends GameObject{
  public boolean appearStatus=true;

  public void setAppear(boolean t){
    appearStatus = t;
  }
  public boolean getAppear(){
    return appearStatus;
  }
  public void draw(Graphics2D g){
    if(getAppear())
      g.drawImage(image,x,y,image.getWidth(), image.getHeight(), null);
  }
  public boolean check(Rectangle mc){
    return hitArea.intersects(mc);
  }
}
