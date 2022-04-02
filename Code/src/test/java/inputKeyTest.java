import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.IOException;


public class inputKeyTest {
    @Test
    public void checkInputKeyConstructor() throws IOException{
        int colm = 16;
        int rows = 12;
        int pacSize = 48; //industry standard 48x48
        GameFrame gameFrameTest = new GameFrame(colm, rows, pacSize);

        inputKey key = null;

        if(gameFrameTest != null) {
            key = new inputKey(gameFrameTest);
            System.out.println("[checkInputKeyConstructor] inputKey object created");
            Assertions.assertNotNull(key);
            System.out.println("[checkInputKeyConstructor] inputKey object not null");

            // test keyPressed

            // test keyReleased

            System.out.println("[checkInputKeyConstructor] All done.");
        }else {
            System.out.println("[checkInputKeyConstructor] Object creation prior to assertion failed");
        }
    }

    @Test
    public void checkTileTest() throws IOException {
//        int colm = 16;
//        int rows = 12;
//        int pacSize = 48; //industry standard 48x48
//
//        //Make GameFrame class
//        GameFrame testGF = new GameFrame(colm, rows, pacSize);
//
//        //Make Dynamic Character
//        DynamicCharacter dcTest = new DynamicCharacter();
//
//        //Make checkCollision class and send GameFrame class in
//        checkCollision ccTest = new checkCollision(testGF);
//
//        //hit area
//        dcTest.hitArea = new Rectangle( 0,0,24,  24);
//
//        //Corner tests
//        //0,0
//        ccTest.checkTile(dcTest);
//        Assertions.assertTrue(dcTest.collisionArea);
//        System.out.print(dcTest.speed);
        return;

    }

}
