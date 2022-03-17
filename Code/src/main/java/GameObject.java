import java.awt.*;
import java.awt.image.BufferedImage;

abstract class GameObject {
  public int x,y;
  public int width;
  int height;
    private boolean collision;
    public Rectangle hitArea;
    public boolean collisionArea;
    private GameFrame frame;

  BufferedImage image;

  public boolean getCollision(){return collision; }

  public void setCollision(boolean collision){ this.collision = collision; }

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

  public void isWall(){

    }
}
