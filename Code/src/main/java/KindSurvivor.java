import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * class to implement character which increases player HP
 */
public class KindSurvivor extends StaticCharacter{
    /**
     * The attributes of kind survivor: the door he can open
     */
    private int[][] map;
    private int door_x, door_y;

    /**
     * The constructor for KindSurvivor
     * The parameters are:
     *      gameFrame--The frame that show the game
     *      key -- The key input
     *      mc -- the main character
     *      x -- the x position of the kind survivor
     *      y -- the y position of the kind survivor
     *      Board -- the GameMap
     *      door_x -- the x position of the door that kind survivor can open
     *      door_y -- the y position of the door that kind survivor can open
     */
    public KindSurvivor(GameFrame gameFrame, inputKey key, MainCharacter mc, int x, int y, int[][] Board,int door_x, int door_y){
        this.gf = gameFrame;
        this.k = key;
        this.mc = mc;
        this.map = Board;
        this.width = 30;
        this.height = 30;

        getSurvivorImages();
        setDefaultValue(x,y,door_x,door_y);
    }

    /**
     * reset maze
     * @param Board
     */
    public void resetBoard(int[][] Board){
      this.map = Board;
    }

    /**
     * set starting point and attributes for good character and their dialog message box
     * @param x
     * @param y
     * @param door_x
     * @param door_y
     */
    private void setDefaultValue(int x, int y,int door_x, int door_y){
        this.direction = "down";
        this.x = x;
        this.y = y;
        this.door_x = door_x;
        this.door_y = door_y;
        this.speakMessageShow = false;
        this.message_x = x;
        this.message_y = y - message_image.getHeight();
        this.message_width = message_image.getWidth();
        this.message_height = message_image.getHeight();
    }

    /**
     * Set exit wall to floor for player to exit
     */
    public void openDoor() {
        this.map[door_x][door_y] = 0;
    }

    /**
     * read and import character image
     */
    public void getSurvivorImages(){
        try{
            message_image= ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_goodPerson.png"));
            message_image_incomplete= ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_goodPerson2.png"));
            up = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToUp.png"));
            down = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToDown.png"));
            right = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToRight.png"));
            left = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToLeft.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * check if player pressed F while in close proximity with static enemy
     * if close then display message box and open door if all required vaccines are collected by player
     */
    void speak(){
        if(status && (this.x < (mc.x + 40)) && (this.x > (mc.x - 40)) && (this.y < (mc.y + 40)) && (this.y > (mc.y - 40))){
            if(k.pressF == true) {
                int x_distance = this.x - mc.x;
                int y_distance = this.y - mc.y;
                if (x_distance > 0) {
                    // left
                    direction = "left";
                    if (x_distance < y_distance) {
                        // up
                        direction = "up";
                    } else if (x_distance < -y_distance) {
                        // down
                        direction = "down";
                    }
                } else {
                    // right
                    direction = "right";
                    if (-x_distance < y_distance) {
                        // up
                        direction = "up";
                    } else if (-x_distance < -y_distance) {
                        // down
                        direction = "down";
                    }
                }

                speakMessageShow = true;
            } else if (speakMessageShow == true){
                speakTimeCounter++;
                if(speakTimeCounter >200){
                    speakMessageShow = false;
                    speakTimeCounter = 0;
                    if(mc.getVaccines() >= gf.numOfVaccines){
                        openDoor();
                        status = false;
                    }
                }
            }



        }
    }

    /**
     * draw character dialog box
     * @param g2
     */
    void drawKindCharcMsgBox(Graphics2D g2){
        speak();
        if(speakMessageShow){
            if (message_x+message_width >= 768){
                message_x = 768 - (message_width+10);
            }
            if (mc.getVaccines() >= gf.numOfVaccines){
                g2.drawImage(message_image,message_x,message_y,message_width,message_height,null);
            } else {
                g2.drawImage(message_image_incomplete,message_x,message_y,message_width,message_height,null);
            }
        }
    }

    /**
     * draw character
     * @param g2
     */
    void drawKindCharacter(Graphics2D g2){
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

    }
}
