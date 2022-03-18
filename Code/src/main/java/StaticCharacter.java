import java.awt.image.BufferedImage;

public class StaticCharacter {
    /**
     * The x,y means the position of the static character
     * The message_x, message_y means the position of the message box of this static character
     * The width and height is the image's size
     * The message_width and message_height is the message box image's size
     */
    public int x,y,width,height;
    public int message_x, message_y, message_width, message_height;
    public GameFrame gf;
    public inputKey k;
    public MainCharacter mc;
    public boolean speakMessageShow;
    public boolean status = true;
    public int speakTimeCounter;

    public BufferedImage up, left, right, down, message_image;
    public String direction;

    public String message;
}
