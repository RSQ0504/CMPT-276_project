import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TimerClockTest {

    private TimerClock tcTest = new TimerClock();

    /**
     * Test correct image import in Class constructor
     */
    @Test
    public void getTimerImgTest() throws IOException {
        BufferedImage testImg = ImageIO.read(new File("src/main/java/picture/GUI_image/Time_panel.png"));
        Assertions.assertEquals((tcTest.timerImg).toString().substring(22), testImg.toString().substring(22));
    }

    /**
     * Test for correct timer start values
     */
    @Test
    public void startTimerTest(){
        tcTest.startTimer();
       Assertions.assertEquals(tcTest.getSecond(), 0);
        Assertions.assertEquals(tcTest.getMinute(), 0);
        Assertions.assertEquals(tcTest.getFormattedMinute(), "00");
        Assertions.assertEquals(tcTest.getFormattedSecond(), "00");
        System.out.println(tcTest.getSecond());


    }

    /**
     * Test correct return of Seconds
     */
    @Test
    public void getSecondTest(){
        Assertions.assertEquals(tcTest.getSecond(), 0);
    }

    /**
     * Test for timer stop
     */
    @Test
    public void stopTimerTest(){

    }

    /**
     * Test for correct time increment
     */
    @Test
    public void clockTimerTest(){

    }



}
