import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BadSurvivor extends StaticCharacter{

    public BadSurvivor(GameFrame gameFrame, inputKey key, MainCharacter mc, int x, int y){
        this.gf = gameFrame;
        this.k = key;
        this.mc = mc;
        this.width = 30;
        this.height = 30;

        getSurvivorImages();
        setDefaultValue(x,y);
    }

    private void setDefaultValue(int x, int y){
        this.direction = "down";
        this.x = x;
        this.y = y;
        this.speakMessageShow = false;
        this.message_x = x;
        this.message_y = y - message_image.getHeight();
        this.message_width = message_image.getWidth();
        this.message_height = message_image.getHeight();
    }

    public void getSurvivorImages(){
        try{
            message_image= ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_badPerson.png"));
            up = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToUp.png"));
            down = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToDown.png"));
            right = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToRight.png"));
            left = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToLeft.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void speak(){
        if((this.x < (mc.x + 40)) && (this.x > (mc.x - 40)) && (this.y < (mc.y + 40)) && (this.y > (mc.y - 40))){
            if(k.pressF == true){
                int x_distance = this.x - mc.x;
                int y_distance = this.y - mc.y;
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
                speakMessageShow = true;
                // damage
                mc.setHP(mc.getHP()-1);
            }
        }
        else{
            speakMessageShow = false;
        }
    }

    void drawBadCharacter(Graphics2D g2){
        BufferedImage character_image = null;
        speak();
        switch (direction){
            case "up":
                character_image = up;
                break;
            case "down":
                character_image = down;
                break;
            case "left":
                character_image = left;
                break;
            case "right":
                character_image = right;
                break;
        }
        g2.drawImage(character_image,x,y,character_image.getWidth(),character_image.getHeight(),null);
        if(speakMessageShow){
            g2.drawImage(message_image,message_x,message_y,message_width,message_height,null);
        }
    }
}
