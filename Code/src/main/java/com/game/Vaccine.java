package com.game;

import com.game.Reward;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vaccine extends Reward {

  public void increaseVaccine(MainCharacter m){
    m.setVaccines(m.getVaccines()+1);
  }

  public Vaccine(int x,int y,String path) throws IOException {
    this.x = x;
    this.y = y;
    image = ImageIO.read(new File(path));
    this.width =10;
    this.height = 10;
    draw.repaint();
  }

}
