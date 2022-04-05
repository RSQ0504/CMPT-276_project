import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // commandNum: 0 -> 1
        testGameFrame.commandNum = 0;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(1, testGameFrame.commandNum);


        // commandNum: 2 -> 0
        testGameFrame.commandNum = 2;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: -2 -> 2
        testGameFrame.commandNum = -2;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.commandNum);

        // commandNum: 5 -> 0
        testGameFrame.commandNum = 5;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
    }
    @Test
    public void enterPressedInChangeLevelState() throws IOException {
        System.out.println("[enterPressedInChangeLevelState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // ENTER pressed with commandNum: 0 gameLevel: 0 -> 0
        testGameFrame.commandNum = 0;
        testGameFrame.gameState = testGameFrame.changeLevelState;
        testGameFrame.gameLevel = testGameFrame.levelEasy;
        testGameFrame.numOfVaccines = 5;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);
        Assertions.assertEquals(testGameFrame.levelEasy, testGameFrame.gameLevel);
        Assertions.assertEquals(5, testGameFrame.numOfVaccines);

        // ENTER pressed with commandNum: 0 gameLevel: 1 -> 0
        testGameFrame.commandNum = 0;
        testGameFrame.gameState = testGameFrame.changeLevelState;
        testGameFrame.gameLevel = testGameFrame.levelIntermediate;
        testGameFrame.numOfVaccines = 7;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);
        Assertions.assertEquals(testGameFrame.levelEasy, testGameFrame.gameLevel);
        Assertions.assertEquals(5, testGameFrame.numOfVaccines);


        // ENTER pressed with commandNum: 1 gameLevel: 2 -> 1
        testGameFrame.commandNum = 1;
        testGameFrame.gameState = testGameFrame.changeLevelState;
        testGameFrame.gameLevel = testGameFrame.levelChallenge;
        testGameFrame.numOfVaccines = 10;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);
        Assertions.assertEquals(testGameFrame.levelIntermediate, testGameFrame.gameLevel);
        Assertions.assertEquals(7, testGameFrame.numOfVaccines);

        // ENTER pressed with commandNum: 2 gameLevel: 0 -> 2
        testGameFrame.commandNum = 2;
        testGameFrame.gameState = testGameFrame.changeLevelState;
        testGameFrame.gameLevel = testGameFrame.levelEasy;
        testGameFrame.numOfVaccines = 5;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);
        Assertions.assertEquals(testGameFrame.levelChallenge, testGameFrame.gameLevel);
        Assertions.assertEquals(10, testGameFrame.numOfVaccines);

        // ENTER pressed with invalid commandNum: other than 0, 1, or 2 and invalid game configuration
        testGameFrame.commandNum = 5;
        testGameFrame.gameState = testGameFrame.changeLevelState;
        testGameFrame.gameLevel = -1;
        testGameFrame.numOfVaccines = -1;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.changeLevelState, testGameFrame.gameState);
        Assertions.assertEquals(testGameFrame.levelEasy, testGameFrame.gameLevel);
        Assertions.assertEquals(5, testGameFrame.numOfVaccines);
    }


    // 3. playState (UP, DOWN, LEFT, RIGHT, F, ESCAPE) *escape not used
    @Test
    public void upPressedInPlayState() throws IOException {
        System.out.println("[upPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.playState;

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

        // [keyPressed()] pressedUp: false -> true
        testKey.pressedUp = false;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(true, testKey.pressedUp);

        // [keyPressed()] pressedUp: true -> true (exception)
        testKey.pressedUp = true;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(true, testKey.pressedUp);

        // [keyReleased()] pressedUp: true -> false
        testKey.pressedUp = true;
        testKey.keyReleased(upKey);
        Assertions.assertEquals(false, testKey.pressedUp);

        // [keyReleased()] pressedUp: false -> false (exception)
        testKey.pressedUp = false;
        testKey.keyReleased(upKey);
        Assertions.assertEquals(false, testKey.pressedUp);
    }
    @Test
    public void downPressedInPlayState() throws IOException {
        System.out.println("[downPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.playState;

        // [key pressed] DOWN:
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // [keyPressed()] pressedDown: false -> true
        testKey.pressedDown = false;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(true, testKey.pressedDown);

        // [keyPressed()] pressedDown: true -> true (exception)
        testKey.pressedDown = true;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(true, testKey.pressedDown);

        // [keyReleased()] pressedDown: true -> false
        testKey.pressedDown = true;
        testKey.keyReleased(downKey);
        Assertions.assertEquals(false, testKey.pressedDown);

        // [keyReleased()] pressedDown: false -> false (exception)
        testKey.pressedDown = false;
        testKey.keyReleased(downKey);
        Assertions.assertEquals(false, testKey.pressedDown);
    }
    @Test
    public void leftPressedInPlayState() throws IOException {
        System.out.println("[leftPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.playState;

        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // [keyPressed()] pressedLeft: false -> true
        testKey.pressedDown = false;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(true, testKey.pressedLeft);

        // [keyPressed()] pressedLeft: true -> true (exception)
        testKey.pressedDown = true;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(true, testKey.pressedLeft);

        // [keyReleased()] pressedLeft: true -> false
        testKey.pressedDown = true;
        testKey.keyReleased(leftKey);
        Assertions.assertEquals(false, testKey.pressedLeft);

        // [keyReleased()] pressedLeft: false -> false (exception)
        testKey.pressedDown = false;
        testKey.keyReleased(leftKey);
        Assertions.assertEquals(false, testKey.pressedLeft);
    }
    @Test
    public void rightPressedInPlayState() throws IOException {
        System.out.println("[rightPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.playState;

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // [keyPressed()] pressedRight: false -> true
        testKey.pressedRight = false;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(true, testKey.pressedRight);

        // [keyPressed()] pressedRight: true -> true (exception)
        testKey.pressedRight = true;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(true, testKey.pressedRight);

        // [keyReleased()] pressedRight: true -> false
        testKey.pressedRight= true;
        testKey.keyReleased(rightKey);
        Assertions.assertEquals(false, testKey.pressedRight);

        // [keyReleased()] pressedRight: false -> false (exception)
        testKey.pressedRight= false;
        testKey.keyReleased(rightKey);
        Assertions.assertEquals(false, testKey.pressedRight);
    }
    @Test
    public void fPressedInPlayState() throws IOException {
        System.out.println("[fPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.playState;

        // [key pressed] F:
        KeyEvent fKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_F,'Z');

        // [keyPressed()] pressF: false -> true
        testKey.pressF = false;
        testKey.keyPressed(fKey);
        Assertions.assertEquals(true, testKey.pressF);

        // [keyPressed()] pressF: true -> true (exception)
        testKey.pressF = true;
        testKey.keyPressed(fKey);
        Assertions.assertEquals(true, testKey.pressF);

        // [keyReleased()] pressF: true -> false
        testKey.pressF = true;
        testKey.keyReleased(fKey);
        Assertions.assertEquals(false, testKey.pressF);

        // [keyReleased()] pressF: false -> false (exception)
        testKey.pressF = false;
        testKey.keyReleased(fKey);
        Assertions.assertEquals(false, testKey.pressF);
    }

    // 4. tutorialState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInTutorialState() throws IOException {
        System.out.println("[leftPressedInTutorialState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);


        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // tutorialState: 5 -> 5 (no change)
        testGameFrame.tutorialState = testGameFrame.tutorialIntro;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);

        // tutorialState: 6 -> 5
        testGameFrame.tutorialState = testGameFrame.tutorial1;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);

        // tutorialState: 10 -> 5 (exception, reset tutorialState to 5)
        testGameFrame.tutorialState = 10;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);

        // tutorialState: 3 -> 5 (exception, reset tutorialState to 5)
        testGameFrame.tutorialState = 3;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);

        // tutorialState: 23 -> 5 (exception)
        testGameFrame.tutorialState = 23;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);

        // tutorialState: -1 -> 5 (exception)
        testGameFrame.tutorialState = -1;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);
    }
    @Test
    public void rightPressedInTutorialState() throws IOException {
        System.out.println("[rightPressedInTutorialState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.tutorialState;

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // tutorialState: 5 -> 6
        testGameFrame.tutorialState = testGameFrame.tutorialIntro;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.tutorial1, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorial1, testGameFrame.gameState);

        // tutorialState: 8 -> 9 (gameState transfers to narration state)
        testGameFrame.tutorialState = testGameFrame.tutorial3;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // tutorialState: 10 -> 8 (exception)
        testGameFrame.tutorialState = 10;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.gameState);

        // tutorialState: 3 -> 8 (exception)
        testGameFrame.tutorialState = 3;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.gameState);

        // tutorialState: 23 -> 8 (exception)
        testGameFrame.tutorialState = 23;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.gameState);

        // tutorialState: -1 -> 8 (exception)
        testGameFrame.tutorialState = -1;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorial3, testGameFrame.gameState);
    }
    @Test
    public void enterPressedInTutorialState() throws IOException {
        System.out.println("[enterPressedInTutorialState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.tutorialState;

        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // tutorialState: 5 -> 9
        testGameFrame.tutorialState = testGameFrame.tutorialIntro;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // tutorialState: 8 -> 9
        testGameFrame.tutorialState = testGameFrame.tutorial3;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // tutorialState: 23 -> 5 (exception)
        testGameFrame.tutorialState = 23;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);

        // tutorialState: -1 -> 5 (exception)
        testGameFrame.tutorialState = -1;
        testGameFrame.gameState = testGameFrame.tutorialState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.tutorialState);
        Assertions.assertEquals(testGameFrame.tutorialIntro, testGameFrame.gameState);
    }


    // 5. narrationState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInNarrationState() throws IOException {
        System.out.println("[leftPressedInNarrationState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.narrationState;

        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // narrationState: 9 -> 9
        testGameFrame.narrationState = testGameFrame.narration1;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // narrationState: 10 -> 9
        testGameFrame.narrationState = testGameFrame.narration2;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // narrationState: 23 -> 9 (exception)
        testGameFrame.narrationState = 23;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // narrationState: -1 -> 9 (exception)
        testGameFrame.narrationState = -1;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);
    }
    @Test
    public void rightPressedInNarrationState() throws IOException {
        System.out.println("[rightPressedInNarrationState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.narrationState;

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // narrationState: 9 -> 10
        testGameFrame.narrationState = testGameFrame.narration1;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.narration2, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration2, testGameFrame.gameState);

        // narrationState: 19 -> 19
        testGameFrame.narrationState = testGameFrame.narration11;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.narration11, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration11, testGameFrame.gameState);

        // narrationState: 23 -> 19 (exception)
        testGameFrame.narrationState = 23;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.narration11, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration11, testGameFrame.gameState);

        // narrationState: -1 -> 19 (exception)
        testGameFrame.narrationState = -1;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(testGameFrame.narration11, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration11, testGameFrame.gameState);
    }
    @Test
    public void enterPressedInNarrationState() throws IOException {
        System.out.println("[enterPressedInNarrationState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.numOfCommands - 1;
        testGameFrame.gameState = testGameFrame.narrationState;

        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // narrationState: 9 -> play
        testGameFrame.narrationState = testGameFrame.narration1;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.playState, testGameFrame.gameState);

        // narrationState: 19 -> play
        testGameFrame.narrationState = testGameFrame.narration11;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.playState, testGameFrame.gameState);

        // narrationState: 15 -> play
        testGameFrame.narrationState = testGameFrame.narration7;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.playState, testGameFrame.gameState);

        // narrationState: 23 -> 9 (exception)
        testGameFrame.narrationState = 23;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);

        // narrationState: -1 -> 9 (exception)
        testGameFrame.narrationState = -1;
        testGameFrame.gameState = testGameFrame.narrationState;
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.narrationState);
        Assertions.assertEquals(testGameFrame.narration1, testGameFrame.gameState);
    }


    // 6. endState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInEndState() throws IOException {
        System.out.println("[leftPressedInEndState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.endState;

        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // commandNum: 0 -> 1
        testGameFrame.commandNum = 0;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(1, testGameFrame.commandNum);

        // commandNum: 1 -> 0
        testGameFrame.commandNum = 1;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: 7 -> 0 (exception)
        testGameFrame.commandNum = 7;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
    }
    @Test
    public void rightPressedInEndState() throws IOException {
        System.out.println("[rightPressedInEndState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.gameState = testGameFrame.endState;

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // commandNum: 0 -> 1
        testGameFrame.commandNum = 0;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(1, testGameFrame.commandNum);

        // commandNum: 1 -> 0
        testGameFrame.commandNum = 1;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);

        // commandNum: 7 -> 0 (exception)
        testGameFrame.commandNum = 7;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(0, testGameFrame.commandNum);
    }
    @Test
    public void enterPressedInEndState() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("[enterPressedInEndState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);


        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // [retry: return to tutorial screen, restart game with same level]
        // commandNum: 0
        // gameLevel: intermediate
        // numOfVaccines: 7
        // gameResult: fail
        testGameFrame.gameState = testGameFrame.endState;
        testGameFrame.commandNum = 0;
        testGameFrame.gameLevel = testGameFrame.levelIntermediate;
        testGameFrame.numOfVaccines = 7;
        testGameFrame.gameResult = testGameFrame.fail;
        testGameFrame.playBGM(testGameFrame.track3_playState);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.tutorialState, testGameFrame.gameState);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(7, testGameFrame.numOfVaccines);
        Assertions.assertEquals(testGameFrame.levelIntermediate, testGameFrame.gameLevel);
        Assertions.assertEquals(-1, testGameFrame.musicTrack);

        // [return to title screen]
        // commandNum: 1
        // gameLevel: challenge
        // numOfVaccines: 10
        // gameResult: win
        testGameFrame.gameState = testGameFrame.endState;
        testGameFrame.commandNum = 1;
        testGameFrame.gameLevel = testGameFrame.levelChallenge;
        testGameFrame.numOfVaccines = 10;
        testGameFrame.gameResult = testGameFrame.win;
        testGameFrame.playBGM(testGameFrame.track3_playState);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.titleState, testGameFrame.gameState);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(5, testGameFrame.numOfVaccines);
        Assertions.assertEquals(testGameFrame.levelEasy, testGameFrame.gameLevel);
        Assertions.assertEquals(-1, testGameFrame.musicTrack);

        // enter pressed with commandNum: 5 (exception)
        testGameFrame.gameState = testGameFrame.endState;
        testGameFrame.commandNum = 5;
        testGameFrame.gameLevel = 6;
        testGameFrame.numOfVaccines = 34;
        testGameFrame.gameResult = -1;
        testGameFrame.playBGM(testGameFrame.track3_playState);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.endState, testGameFrame.gameState);
        Assertions.assertEquals(0, testGameFrame.commandNum);
        Assertions.assertEquals(testGameFrame.track3_playState, testGameFrame.musicTrack);
    }




}
