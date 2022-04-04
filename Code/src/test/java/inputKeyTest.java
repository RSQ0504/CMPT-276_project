import org.junit.Assert;
import org.junit.Rule;
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
//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    // 1. titleState (UP, DOWN, ENTER)
    @Test
    public void upPressedInTitleState() throws IOException, AWTException {
        System.out.println("[upPressedInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

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
    }

    @Test
    public void downPressedInTitleState() throws IOException, AWTException {
        System.out.println("[downPressedInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;

        // [key pressed] DOWN:
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // commandNum: 0 -> 1
        testGameFrame.commandNum = 0;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(1, testGameFrame.commandNum);

        // commandNum: 1 -> 2
        testGameFrame.commandNum = 1;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.commandNum);

        // commandNum: 2 -> 0
        testGameFrame.commandNum = 2;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: -2 -> 2 (exception)
        testGameFrame.commandNum = -2;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(2, testGameFrame.commandNum);

        // commandNum: 5 -> 0 (exception)
        testGameFrame.commandNum = 5;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
    }

    @Test
    public void enterPressedInTitleState() throws IOException, AWTException {
        System.out.println("[enterPressedInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;

        // [key pressed] ENTER
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // enter key pressed with commandNum: 0 (title -> tutorial)
        testGameFrame.gameState = testGameFrame.titleState;
        testGameFrame.commandNum = 0;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.tutorialState, testGameFrame.gameState);

        // enter key pressed with commandNum: 1 (title -> change level)
        testGameFrame.gameState = testGameFrame.titleState;
        testGameFrame.commandNum = 1;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.gameLevel, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.changeLevelState, testGameFrame.gameState);

        // enter key pressed with commandNum: 2 (title -> exit)
//        testGameFrame.gameState = testGameFrame.titleState;
//        testGameFrame.commandNum = 2;
//        testKey.keyPressed(enterKey);

        // enter key pressed with commandNum: -3 (exception)
        testGameFrame.gameState = testGameFrame.titleState;
        testGameFrame.commandNum = -3;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);

        // enter key pressed with commandNum: 5 (exception)
        testGameFrame.gameState = testGameFrame.titleState;
        testGameFrame.commandNum = 5;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);
    }



    // 2. changeLevelState (UP, DOWN, ENTER)
    @Test
    public void upPressedInChangeLevelState() throws IOException {
        System.out.println("[upPressedInChangeLevelState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.changeLevelState;

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

        // commandNum: 0 -> 2
        testGameFrame.commandNum = 0;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.commandNum);


        // commandNum: 1 -> 0
        testGameFrame.commandNum = 1;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: -2 -> 2
        testGameFrame.commandNum = -2;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.commandNum);

        // commandNum: 5 -> 0
        testGameFrame.commandNum = 5;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

    }
    @Test
    public void downPressedInChangeLevelState() throws IOException {
        System.out.println("[downPressedInChangeLevelState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.changeLevelState;

        // [key pressed] DOWN:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // commandNum: 0 -> 1
        testGameFrame.commandNum = 0;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(1, testGameFrame.commandNum);


        // commandNum: 2 -> 0
        testGameFrame.commandNum = 2;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: -2 -> 2
        testGameFrame.commandNum = -2;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.commandNum);

        // commandNum: 5 -> 0
        testGameFrame.commandNum = 5;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
    }
    @Test
    public void enterPressedInChangeLevelState() {}


    // 3. playState (UP, DOWN, LEFT, RIGHT, F, ESCAPE) *escape not used
    @Test
    public void upPressedInPlayState() {}
    @Test
    public void downPressedInPlayState() {}
    @Test
    public void leftPressedInPlayState() {}
    @Test
    public void rightPressedInPlayState() {}
    @Test
    public void fPressedInPlayState() {}

    // 4. tutorialState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInTutorialState() {}
    @Test
    public void rightPressedInTutorialState() {}
    @Test
    public void enterPressedInTutorialState() {}


    // 5. narrationState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInNarrationState() {}
    @Test
    public void rightPressedInNarrationState() {}
    @Test
    public void enterPressedInNarrationState() {}


    // 6. endState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInEndState() {}
    @Test
    public void rightPressedInEndState() {}
    @Test
    public void enterPressedInEndState() {}



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
