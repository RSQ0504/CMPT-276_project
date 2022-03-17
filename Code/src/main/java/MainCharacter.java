import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainCharacter extends DynamicCharacter{
    /**
     * The attributes of main character: the number of vaccines, the HP
     */
    private int vaccines;
    private int HP;

    private GameFrame gf;
    private inputKey key;
    public BufferedImage MC_image;

    public MainCharacter(GameFrame gameFrame, inputKey key){
        this.gf = gameFrame;
        this.key = key;
        this.width = 30;
        this.height = 30;
        this.lvl = 2;
        System.out.println("Heyyyyy " + gf.gameLevel);

        setDefaultValue(gf.tileFrame.getStartPoints(gf.gameLevel));
        getMCImages();
        hitArea = new Rectangle((int) 1,45,gf.getCellSize()/2, (int) (gf.getCellSize()));
    }

    // Set Start point
    private void setDefaultValue(int[] startPoints){
        x = startPoints[0];
        y = startPoints[1];
        speed = 1 ;
    }

    /**
     * Return the number of vaccine
     */
    public int getVaccines() {
        return vaccines;
    }

    /**
     * Return the HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * Set the number of vaccines
     */
    public void setVaccines(int vaccines) {
        this.vaccines = vaccines;
    }

    /**
     * Set the HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    public void getMCImages(){
        try{
            up1 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp1.png"));
            up2 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp2.png"));
            up3 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp3.png"));
            up4 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp4.png"));

            down1 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown1.png"));
            down2 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown2.png"));
            down3 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown3.png"));
            down4 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown4.png"));

            right1 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight1.png"));
            right2 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight2.png"));
            right3 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight3.png"));
            right4 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight4.png"));

            left1 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft1.png"));
            left2 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft2.png"));
            left3 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft3.png"));
            left4 = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft4.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void updateMC(){
        if(key.pressedUp == true || key.pressedDown == true || key.pressedLeft == true || key.pressedRight == true) {
            if (key.pressedUp == true) {
                direction = "up";
            } else if (key.pressedDown == true) {
                direction = "down";
            } else if (key.pressedLeft == true) {
                direction = "left";
            } else if (key.pressedRight == true) {
                direction = "right";
            }

            collisionArea = false;
            gf.check_collision.checkTile(this);

            if (collisionArea == false) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;

                }
            }


            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 4 + 1;
                spriteCounter = 0;
            }
        }
    }

    void drawMC(Graphics2D g2){
        MC_image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1)
                    MC_image = up1;
                else if(spriteNum == 2)
                    MC_image = up2;
                else if(spriteNum == 3)
                    MC_image = up3;
                else
                    MC_image = up4;
                break;
            case "down":
                if(spriteNum == 1)
                    MC_image = down1;
                else if(spriteNum == 2)
                    MC_image = down2;
                else if(spriteNum == 3)
                    MC_image = down3;
                else
                    MC_image = down4;
                break;
            case "left":
                if(spriteNum == 1)
                    MC_image = left1;
                else if(spriteNum == 2)
                    MC_image = left2;
                else if(spriteNum == 3)
                    MC_image = left3;
                else
                    MC_image = left4;
                break;
            case "right":
                if(spriteNum == 1)
                    MC_image = right1;
                else if(spriteNum == 2)
                    MC_image = right2;
                else if(spriteNum == 3)
                    MC_image = right3;
                else
                    MC_image = right4;
                break;
        }
        g2.drawImage(MC_image,x,y, (gf.getCellSize()/3), gf.getCellSize(), null);
    }
}
