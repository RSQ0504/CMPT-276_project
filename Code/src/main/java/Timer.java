import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Timer {
    public BufferedImage timerImg;
    Timer(){
        getTimerImg();
    }

    private void getTimerImg() {
        try{
            timerImg = ImageIO.read(new File("src/main/java/picture/GUI_image/Time_panel.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(timerImg, 600,0, 90,40,null);
        g2.drawString("Timer", 500,0);
    }

}
