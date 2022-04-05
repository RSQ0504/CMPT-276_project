import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.IOException;


public class checkCollisionTest {
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
        dcTest.collisionArea = false;
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);

        //48,48
        dcTest.x = 768/2;
        dcTest.y = 576/2;
        dcTest.collisionArea = false;
        dcTest.direction = "up";
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        

        //0,48
        dcTest.x = 0;
        dcTest.y = 576/2;
        dcTest.collisionArea = false;
        dcTest.direction = "down";
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        

        //48,0
        dcTest.x = 768/2;
        dcTest.y = 0;
        dcTest.direction = "left";
        dcTest.collisionArea = false;
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collisionArea);
        

        //Outside walls
        dcTest.x = 144;
        dcTest.y = 24;
        dcTest.collisionArea = false;
        dcTest.direction = "right";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collisionArea);
        

        //Top touching wall
        dcTest.x = 48;
        dcTest.y = 24;
        dcTest.collisionArea = false;
        dcTest.direction = "up";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collisionArea);
        

        //Bottom touching wall
        dcTest.x = 48;
        dcTest.y = 276;
        dcTest.collisionArea = false;
        dcTest.direction = "down";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collisionArea);
        

        //Right touching wall
        dcTest.x = 168;
        dcTest.y = 518;
        dcTest.collisionArea = false;
        dcTest.direction = "right";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collisionArea);

        //left touching wall
        dcTest.x = 238;
        dcTest.y = 518;
        dcTest.collisionArea = false;
        dcTest.direction = "left";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collisionArea);
    }

}
