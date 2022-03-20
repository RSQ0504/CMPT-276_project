import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for managing Static and Dynamic game objects
 */
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

  /**
   * get collision boolean
   * @return
   */
  public boolean getCollision(){return collision; }

  /**
   * set collision boolean
   * @param collision
   */
  public void setCollision(boolean collision){ this.collision = collision; }

  /**
   * get image for game objects
   * @return
   */
  public BufferedImage getImage(){
    return image;
  }

  /**
   * get X points of objects
   * @return
   */
  public int getX(){
    return x;
  }

  /**
   * Get Y Points of objects
   * @return
   */
  public int getY(){
    return y;
  }

  /**
   * get object width
   * @return
   */
  public int getWidth(){
    return width;
  }

  /**
   * get object height
   * @return
   */
  public int getHeight(){
    return height;
  }

  /**
   * set private variable frame
   * @param frame
   */
  public void setFrame(GameFrame frame){this.frame = frame;}
}
