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

        // key input in title screen to move cursor
        if(gf.gameState == gf.titleState) {
            if(key == KeyEvent.VK_UP) {
                gf.commandNum--;
                if(gf.commandNum == -1) {
                    gf.commandNum = 2;
                }
            }
            if(key == KeyEvent.VK_DOWN) {
                gf.commandNum++;
                if(gf.commandNum == 3) {
                    gf.commandNum = (gf.commandNum % 3);
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gf.commandNum == gf.optionStart) {
                    gf.gameState = gf.playState;
                }
                if(gf.commandNum == gf.optionChangeLevel) {
                    gf.gameState = gf.changeLevelState;
                }
                if(gf.commandNum == gf.optionExit) {
                    System.exit(0);
                }
            }
        }


        // key input in change level screen to move cursor
        if(gf.gameState == gf.changeLevelState) {
            if(key == KeyEvent.VK_UP) {
                gf.commandNum--;
                if(gf.commandNum == -1) {
                    gf.commandNum = 2;
                }
            }
            if(key == KeyEvent.VK_DOWN) {
                gf.commandNum++;
                if(gf.commandNum == 3) {
                    gf.commandNum = (gf.commandNum % 3);
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gf.commandNum == gf.levelEasy) {
                    gf.gameLevel = gf.levelEasy;
                }
                if(gf.commandNum == gf.levelIntermediate) {
                    gf.gameLevel = gf.levelIntermediate;
                }
                if(gf.commandNum == gf.levelChallenge) {
                    gf.gameLevel = gf.levelChallenge;
                }
                gf.commandNum = 0;
//                gf.gameState = gf.titleState;
            }
        }


        // key input during play state
        if(gf.gameState == gf.playState) {
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
