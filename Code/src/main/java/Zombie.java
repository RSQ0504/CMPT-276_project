import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * class to implement and manage dynamic enemy
 */
public class Zombie extends DynamicCharacter{
    /**
     * The attributes of zombie: the number of HP decrease from MainCharacter (damage)
     */

    private MainCharacter aim;

    /**
     * class constructor
     */
    public Zombie(){
        // just use for test
    }

    /**
     * class constructor
     * @param gameFrame
     * @param x
     * @param y
     * @param mc
     */
    public Zombie(GameFrame gameFrame, int x, int y, MainCharacter mc){
        this.frame = gameFrame;
        this.aim = mc;

        setDefaultValue(x,y);
        getZombieImages();
        hitAreaStatic = new Rectangle((int) 1,45,frame.getCellSize()/2, (int) (frame.getCellSize()));
    }

    /**
     * Read and import images for character
     */
    public void getZombieImages() {
        try{
            up1 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp1.png"));
            up2 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp2.png"));
            up3 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp3.png"));
            up4 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp4.png"));

            down1 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown1.png"));
            down2 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown2.png"));
            down3 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown3.png"));
            down4 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown4.png"));

            right1 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight1.png"));
            right2 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight2.png"));
            right3 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight3.png"));
            right4 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight4.png"));

            left1 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft1.png"));
            left2 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft2.png"));
            left3 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft3.png"));
            left4 = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft4.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * set starting points and speed of character
     * @param x
     * @param y
     */
    private void setDefaultValue(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 1;
    }

    /**
     * update character positioning each update
     */
    void updateZombie(){
        collision = false;
        frame.check_collision.checkTile(this);

        if (collision == false) {
            switch (direction){
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
            spriteCounter++;
            if (spriteCounter > 30) {
                spriteNum = spriteNum + 1;
                if(spriteNum > 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if(!closedToAim()){
            spriteCounter++;
            if(spriteCounter > 120){
                Random random = new Random();
                int randomNum = random.nextInt(100)+1;
                if(randomNum <= 25){
                    direction = "up";
                }
                else if(randomNum > 25 && randomNum <= 50){
                    direction = "down";
                }
                else if(randomNum > 50 && randomNum <= 75){
                    direction = "left";
                }
                else{
                    direction = "right";
                }
                spriteCounter = 0;
            }
        }
        else{
            int x_distance = this.x - aim.x;
            int y_distance = this.y - aim.y;
            if(x_distance>0){
                // left
                direction = "left";
                if(x_distance<y_distance){
                    // up
                    direction = "up";
                }
                else if(x_distance < -y_distance){
                    // down
                    direction = "down";
                }
            }
            else{
                // right
                direction = "right";
                if(-x_distance < y_distance){
                    // up
                    direction = "up";
                }
                else if(-x_distance < -y_distance){
                    // down
                    direction = "down";
                }
            }
        }
    }

    /**
     * check if enemy is close to player
     * @return
     */
    public boolean closedToAim() {
        if((this.x < (aim.x + 150)) && (this.x > (aim.x - 150)) && (this.y < (aim.y + 150)) && (this.y > (aim.y - 150))){
            return true;
        }
        else
            return false;
    }

    /**
     * draw character
     * @param g2
     */
    void drawZombie(Graphics2D g2){
        BufferedImage zombie_image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1)
                    zombie_image = up1;
                else if(spriteNum == 2)
                    zombie_image = up2;
                else if(spriteNum == 3)
                    zombie_image = up3;
                else
                    zombie_image = up4;
                break;
            case "down":
                if(spriteNum == 1)
                    zombie_image = down1;
                else if(spriteNum == 2)
                    zombie_image = down2;
                else if(spriteNum == 3)
                    zombie_image = down3;
                else
                    zombie_image = down4;
                break;
            case "left":
                if(spriteNum == 1)
                    zombie_image = left1;
                else if(spriteNum == 2)
                    zombie_image = left2;
                else if(spriteNum == 3)
                    zombie_image = left3;
                else
                    zombie_image = left4;
                break;
            case "right":
                if(spriteNum == 1)
                    zombie_image = right1;
                else if(spriteNum == 2)
                    zombie_image = right2;
                else if(spriteNum == 3)
                    zombie_image = right3;
                else
                    zombie_image = right4;
                break;
        }
        g2.drawImage(zombie_image,x,y,zombie_image.getWidth(),zombie_image.getHeight(),null);
    }

    /**
     * check for hit intersection
     * @param mc
     * @return
     */
    public boolean check(Rectangle mc){
      hitAreaStatic = new Rectangle(this.x,this.y,5,5);
      return hitAreaStatic.intersects(mc);
    }
}
