import java.awt.image.BufferedImage;

/**
 * class to manage static objects
 */
public class StaticCharacter extends GameObject {
    /**
     * The x,y means the position of the static character
     * The message_x, message_y means the position of the message box of this static character
     * The width and height is the image's size
     * The message_width and message_height is the message box image's size
     */
    public int message_x, message_y, message_width, message_height;
    public MainCharacter mc;
    public boolean speakMessageShow;
    public int speakTimeCounter;

    public BufferedImage up, left, right, down, message_image, message_image_incomplete;
}
