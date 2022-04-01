import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.IOException;


public class checkCollisionTest {




    //set objects position in
        //corner
        //in walls
        //outside walls
        //Each side touching wall
    //Call checkTile Function and send Dynamic character in after setting character's position
    //speed = 0
    @Test
    public void checkTileTest() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48; //industry standard 48x48

        //Make GameFrame class
        GameFrame testGF = new GameFrame(colm, rows, pacSize);

        //Make Dynamic Character
        DynamicCharacter dcTest = new DynamicCharacter();

        //Make checkCollision class and send GameFrame class in
        checkCollision ccTest = new checkCollision(testGF);

        //hit area
        dcTest.hitArea = new Rectangle( 0,0,24,  24);

        //Corner tests
        //0,0
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        System.out.print(dcTest.speed);

        //48,48
        dcTest.x = 768/2;
        dcTest.y = 576/2;
        dcTest.direction = "up";
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        System.out.print(dcTest.x);

        //0,48
        dcTest.x = 0;
        dcTest.y = 576;
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        System.out.print(dcTest.x);

        //48,0
        dcTest.x = 768;
        dcTest.y = 0;
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        System.out.print(dcTest.x);




    }

}
