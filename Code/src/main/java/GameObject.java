import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for managing Static and Dynamic game objects
 */
abstract class GameObject {
  protected int x = 0,y = 0;
  protected int width, height;
  protected boolean collision = false;
  protected GameFrame frame;
  public Rectangle hitAreaStatic;
  public Rectangle hitArea;
  protected int lvl;

  BufferedImage image;
  protected inputKey key;
  protected String direction = "down";
  protected boolean appearStatus=true;

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
  public GameFrame getFrame(){return frame;}

  /**
   * Draw rewards
   * @param g
   */
  public void draw(Graphics2D g){
    if(appearStatus)
      g.drawImage(image,x,y,image.getWidth(), image.getHeight(), null);
  }

  /**
   * check if player intersects
   * @param mc
   * @return
   */
  public boolean check(Rectangle mc){
    return hitArea.intersects(mc);
  }
}
