import junit.framework.Assert;
import org.junit.jupiter.api.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainCharacterTest {
    @Test
    public void testSetDefaultValue() throws IOException {
        MainCharacter mc= new MainCharacter();
        int[] numbers = new int[2];
        numbers[0] = 3;
        numbers[1] = 5;
        mc.setDefaultValue(numbers);
        Assert.assertEquals(3, mc.x);
        Assert.assertEquals(5, mc.y);
        Assert.assertEquals(2, mc.speed);
    }

    @Test
    public void testGetAndSetVaccines() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);

        int vaccines = mc.getVaccines();
        Assert.assertEquals(0, vaccines);

        mc.setVaccines(5);
        Assert.assertEquals(5,mc.getVaccines());
    }

    @Test
    public void testGetAndSetHP() throws IOException {
        MainCharacter mc = new MainCharacter();

        int HP = mc.getHP();
        Assert.assertEquals(1, HP);

        mc.setHP(5);
        Assert.assertEquals(5,mc.getHP());
    }

    @Test
    public void testGetMCImages() throws IOException {
        MainCharacter mc = new MainCharacter();
        mc.getMCImages();

        BufferedImage[] img = new BufferedImage[16];

        img[0] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp1.png"));
        img[1] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp2.png"));
        img[2] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp3.png"));
        img[3] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp4.png"));

        img[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown1.png"));
        img[5] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown2.png"));
        img[6] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown3.png"));
        img[7] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown4.png"));

        img[8] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight1.png"));
        img[9] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight2.png"));
        img[10] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight3.png"));
        img[11] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight4.png"));

        img[12] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft1.png"));
        img[13] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft2.png"));
        img[14] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft3.png"));
        img[15] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft4.png"));
        for(int i = 0; i < 16; i++){
            String[] MCImg = null;
            switch (i){
                case 0:
                    MCImg = mc.up1.toString().split(" ");
                    break;
                case 1:
                    MCImg = mc.up2.toString().split(" ");
                    break;
                case 2:
                    MCImg = mc.up3.toString().split(" ");
                    break;
                case 3:
                    MCImg = mc.up4.toString().split(" ");
                    break;
                case 4:
                    MCImg = mc.down1.toString().split(" ");
                    break;
                case 5:
                    MCImg = mc.down2.toString().split(" ");
                    break;
                case 6:
                    MCImg = mc.down3.toString().split(" ");
                    break;
                case 7:
                    MCImg = mc.down4.toString().split(" ");
                    break;
                case 8:
                    MCImg = mc.right1.toString().split(" ");
                    break;
                case 9:
                    MCImg = mc.right2.toString().split(" ");
                    break;
                case 10:
                    MCImg = mc.right3.toString().split(" ");
                    break;
                case 11:
                    MCImg = mc.right4.toString().split(" ");
                    break;
                case 12:
                    MCImg = mc.left1.toString().split(" ");
                    break;
                case 13:
                    MCImg = mc.left2.toString().split(" ");
                    break;
                case 14:
                    MCImg = mc.left3.toString().split(" ");
                    break;
                case 15:
                    MCImg = mc.left4.toString().split(" ");
                    break;
                default:
                    break;
            }

            for(int j = 1; j < MCImg.length; j++)
                Assertions.assertEquals(img[i].toString().split(" ")[j], MCImg[j]);
        }
    }

    @Test
    public void testUpdateMC() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);

        int[] startPoint = {0,0};
        mc.setDefaultValue(startPoint);
        Assert.assertEquals(startPoint[0],mc.x);
        Assert.assertEquals(startPoint[0],mc.y);

        // Walk up 1 step
        key.pressedUp = true;
        mc.setCollision(false);
        mc.updateMC();
        startPoint = new int[]{0, 0};
        Assert.assertEquals(startPoint[0],mc.x);
        Assert.assertEquals(startPoint[1],mc.y);

        // Walk Left 1 step
        key.pressedLeft = true;
        mc.setCollision(false);
        mc.updateMC();
        startPoint = new int[]{0, 0};
        Assert.assertEquals(startPoint[0],mc.x);
        Assert.assertEquals(startPoint[1],mc.y);

        // Can not walk right 1 step because collision
        key.pressedRight = true;
        mc.setCollision(true);
        mc.updateMC();
        startPoint = new int[]{0, 0};
        Assert.assertEquals(startPoint[0],mc.x);
        Assert.assertEquals(startPoint[1],mc.y);

        // Can not walk down 1 step because collision
        key.pressedDown = true;
        mc.setCollision(true);
        mc.updateMC();
        startPoint = new int[]{0, 0};
        Assert.assertEquals(startPoint[0],mc.x);
        Assert.assertEquals(startPoint[1],mc.y);
    }

    @Test
    public void testResetAttributesMC() {
        MainCharacter mc = new MainCharacter();

        mc.setHP(5);
        mc.setVaccines(10);
        Assert.assertEquals(5,mc.getHP());
        Assert.assertEquals(10,mc.getVaccines());

        mc.resetAttributesMC();
        Assert.assertEquals(1,mc.getHP());
        Assert.assertEquals(0,mc.getVaccines());
    }

    @Test
    void drawTest() throws InterruptedException, IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {0,0};
        mc.setDefaultValue(startPoint);

        class drawTest extends JPanel {
            private Frame frame = new Frame("BadSurvivorTest");
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                mc.draw(g2);
                mc.drawScore(g2,50,10,5);
            }

            public void init() {
                drawTest Draw = new drawTest();
                Draw.repaint();
                Draw.setPreferredSize(new Dimension(100,70));
                frame.add(Draw);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                frame.pack();
                frame.setVisible(true);
            }
        }
        drawTest test = new drawTest();
        test.init();
        Thread.sleep(1000);
    }
}
