
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract class Reward extends GameObject{
  public boolean appearStatus;

  public void setAppear(boolean t){
    appearStatus = t;
  }
  public boolean getAppear(){
    return appearStatus;
  }


}
