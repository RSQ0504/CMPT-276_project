import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GameMapTest {

    private int colm = 16;
    private int rows = 12;
    private int pacSize = 48; //industry standard 48x48

    //Make GameFrame class
    private GameFrame testGF = new GameFrame(colm,rows,pacSize);

    private GameMap gmTest = new GameMap(testGF);

    public GameMapTest() throws IOException {
    }

    /**
     * Test for correct return of end points
     * X = 30,Y = 540
     */
    @Test
    public void getEndPointTest() throws IOException {
        Assertions.assertEquals(30,gmTest.getEndPointX());
        Assertions.assertEquals(540,gmTest.getEndPointY());

    }

    /**
     * Test for correct board map set depending on game level
     */
    @Test
    public void setNewBoardTest(){
        int[][] maplvl1 = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,0,0,1} ,
                {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
                {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

        int[][] maplvl2 = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1} ,
                {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
                {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

        int maplvl3[][] = {
                {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,0,0,1} ,
                {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,1,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
                {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,0,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,0,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1},
                {1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

        int[][] maplvl1_correct = {
                {1,2,2,2,2,2,1,2,2,1,1,2,1,1,2,4,2,2,2,1,1,1,2,2,2,2,1,2,1,2,2,1},
                {9,5,3,3,3,3,2,5,3,2,2,5,2,2,5,6,3,3,3,2,2,2,5,3,3,3,15,5,15,5,3,8},
                {9,4,2,2,2,4,3,6,0,3,3,6,3,3,6,10,7,12,4,3,3,3,6,10,12,4,15,4,15,4,0,8},
                {9,4,3,3,3,2,2,4,0,0,11,4,0,2,2,13,1,9,4,2,2,2,2,2,2,4,15,4,15,4,0,8},
                {9,4,2,2,2,5,3,11,4,0,15,4,11,5,3,8,1,9,4,3,3,3,3,3,3,10,9,4,8,2,4,8},
                {9,4,3,3,3,6,0,15,4,0,8,2,9,4,0,8,2,2,7,2,2,4,10,12,4,2,2,4,15,5,6,8},
                {14,2,7,2,2,2,2,2,4,0,15,5,15,4,0,15,5,3,15,5,3,6,8,2,4,3,3,6,15,4,10,13},
                {9,5,2,5,3,3,3,3,6,0,15,4,2,4,0,15,4,10,13,2,12,4,15,5,2,7,12,4,15,4,8,1},
                {9,4,3,6,2,2,2,2,2,7,2,4,3,6,0,15,4,8,9,5,15,4,15,4,3,8,9,4,15,4,8,1},
                {9,4,2,2,5,3,3,3,3,2,5,6,2,2,2,9,4,2,2,4,15,4,8,12,4,2,2,4,15,4,8,1},
                {9,4,3,3,6,0,0,0,0,3,6,0,3,3,3,15,4,3,3,6,15,4,8,9,4,3,3,6,15,4,8,1},
                {14,2,2,2,7,2,2,2,12,4,10,7,7,12,4,8,7,12,4,10,9,4,8,14,7,2,2,4,2,4,8,1},
                {9,5,3,3,15,5,3,3,15,4,2,2,2,2,4,2,2,2,4,8,9,4,2,2,2,5,3,6,3,6,8,1},
                {9,4,11,4,15,4,11,4,15,4,3,3,3,3,6,3,3,3,6,8,9,4,3,3,3,10,12,4,11,4,8,1},
                {9,4,15,4,8,7,2,4,15,4,10,2,2,2,2,12,4,10,7,13,9,4,2,2,2,2,9,4,15,4,8,1},
                {9,4,15,4,2,2,5,6,15,4,15,5,3,3,3,15,4,8,1,2,2,2,5,3,3,3,15,4,15,4,2,1},
                {9,4,15,4,3,3,6,2,2,4,15,4,10,12,4,2,4,8,9,5,3,3,10,2,2,4,15,4,15,4,3,8},
                {9,4,8,7,7,7,12,5,3,6,15,4,8,9,4,3,6,8,9,4,11,4,15,5,3,6,15,4,8,2,4,8},
                {9,4,2,2,2,2,2,4,11,4,2,4,2,2,2,2,2,2,2,4,15,4,15,4,10,7,9,4,15,5,6,8},
                {9,4,3,3,3,3,3,6,15,4,3,6,3,3,3,3,3,3,3,6,15,4,15,4,2,2,9,4,15,4,0,8},
                {14,12,4,10,7,7,7,7,9,4,10,12,4,2,2,7,2,2,2,4,15,4,15,4,3,3,15,4,15,4,0,8},
                {1,2,2,2,2,2,2,2,2,4,8,9,4,3,3,2,5,3,3,6,15,4,2,4,2,4,8,2,2,2,4,8},
                {9,5,3,3,3,3,3,3,3,6,8,14,7,12,4,3,6,10,12,4,15,4,3,6,3,6,15,5,3,3,6,8},
                {14,7,7,7,7,7,7,7,7,7,13,1,1,14,7,7,7,13,14,7,13,7,7,7,7,7,13,7,7,7,7,13}
        };
        int[][] maplvl1_new = gmTest.setNewBoard(maplvl1);
        Assertions.assertEquals(Arrays.deepToString(maplvl1_correct),Arrays.deepToString(maplvl1_new));

        int[][] maplvl2_correct = {
                {1,2,2,2,2,2,1,2,2,1,1,2,1,1,2,2,2,2,2,1,1,1,2,2,2,2,1,1,1,2,4,8},
                {9,5,3,3,3,3,2,5,3,2,2,5,2,2,5,3,3,3,3,2,2,2,5,3,3,3,8,2,9,5,6,8},
                {9,4,2,2,2,4,3,6,0,3,3,6,3,3,6,10,7,12,4,3,3,3,6,10,12,4,15,5,15,4,0,8},
                {9,4,3,3,3,2,2,4,0,0,11,4,0,2,2,13,1,9,4,2,2,2,2,2,2,4,15,4,15,4,0,8},
                {9,4,2,2,2,5,3,11,4,0,15,4,11,5,3,8,1,9,4,3,3,3,3,3,3,10,9,4,8,2,4,8},
                {9,4,3,3,3,6,0,15,4,0,8,2,9,4,0,8,2,2,7,2,2,4,10,12,4,2,2,4,15,5,6,8},
                {14,2,7,2,2,2,2,2,4,0,15,5,15,4,0,15,5,3,15,5,3,6,8,2,4,3,3,6,15,4,10,13},
                {9,5,2,5,3,3,3,3,6,0,15,4,2,4,0,15,4,10,13,2,12,4,15,5,2,7,12,4,15,4,8,1},
                {9,4,3,6,2,2,2,2,2,7,2,4,3,6,0,15,4,8,9,5,15,4,15,4,3,8,9,4,15,4,8,1},
                {9,4,2,2,5,3,3,3,3,2,5,6,2,2,2,9,4,2,2,4,15,4,8,12,4,2,2,4,15,4,8,1},
                {9,4,3,3,6,0,0,0,0,3,6,0,3,3,3,15,4,3,3,6,15,4,8,9,4,3,3,6,15,4,8,1},
                {14,2,2,2,7,2,2,2,12,4,10,7,7,12,4,8,7,12,4,10,9,4,8,14,7,2,2,4,2,4,8,1},
                {9,5,3,3,15,5,3,3,15,4,2,2,2,2,4,2,2,2,4,8,9,4,2,2,2,5,3,6,3,6,8,1},
                {9,4,11,4,15,4,11,4,15,4,3,3,3,3,6,3,3,3,6,8,9,4,3,3,3,10,12,4,11,4,8,1},
                {9,4,15,4,8,7,2,4,15,4,10,2,2,2,2,12,4,10,7,13,9,4,2,2,2,2,9,4,15,4,8,1},
                {9,4,15,4,2,2,5,6,15,4,15,5,3,3,3,15,4,8,1,2,2,2,5,3,3,3,15,4,15,4,2,1},
                {9,4,15,4,3,3,6,2,2,4,15,4,10,12,4,2,4,8,9,5,3,3,10,2,2,4,15,4,8,12,5,8},
                {9,4,8,7,7,7,12,5,3,6,15,4,8,9,4,3,6,8,9,4,11,4,15,5,3,6,15,4,8,2,4,8},
                {9,4,2,2,2,2,2,4,11,4,15,4,2,2,2,2,2,2,2,4,15,4,15,4,10,7,9,4,15,5,6,8},
                {9,4,3,3,3,3,3,6,15,4,15,4,3,3,3,3,3,3,3,6,15,4,15,4,2,2,9,4,15,4,0,8},
                {14,12,4,10,7,7,7,7,9,4,2,2,4,2,2,7,2,2,2,4,15,4,15,4,3,3,15,4,15,4,0,8},
                {1,2,2,2,2,2,2,2,2,4,3,3,6,3,3,2,5,3,3,6,15,4,2,4,2,2,2,2,2,2,4,8},
                {9,5,3,3,3,3,3,3,3,6,10,7,7,12,4,3,6,10,12,4,15,4,3,6,3,3,3,3,3,3,6,8},
                {14,7,7,7,7,7,7,7,7,7,13,1,1,14,7,7,7,13,14,7,13,7,7,7,7,7,7,7,7,7,7,13}
        };
        int[][] maplvl2_new = gmTest.setNewBoard(maplvl2);
        Assertions.assertEquals(Arrays.deepToString(maplvl2_correct),Arrays.deepToString(maplvl2_new));

        int[][] maplvl3_correct = {
                {1,2,2,2,2,2,1,2,2,1,9,4,8,1,2,2,2,2,2,1,1,1,2,2,2,2,1,2,1,2,2,1},
                {9,5,3,3,3,3,2,5,3,2,2,4,2,2,5,3,3,3,3,2,2,2,5,3,3,3,15,5,15,5,3,8},
                {9,4,2,2,2,4,3,6,0,3,3,6,3,3,6,10,7,12,4,3,3,3,6,10,12,4,15,4,15,4,0,8},
                {9,4,3,3,3,2,2,4,0,0,11,4,0,2,2,13,1,9,4,2,2,2,2,2,2,4,15,4,15,4,0,8},
                {9,4,2,2,2,5,3,11,4,0,15,4,11,5,3,8,1,9,4,3,3,3,3,3,3,10,9,4,8,2,4,8},
                {9,4,3,3,3,6,0,15,4,0,15,4,15,4,0,8,2,2,7,2,2,4,10,12,4,2,2,4,15,5,6,8},
                {14,2,7,2,2,2,2,2,4,0,15,4,15,4,0,15,5,3,15,5,3,6,8,2,4,3,3,6,15,4,10,13},
                {9,5,2,5,3,3,3,3,6,0,15,4,2,4,0,15,4,10,13,2,12,4,15,5,2,7,12,4,15,4,8,1},
                {9,4,3,6,2,2,2,2,2,7,2,4,3,6,0,15,4,8,9,5,15,4,15,4,3,8,9,4,15,4,8,1},
                {9,4,2,2,5,3,3,3,3,2,5,6,2,2,2,9,4,2,2,4,15,4,8,12,4,2,2,4,2,4,8,1},
                {9,4,3,3,6,0,0,0,0,3,6,0,3,3,3,15,4,3,3,6,15,4,8,9,4,3,3,6,3,6,8,1},
                {14,2,2,2,7,2,2,2,12,4,10,7,7,12,4,8,7,12,4,10,9,4,8,14,7,2,2,4,0,0,8,1},
                {9,5,3,3,15,5,3,3,15,4,2,2,2,2,4,2,2,2,4,8,9,4,2,2,2,5,3,6,0,0,8,1},
                {9,4,11,4,15,4,11,4,15,4,3,3,3,3,6,3,3,3,6,8,9,4,3,3,3,10,12,4,11,4,8,1},
                {9,4,15,4,8,7,2,4,15,4,10,2,2,2,2,12,4,10,7,13,9,4,2,2,2,2,9,4,15,4,8,1},
                {9,4,15,4,2,2,5,6,15,4,15,5,3,3,3,15,4,8,1,2,2,2,5,3,3,3,15,4,15,4,2,1},
                {9,4,15,4,3,3,6,2,2,4,15,4,10,12,4,2,4,8,9,5,3,3,10,2,2,4,15,4,15,4,3,8},
                {9,4,8,7,7,7,12,5,3,6,15,4,8,9,4,3,6,8,9,4,11,4,15,5,3,6,15,4,8,2,4,8},
                {9,4,2,2,2,2,2,4,11,4,15,4,2,2,2,2,2,2,2,4,15,4,15,4,10,7,9,4,15,5,6,8},
                {9,4,3,3,3,3,3,6,15,4,15,4,3,3,3,3,3,3,3,6,15,4,15,4,2,2,9,4,15,4,0,8},
                {14,12,4,10,7,7,7,7,9,4,8,12,4,2,2,7,2,2,2,4,15,4,15,4,3,3,15,4,15,4,0,8},
                {1,9,4,2,2,2,2,2,2,4,8,9,4,3,3,2,5,3,3,6,15,4,2,4,2,4,8,2,2,2,4,8},
                {1,9,4,3,3,3,3,3,3,6,8,14,7,12,4,3,6,10,12,4,15,4,3,6,3,6,15,5,3,3,6,8},
                {1,14,7,7,7,7,7,7,7,7,13,1,1,14,7,7,7,13,14,7,13,7,7,7,7,7,13,7,7,7,7,13}
        };
        int[][] maplvl3_new = gmTest.setNewBoard(maplvl3);
        Assertions.assertEquals(Arrays.deepToString(maplvl3_correct),Arrays.deepToString(maplvl3_new));

        Assertions.assertEquals(gmTest.getOriginMap(3),null);
    }

    /**
     * Test for correct board map return for game start up depending on level
     */
    @Test
    public void getBoardTest(){
        int[][] maplvl1_correct = {
                {9,2,2,2,2,2,15,2,2,8,9,2,8,9,2,4,2,2,2,8,1,9,2,2,2,2,15,2,15,2,2,8},
                {9,5,3,3,3,3,2,5,3,2,2,5,2,2,5,6,3,3,3,2,2,2,5,3,3,3,15,5,15,5,3,8},
                {9,4,2,2,2,4,3,6,0,3,3,6,3,3,6,10,7,12,4,3,3,3,6,10,12,4,15,4,15,4,0,8},
                {9,4,3,3,3,2,2,4,0,0,11,4,0,2,2,8,1,9,4,2,2,2,2,2,2,4,15,4,15,4,0,8},
                {9,4,2,2,2,5,3,11,4,0,15,4,11,5,3,8,1,9,4,3,3,3,3,3,3,10,9,4,15,2,4,8},
                {9,4,3,3,3,6,0,15,4,0,15,2,15,4,0,15,2,2,11,2,2,4,10,12,4,2,2,4,15,5,6,8},
                {9,2,11,2,2,2,2,2,4,0,15,5,15,4,0,15,5,3,15,5,3,6,15,2,4,3,3,6,15,4,10,13},
                {9,5,2,5,3,3,3,3,6,0,15,4,2,4,0,15,4,10,9,2,11,4,15,5,2,10,12,4,15,4,8,1},
                {9,4,3,6,2,2,2,2,2,11,2,4,3,6,0,15,4,8,9,5,15,4,15,4,3,8,9,4,15,4,8,1},
                {9,4,2,2,5,3,3,3,3,2,5,6,2,2,2,15,4,2,2,4,15,4,8,12,4,2,2,4,15,4,8,1},
                {9,4,3,3,6,0,0,0,0,3,6,0,3,3,3,15,4,3,3,6,15,4,8,9,4,3,3,6,15,4,8,1},
                {9,2,2,2,11,2,2,2,11,4,10,7,7,12,4,8,7,12,4,10,9,4,8,14,12,2,2,4,2,4,8,1},
                {9,5,3,3,15,5,3,3,15,4,2,2,2,2,4,2,2,2,4,8,9,4,2,2,2,5,3,6,3,6,8,1},
                {9,4,11,4,15,4,11,4,15,4,3,3,3,3,6,3,3,3,6,8,9,4,3,3,3,10,12,4,11,4,8,1},
                {9,4,15,4,8,12,2,4,15,4,11,2,2,2,2,11,4,10,7,13,9,4,2,2,2,2,15,4,15,4,8,1},
                {9,4,15,4,2,2,5,6,15,4,15,5,3,3,3,15,4,8,9,2,2,2,5,3,3,3,15,4,15,4,2,8},
                {9,4,15,4,3,3,6,2,2,4,15,4,10,12,4,2,4,8,9,5,3,3,11,2,2,4,15,4,15,4,3,8},
                {9,4,8,7,7,7,12,5,3,6,15,4,8,9,4,3,6,8,9,4,11,4,15,5,3,6,15,4,15,2,4,8},
                {9,4,2,2,2,2,2,4,11,4,2,4,2,2,2,2,2,2,2,4,15,4,15,4,10,7,9,4,15,5,6,8},
                {9,4,3,3,3,3,3,6,15,4,3,6,3,3,3,3,3,3,3,6,15,4,15,4,2,2,15,4,15,4,0,8},
                {14,12,4,10,7,7,7,7,9,4,10,12,4,2,2,11,2,2,2,4,15,4,15,4,3,3,15,4,15,4,0,8},
                {9,2,2,2,2,2,2,2,2,4,8,9,4,3,3,2,5,3,3,6,15,4,2,4,2,4,15,2,2,2,4,8},
                {9,5,3,3,3,3,3,3,3,6,8,14,7,12,4,3,6,10,12,4,15,4,3,6,3,6,15,5,3,3,6,8},
                {14,7,7,7,7,7,7,7,7,7,13,1,1,14,7,7,7,13,14,7,13,7,7,7,7,7,13,7,7,7,7,13}
        };

        int[][] maplvl2_correct = {
                {9,2,2,2,2,2,15,2,2,8,9,2,8,9,2,2,2,2,2,8,1,9,2,2,2,2,8,1,9,2,4,8},
                {9,5,3,3,3,3,2,5,3,2,2,5,2,2,5,3,3,3,3,2,2,2,5,3,3,3,15,2,15,5,6,8},
                {9,4,2,2,2,4,3,6,0,3,3,6,3,3,6,10,7,12,4,3,3,3,6,10,12,4,15,5,15,4,0,8},
                {9,4,3,3,3,2,2,4,0,0,11,4,0,2,2,8,1,9,4,2,2,2,2,2,2,4,15,4,15,4,0,8},
                {9,4,2,2,2,5,3,11,4,0,15,4,11,5,3,8,1,9,4,3,3,3,3,3,3,10,9,4,15,2,4,8},
                {9,4,3,3,3,6,0,15,4,0,15,2,15,4,0,15,2,2,11,2,2,4,10,12,4,2,2,4,15,5,6,8},
                {9,2,11,2,2,2,2,2,4,0,15,5,15,4,0,15,5,3,15,5,3,6,15,2,4,3,3,6,15,4,10,13},
                {9,5,2,5,3,3,3,3,6,0,15,4,2,4,0,15,4,10,9,2,11,4,15,5,2,10,12,4,15,4,8,1},
                {9,4,3,6,2,2,2,2,2,11,2,4,3,6,0,15,4,8,9,5,15,4,15,4,3,8,9,4,15,4,8,1},
                {9,4,2,2,5,3,3,3,3,2,5,6,2,2,2,15,4,2,2,4,15,4,8,12,4,2,2,4,15,4,8,1},
                {9,4,3,3,6,0,0,0,0,3,6,0,3,3,3,15,4,3,3,6,15,4,8,9,4,3,3,6,15,4,8,1},
                {9,2,2,2,11,2,2,2,11,4,10,7,7,12,4,8,7,12,4,10,9,4,8,14,12,2,2,4,2,4,8,1},
                {9,5,3,3,15,5,3,3,15,4,2,2,2,2,4,2,2,2,4,8,9,4,2,2,2,5,3,6,3,6,8,1},
                {9,4,11,4,15,4,11,4,15,4,3,3,3,3,6,3,3,3,6,8,9,4,3,3,3,10,12,4,11,4,8,1},
                {9,4,15,4,8,12,2,4,15,4,11,2,2,2,2,11,4,10,7,13,9,4,2,2,2,2,15,4,15,4,8,1},
                {9,4,15,4,2,2,5,6,15,4,15,5,3,3,3,15,4,8,9,2,2,2,5,3,3,3,15,4,15,4,2,8},
                {9,4,15,4,3,3,6,2,2,4,15,4,10,12,4,2,4,8,9,5,3,3,11,2,2,4,15,4,8,12,5,8},
                {9,4,8,7,7,7,12,5,3,6,15,4,8,9,4,3,6,8,9,4,11,4,15,5,3,6,15,4,15,2,4,8},
                {9,4,2,2,2,2,2,4,11,4,15,4,2,2,2,2,2,2,2,4,15,4,15,4,10,7,9,4,15,5,6,8},
                {9,4,3,3,3,3,3,6,15,4,15,4,3,3,3,3,3,3,3,6,15,4,15,4,2,2,15,4,15,4,0,8},
                {14,12,4,10,7,7,7,7,9,4,2,2,4,2,2,11,2,2,2,4,15,4,15,4,3,3,15,4,15,4,0,8},
                {9,2,2,2,2,2,2,2,2,4,3,3,6,3,3,2,5,3,3,6,15,4,2,4,2,2,2,2,2,2,4,8},
                {9,5,3,3,3,3,3,3,3,6,10,7,7,12,4,3,6,10,12,4,15,4,3,6,3,3,3,3,3,3,6,8},
                {14,7,7,7,7,7,7,7,7,7,13,1,1,14,7,7,7,13,14,7,13,7,7,7,7,7,7,7,7,7,7,13}
        };

        int[][] maplvl3_correct = {{9,2,2,2,2,2,15,2,2,8,9,4,8,9,2,2,2,2,2,8,1,9,2,2,2,2,15,2,15,2,2,8},
                {9,5,3,3,3,3,2,5,3,2,2,4,2,2,5,3,3,3,3,2,2,2,5,3,3,3,15,5,15,5,3,8},
                {9,4,2,2,2,4,3,6,0,3,3,6,3,3,6,10,7,12,4,3,3,3,6,10,12,4,15,4,15,4,0,8},
                {9,4,3,3,3,2,2,4,0,0,11,4,0,2,2,8,1,9,4,2,2,2,2,2,2,4,15,4,15,4,0,8},
                {9,4,2,2,2,5,3,11,4,0,15,4,11,5,3,8,1,9,4,3,3,3,3,3,3,10,9,4,15,2,4,8},
                {9,4,3,3,3,6,0,15,4,0,15,4,15,4,0,15,2,2,11,2,2,4,10,12,4,2,2,4,15,5,6,8},
                {9,2,11,2,2,2,2,2,4,0,15,4,15,4,0,15,5,3,15,5,3,6,15,2,4,3,3,6,15,4,10,13},
                {9,5,2,5,3,3,3,3,6,0,15,4,2,4,0,15,4,10,9,2,11,4,15,5,2,10,12,4,15,4,8,1},
                {9,4,3,6,2,2,2,2,2,11,2,4,3,6,0,15,4,8,9,5,15,4,15,4,3,8,9,4,15,4,8,1},
                {9,4,2,2,5,3,3,3,3,2,5,6,2,2,2,15,4,2,2,4,15,4,8,12,4,2,2,4,15,4,8,1},
                {9,4,3,3,6,0,0,0,0,3,6,0,3,3,3,15,4,3,3,6,15,4,8,9,4,3,3,6,15,4,8,1},
                {9,2,2,2,11,2,2,2,11,4,10,7,7,12,4,8,7,12,4,10,9,4,8,14,12,2,2,4,2,4,8,1},
                {9,5,3,3,15,5,3,3,15,4,2,2,2,2,4,2,2,2,4,8,9,4,2,2,2,5,3,6,3,6,8,1},
                {9,4,11,4,15,4,11,4,15,4,3,3,3,3,6,3,3,3,6,8,9,4,3,3,3,10,12,4,11,4,8,1},
                {9,4,15,4,8,12,2,4,15,4,11,2,2,2,2,11,4,10,7,13,9,4,2,2,2,2,15,4,15,4,8,1},
                {9,4,15,4,2,2,5,6,15,4,15,5,3,3,3,15,4,8,9,2,2,2,5,3,3,3,15,4,15,4,2,8},
                {9,4,15,4,3,3,6,2,2,4,15,4,10,12,4,2,4,8,9,5,3,3,11,2,2,4,15,4,15,4,3,8},
                {9,4,8,7,7,7,12,5,3,6,15,4,8,9,4,3,6,8,9,4,11,4,15,5,3,6,15,4,15,2,4,8},
                {9,4,2,2,2,2,2,4,11,4,15,4,2,2,2,2,2,2,2,4,15,4,15,4,10,7,9,4,15,5,6,8},
                {9,4,3,3,3,3,3,6,15,4,15,4,3,3,3,3,3,3,3,6,15,4,15,4,2,2,15,4,15,4,0,8},
                {14,12,4,10,7,7,7,7,9,4,8,12,4,2,2,11,2,2,2,4,15,4,15,4,3,3,15,4,15,4,0,8},
                {1,9,4,2,2,2,2,2,2,4,8,9,4,3,3,2,5,3,3,6,15,4,2,4,2,4,15,2,2,2,4,8},
                {1,9,4,3,3,3,3,3,3,6,8,14,7,12,4,3,6,10,12,4,15,4,3,6,3,6,15,5,3,3,6,8},
                {1,14,7,7,7,7,7,7,7,7,13,1,1,14,7,7,7,13,14,7,13,7,7,7,7,7,13,7,7,7,7,13}
        };

        Assertions.assertEquals(Arrays.deepToString(maplvl1_correct),Arrays.deepToString(gmTest.getBoard(0)));
        Assertions.assertEquals(Arrays.deepToString(maplvl2_correct),Arrays.deepToString(gmTest.getBoard(1)));
        Assertions.assertEquals(Arrays.deepToString(maplvl3_correct),Arrays.deepToString(gmTest.getBoard(2)));
        Assertions.assertEquals(null,gmTest.getOriginMap(3));
    }

    /**
     * Test for correct board map return for game start up depending on level
     */
    @Test
    public void getOriginMapTest(){
        int[][] maplvl1 = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,0,0,1} ,
                {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
                {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

        int[][] maplvl2 = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1} ,
                {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
                {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

        int maplvl3[][] = {
                {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,0,0,1} ,
                {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,1,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
                {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
                {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1},
                {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
                {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
                {1,1,0,1,1,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1},
                {1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

        Assertions.assertEquals(Arrays.deepToString(gmTest.getOriginMap(0)),Arrays.deepToString(maplvl1));
        Assertions.assertEquals(Arrays.deepToString(gmTest.getOriginMap(1)),Arrays.deepToString(maplvl2));
        Assertions.assertEquals(Arrays.deepToString(gmTest.getOriginMap(2)),Arrays.deepToString(maplvl3));
        Assertions.assertEquals(gmTest.getOriginMap(3),null);
    }

    /**
     * Test for correct player starting point depending on game level
     */
    @Test
    public void getStartPoints(){
        int[] startPoints = new int[2];
        startPoints[0] = 365;
        startPoints[1] = 0;

        Assertions.assertEquals(Arrays.toString(gmTest.getStartPoints(0)),Arrays.toString(startPoints));
        startPoints[0] = 720;
        startPoints[1] = 30;
        Assertions.assertEquals(Arrays.toString(gmTest.getStartPoints(1)),Arrays.toString(startPoints));
        startPoints[0] = 265;
        startPoints[1] = 0;
        Assertions.assertEquals(Arrays.toString(gmTest.getStartPoints(2)),Arrays.toString(startPoints));
        startPoints[0] = -1;
        startPoints[1] = -1;
        Assertions.assertEquals(Arrays.toString(gmTest.getStartPoints(3)),Arrays.toString(startPoints));

    }

    /**
     * Test for collect image import
     */
    @Test
    public void getTileImgTest() throws IOException {
        String[] Img = null;
        gmTest.getTileImg();
        BufferedImage img = ImageIO.read(new File("src/main/java/picture/Tiles/earth.png"));
        Img = (gmTest.tile[0].tileImg).toString().split(" ");
        Assertions.assertTrue(gmTest.tile[1].getCollision());
        Assertions.assertFalse(gmTest.tile[0].getCollision());
        for(int i= 1; i < Img.length; i++)
            Assertions.assertEquals(Img[i],img.toString().split(" ")[i]);
    }
}
