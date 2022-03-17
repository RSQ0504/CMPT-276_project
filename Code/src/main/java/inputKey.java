import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class inputKey implements KeyListener {
    GameFrame gf;
    public boolean pressedUp, pressedDown,pressedLeft,pressedRight;
    public boolean pressF;

    public inputKey(GameFrame gf) {
        this.gf = gf;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Dont use this
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(gf.gameState == gf.titleState) { // key input in title screen to move cursor
            System.out.println("key pressed in title screen");
            if(key == KeyEvent.VK_UP) {
                gf.commandNum--;
                System.out.println("commandNum = " + gf.commandNum);
                if(gf.commandNum < 0) {
                    gf.commandNum = gf.numOfCommands - 1;
                    System.out.println("[change] commandNum = " + gf.commandNum);
                }
            }else if(key == KeyEvent.VK_DOWN) {
                gf.commandNum++;
                if(gf.commandNum == 3) {
                    gf.commandNum = 0;
                }
            }else if(key == KeyEvent.VK_ENTER) {
                System.out.println("enter pressed in title screen");
                if(gf.commandNum == gf.optionStart) {
                    gf.gameState = gf.playState;
                    gf.stopBGM();
                    gf.commandNum = 0;
                }else if(gf.commandNum == gf.optionChangeLevel) {
                    gf.gameState = gf.changeLevelState;
                    gf.commandNum = gf.gameLevel;
                }else if(gf.commandNum == gf.optionExit) {
                    System.exit(0);
                }
            }
        }else if(gf.gameState == gf.changeLevelState) { // key input in change level screen to move cursor
            System.out.println("key pressed in change level screen");
            if(key == KeyEvent.VK_UP) {
                gf.commandNum--;
                if(gf.commandNum < 0) {
                    gf.commandNum = gf.numOfCommands - 1;
                }
            }else if(key == KeyEvent.VK_DOWN) {
                gf.commandNum++;
                if(gf.commandNum == 3) {
                    gf.commandNum = 0;
                }
            }else if(key == KeyEvent.VK_ENTER) {
                System.out.println("enter pressed in change level screen");
                if(gf.commandNum == gf.levelEasy) {
                    System.out.println("change to easy");
                    gf.gameLevel = gf.levelEasy;
                }else if(gf.commandNum == gf.levelIntermediate) {
                    System.out.println("change to intermediate");
                    gf.gameLevel = gf.levelIntermediate;
                }else if(gf.commandNum == gf.levelChallenge) {
                    System.out.println("change to challenge");
                    gf.gameLevel = gf.levelChallenge;
                }
                gf.commandNum = 0;
                gf.gameState = gf.titleState;
            }
        } else if(gf.gameState == gf.playState) { // key input during play state
            if(key== KeyEvent.VK_F){
                pressF = true;
            }
            if(key == KeyEvent.VK_UP){
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
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key== KeyEvent.VK_F){
            pressF = true;
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
