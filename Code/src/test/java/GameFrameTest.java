import jdk.jshell.Snippet;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class GameFrameTest {

    //Create GameFrame class to test
    private int colm = 16;
    private int rows = 12;
    private int pacSize = 48; //industry standard 48x48

    GameFrame gfTest = new GameFrame(colm, rows, pacSize);
    inputKey key = new inputKey(gfTest);

    public GameFrameTest() throws IOException {
    }

    /**
     * Test correct variable set and image import once class constructor is called
     */
    @Test
    public void GFConstructorTest(){

    }

    /**
     * Test correct return of cellSize variable
     */
    @Test
    public void getCellSizeTest(){
        Assert.assertEquals(gfTest.getCellSize(), 48);
    }

    /**
     *Test for correct set of values when game is running inside thread
     */
    @Test
    public void runGameTest(){

    }

    /**
     * Test for correct reset of character positioning
     */
    @Test
    public void restGameTest(){
        gfTest.mc.setHP(2);
        gfTest.mc.setVaccines(2);

        //call method to test
        gfTest.resetGame();

        //main character test
        Assert.assertEquals(gfTest.mc.getHP(), 1);
        Assert.assertEquals(gfTest.mc.getVaccines(), 0);
        Assert.assertEquals(gfTest.mc.x, 365);
        Assert.assertEquals(gfTest.mc.y, 0);


    }
}
