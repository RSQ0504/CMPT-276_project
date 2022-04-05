import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * class to handle user keyboard inputs
 */
public class inputKey implements KeyListener {
    GameFrame gf;
    public boolean pressedUp, pressedDown, pressedLeft, pressedRight;
    public boolean pressF;

    /**
     * class constructor
     * @param gf
     */
    public inputKey(GameFrame gf) {
        this.gf = gf;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Dont use this
    }

    /**
     * handle keyboard inputs
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        try {
            if(gf.gameState == gf.titleState) { // key input in title screen to move cursor

                if(key == KeyEvent.VK_UP) {
//                System.out.println("UP pressed in title screen");
                    gf.playSoundEffect(0);

                    gf.commandNum--;
//                    System.out.println("commandNum = " + gf.commandNum);
                    if(gf.commandNum < 0) {
                        gf.commandNum = gf.numOfCommands - 1;
//                    System.out.println("[change] commandNum = " + gf.commandNum);
                    }else if(gf.commandNum > gf.numOfCommands - 1) {
                        gf.commandNum = 0;
                    }
                }else if(key == KeyEvent.VK_DOWN) {
                    System.out.println("DOWN pressed in title screen");
                    gf.playSoundEffect(0);

//                    System.out.println("commandNum = " + gf.commandNum);
                    gf.commandNum++;
                    if(gf.commandNum > gf.numOfCommands - 1) {
                        gf.commandNum = 0;
//                        System.out.println("[change] commandNum = " + gf.commandNum);
                    }else if(gf.commandNum < 0) {
                        // exception
                        gf.commandNum = gf.numOfCommands - 1;
                    }
                }else if(key == KeyEvent.VK_ENTER) {
                    System.out.println("enter pressed in title screen");
                    if(gf.commandNum == gf.optionStart) {
                        gf.gameState = gf.tutorialState;
                        gf.commandNum = 0;
                    }else if(gf.commandNum == gf.optionChangeLevel) {
                        gf.playSoundEffect(0);

                        gf.gameState = gf.changeLevelState;
                        gf.commandNum = gf.gameLevel;
                    }else if(gf.commandNum == gf.optionExit) {
                        gf.playSoundEffect(2);
                        System.exit(0);
                    }else {
                        // exception: reset commandNum to 0
                        gf.commandNum = 0;
                    }
                }
            }else if(gf.gameState == gf.changeLevelState) { // key input in change level screen to move cursor
                System.out.println("key pressed in change level screen");
                if(key == KeyEvent.VK_UP) {
                    gf.playSoundEffect(0);
                    gf.commandNum--;
                    if(gf.commandNum < 0) {
                        gf.commandNum = gf.numOfCommands - 1;
                    }else if(gf.commandNum > gf.numOfCommands - 1) {
                        gf.commandNum = 0;
                    }
                }else if(key == KeyEvent.VK_DOWN) {
                    gf.playSoundEffect(0);

                    gf.commandNum++;
                    if(gf.commandNum > gf.numOfCommands - 1) {
                        gf.commandNum = 0;
                    }else if(gf.commandNum < 0) {
                        gf.commandNum = gf.numOfCommands - 1;
                    }
                }else if(key == KeyEvent.VK_ENTER) {
                    gf.playSoundEffect(0);

                    System.out.println("enter pressed in change level screen");
                    if(gf.commandNum == gf.levelEasy) {
                        System.out.println("change to easy");
                        gf.numOfVaccines = 5;
                        gf.gameLevel = gf.levelEasy;
                        gf.mc.setDefaultValue(gf.tileFrame.getStartPoints(gf.levelEasy));
                        gf.gameState = gf.titleState;
                    }else if(gf.commandNum == gf.levelIntermediate) {
                        System.out.println("change to intermediate");
                        gf.numOfVaccines = 7;
                        gf.gameLevel = gf.levelIntermediate;
                        gf.mc.setDefaultValue(gf.tileFrame.getStartPoints(gf.levelIntermediate));
                        gf.gameState = gf.titleState;
                    }else if(gf.commandNum == gf.levelChallenge) {
                        System.out.println("change to challenge");
                        gf.numOfVaccines = 10;
                        gf.gameLevel = gf.levelChallenge;
                        gf.mc.setDefaultValue(gf.tileFrame.getStartPoints(gf.levelChallenge));
                        gf.gameState = gf.titleState;
                    }else {
                        System.out.println("invalid commandNum");
                        gf.gameLevel = gf.levelEasy;
                        gf.numOfVaccines = 5;
                        gf.gameState = gf.changeLevelState;
                    }
                    gf.commandNum = 0;
//                    gf.gameState = gf.titleState;
                }
            } else if(gf.gameState == gf.playState) { // key input during play state
                if(key== KeyEvent.VK_F){
                    pressF = true;
                }
                else if(key == KeyEvent.VK_UP){
                    pressedUp = true;
                }
                else if(key == KeyEvent.VK_DOWN){
                    pressedDown = true;
                }
                else if(key == KeyEvent.VK_LEFT){
                    pressedLeft = true;
                }
                else if(key == KeyEvent.VK_RIGHT){
                    pressedRight = true;
                }

            }else if(gf.gameState == gf.tutorialState) {
                if(key== KeyEvent.VK_LEFT){
                    if(gf.tutorialState > gf.tutorialIntro && gf.tutorialState <= gf.tutorial3) {
                        gf.tutorialState--;
                        gf.playSoundEffect(0);
                    }else {
                        // exception
                        gf.tutorialState = gf.tutorialIntro;
                    }

                    // update gameState to reflect tutorial state
                    gf.gameState = gf.tutorialState;
                }else if(key == KeyEvent.VK_RIGHT){
                    if(gf.tutorialState >= gf.tutorialIntro && gf.tutorialState <= gf.tutorial3) {
                        gf.tutorialState++;
                        gf.playSoundEffect(0);
                    }else {
                        // exception
                        gf.tutorialState = gf.tutorial3;
                    }

                    // check if gameState has transferred to narration state
                    if(gf.tutorialState == gf.narrationState) {
                        gf.tutorialState = gf.tutorialIntro;// reset
                        gf.gameState = gf.narrationState;
                    }else {
                        gf.gameState = gf.tutorialState;
                    }
                }else if(key == KeyEvent.VK_ENTER){
                    if(gf.tutorialState >= gf.tutorialIntro && gf.tutorialState <= gf.tutorial3) {
                        gf.playSoundEffect(0);
                        gf.tutorialState = gf.tutorialIntro;// reset
                        gf.gameState = gf.narrationState;
                    }else {
                        // exception
                        gf.tutorialState = gf.tutorialIntro;
                        gf.gameState = gf.tutorialState;
                    }

                }
            }else if(gf.gameState == gf.narrationState) {
                System.out.println("gameState: " + gf.gameState + " narrationState: " + gf.narrationState);
                if(key== KeyEvent.VK_LEFT){
                    if(gf.narrationState > gf.narration1 && gf.narrationState <= gf.narration11) {
                        gf.playSoundEffect(0);
                        gf.narrationState--;
                    }else {
                        // exception
                        gf.narrationState = gf.narration1;
                    }

                    gf.gameState = gf.narrationState;
                }else if(key == KeyEvent.VK_RIGHT){
                    if(gf.narrationState >= gf.narration1 && gf.narrationState < gf.narration11) {
                        gf.playSoundEffect(0);
                        gf.narrationState++;
                    }else {
                        // exception
                        gf.narrationState = gf.narration11;
                    }

                    gf.gameState = gf.narrationState;
                }else if(key == KeyEvent.VK_ENTER){
                    if(gf.narrationState >= gf.narration1 && gf.narrationState <= gf.narration11) {
                        gf.narrationState = gf.narration1;
                        gf.gameState = gf.playState;
                        gf.stopBGM();
                    }else {
                        // exception
                        gf.narrationState = gf.narration1;
                        gf.gameState = gf.narrationState;
                    }

                }
            }else if(gf.gameState == gf.endState) {

                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                    gf.playSoundEffect(0);

                    // switch options
                    if(gf.commandNum == 0) {
                        gf.commandNum = 1;
                    }else if(gf.commandNum == 1) {
                        gf.commandNum = 0;
                    }else {
                        // exception: reset commandNum to 0
                        gf.commandNum = 0;
                    }
                }else if(key == KeyEvent.VK_ENTER) {
                    System.out.println("[inputKey] before enter key pressed, game state: " + gf.gameState);
//                    gf.resetGame();

                    if(gf.commandNum == 0) {// restart game at same level: return to tutorial screen
                        gf.playSoundEffect(0);
                        gf.resetGame();// partial reset(game level unchanged)
                        gf.gameState = gf.tutorialState;
                    }else if(gf.commandNum == 1) {// reset game attributes: return to title screen
                        gf.playSoundEffect(0);
                        gf.resetGame();// full reset
                        gf.gameLevel = gf.levelEasy; // default level
                        gf.numOfVaccines = 5;// number of vaccines(for easy)
                        // return to title screen
                        gf.gameState = gf.titleState;
                    }
                    System.out.println("[inputKey] after enter key pressed, game state: " + gf.gameState);
                    gf.commandNum = 0;
                }
            }
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

    /**
     * handle when keys are released
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key== KeyEvent.VK_F){
            pressF = false;
        }
        if(key == KeyEvent.VK_UP){
            pressedUp = false;
        }
        else if(key == KeyEvent.VK_DOWN){
            pressedDown = false;
        }
        else if(key == KeyEvent.VK_LEFT){
            pressedLeft = false;
        }
        else if(key == KeyEvent.VK_RIGHT){
            pressedRight = false;
        }
    }
}
