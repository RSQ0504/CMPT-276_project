import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.Timer;

public class TimerClock {
    public BufferedImage timerImg;
    private Timer timer;
    private int second, minute;
    private String secondFormatted, minuteFormatted;
    private DecimalFormat timeFormatted = new DecimalFormat("00");
    TimerClock(){

        getTimerImg();
        second = 0;
        minute = 0;
        secondFormatted = "00";
        minuteFormatted = "00";
        clockTimer();
        timer.start();
    }

    public void startTimer(){
        timer.restart();
    }

    public void stopTimer(){
        timer.stop();
    }

    private void getTimerImg() {
        try{
            timerImg = ImageIO.read(new File("src/main/java/picture/GUI_image/Time_panel.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void clockTimer(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second++;
                secondFormatted = timeFormatted.format(second);
                if (second == 60) {
                    second = 0;
                    minute++;
                    minuteFormatted = timeFormatted.format(minute);
                }
            }
        });
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(timerImg, 600,0, 90,40,null);
        g2.setColor(Color.black);
        g2.setFont(new Font("default", Font.BOLD, 16));
        g2.drawString(minuteFormatted + ":" + secondFormatted, 635,25);
    }

}
