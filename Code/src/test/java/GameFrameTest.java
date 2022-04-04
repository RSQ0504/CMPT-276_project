import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class GameFrameTest {

    //Create GameFrame class to test
    int colm = 16;
    int rows = 12;
    int pacSize = 48; //industry standard 48x48

    GameFrame gfTest = new GameFrame(colm, rows, pacSize);

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
}
