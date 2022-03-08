package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Reward {
  public boolean appearStatus;

  public int x,y;
  public int width;
  int height;
  BufferedImage image;
  Draw draw = new Draw();

  public void setAppear(boolean t){
    appearStatus = t;
  }
  public boolean getAppear(){
    return appearStatus;
  }

  public void redraw(){
    draw.repaint();
  }

  protected class Draw extends JPanel {
    @Override
    public void paint(Graphics g){
      if(getAppear())
        g.drawImage(image,x,y,null);
    }
  }

}
