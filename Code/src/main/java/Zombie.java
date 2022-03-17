import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Zombie extends DynamicCharacter{
    /**
     * The attributes of zombie: the number of HP decrease from MainCharacter (damage)
     */
    private int damage;

    private GameFrame gf;
    private MainCharacter aim;

    public Zombie(GameFrame gameFrame, int x, int y, MainCharacter mc){
        this.gf = gameFrame;
        this.aim = mc;

        setDefaultValue(x,y);
        getZombieImages();
    }

    private void getZombieImages() {
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

    private void setDefaultValue(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 1;
    }
    
    void updateZombie(){
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

    public boolean closedToAim() {
        if((this.x < (aim.x + 150)) && (this.x > (aim.x - 150)) && (this.y < (aim.y + 150)) && (this.y > (aim.y - 150))){
            return true;
        }
        else
            return false;
    }

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
     * Return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Set the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void Damage(MainCharacter zombie){
        zombie.setHP(zombie.getHP()-damage);
    }
}
