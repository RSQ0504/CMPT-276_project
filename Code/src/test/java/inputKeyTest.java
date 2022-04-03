import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/*
>> keyPressed
1. titleState (UP, DOWN, ENTER)
2. changeLevelState (UP, DOWN, ENTER)
3. playState (UP, DOWN, LEFT, RIGHT, F, ESCAPE) *escape is not used in system (should not be used)
4. tutorialState (LEFT, RIGHT, ENTER)
5. narrationState (LEFT, RIGHT, ENTER)
6. endState (LEFT, RIGHT, ENTER)

>> keyReleased (mainly for playState)
UP, DOWN, LEFT, RIGHT, F
 */

public class inputKeyTest {
    // 1. titleState (UP, DOWN, ENTER)
    @Test
    public void checkKeyInputsInTitleState() throws IOException, AWTException {
        System.out.println("[checkKeyInputsInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

        // when key input causes commandNum to be negative (less than zero)
        // commandNum: 0 -> 2
        testGameFrame.commandNum = 0;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.commandNum);

        // commandNum: 1 -> 0
        testGameFrame.commandNum = 1;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: 2 -> 1
        testGameFrame.commandNum = 2;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(1, testGameFrame.commandNum);

        // commandNum: -2 -> 2 (exception)
        testGameFrame.commandNum = -2;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(2, testGameFrame.commandNum);

        // commandNum: 5 -> 0 (exception)
        testGameFrame.commandNum = 5;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);


        // [key pressed] DOWN:
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');





        // [key pressed] ENTER
    }

    // 2. changeLevelState (UP, DOWN, ENTER)
    @Test
    public void checkKeyInputsInChangeLevelState() {
        // [key pressed] UP
        // [key pressed] DOWN
        // [key pressed] ENTER
    }

    // 3. playState (UP, DOWN, LEFT, RIGHT, F, ESCAPE) *escape not used
    @Test
    public void checkKeyInputsInPlayState() {
        // [key pressed] UP
        // [key pressed] DOWN
        // [key pressed] LEFT
        // [key pressed] RIGHT
        // [key pressed] F
    }

    // 4. tutorialState (LEFT, RIGHT, ENTER)
    @Test
    public void checkKeyInputsInTutorialState() {
        // [key pressed] LEFT
        // [key pressed] RIGHT
        // [key pressed] ENTER
    }

    // 5. narrationState (LEFT, RIGHT, ENTER)
    @Test
    public void checkKeyInputsInNarrationState() {
        // [key pressed] LEFT
        // [key pressed] RIGHT
        // [key pressed] ENTER
    }

    // 6. endState (LEFT, RIGHT, ENTER)
    @Test
    public void checkKeyInputsInEndState() {
        // [key pressed] LEFT
        // [key pressed] RIGHT
        // [key pressed] ENTER
    }


    @Test
    public void checkPressedUp() throws AWTException, IOException {
        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        System.out.println("[checkPressedUp/inputKeyTest]");
        Robot keyboardUser = new Robot();
        keyboardUser.keyPress(KeyEvent.VK_UP);
        keyboardUser.keyRelease(KeyEvent.VK_UP);

        // [gamestate: ]
//        System.out.println("[testGameFrame] var: ", testGameFrame.);

        // assertion
//        KeyEvent key = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
//        instance.getKeyListeners()[0].keyPressed(key);
    }

    @Test
    public void checkPressedDown() {}

    @Test
    public void checkPressedLeft() {}

    @Test
    public void checkPressedRight() {}


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
