import java.awt.*;
import java.awt.image.BufferedImage;

abstract class GameObject {
    private boolean collision;
    public Rectangle hitArea;
    public boolean collisionArea;
    private GameFrame frame;



    public boolean getCollision(){return collision; }

    public void setCollision(boolean collision){ this.collision = collision; }
    public void setFrame(GameFrame frame){this.frame = frame;}

    public void isWall(){

    }
}
