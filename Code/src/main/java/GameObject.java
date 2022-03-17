import java.awt.*;
import java.awt.image.BufferedImage;

abstract class GameObject {
  public int x,y;
  public int width;
  int height;
  private boolean collision;
  private GameFrame frame;
  public Rectangle hitAreaStatic;
  protected int lvl;
  protected GameMap gm;

  BufferedImage image;

  public boolean getCollision(){return collision; }

  public void setCollision(boolean collision){ this.collision = collision; }

  public void setLvl(int lvl, GameMap gm){
    this.lvl = lvl;
    this.gm = gm;
  }


  public BufferedImage getImage(){
    return image;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
  public void setFrame(GameFrame frame){this.frame = frame;}
}
