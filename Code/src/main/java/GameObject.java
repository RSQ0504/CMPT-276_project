import java.awt.*;
import java.awt.image.BufferedImage;

abstract class GameObject {
<<<<<<< HEAD
  public int x,y;
  public int width;
  int height;
=======
    private boolean collision;
    public Rectangle hitArea;
    public boolean collisionArea;
    private GameFrame frame;


>>>>>>> 827176d9c682e2c32a8f70e90fe053848ddf081d

  private boolean collision;

<<<<<<< HEAD
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
=======
    public void setCollision(boolean collision){ this.collision = collision; }
    public void setFrame(GameFrame frame){this.frame = frame;}

    public void isWall(){

    }
>>>>>>> 827176d9c682e2c32a8f70e90fe053848ddf081d
}
