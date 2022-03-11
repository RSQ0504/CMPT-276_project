import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class inputKey implements KeyListener {
    public boolean pressedUp, pressedDown,pressedLeft,pressedRight;
    @Override
    public void keyTyped(KeyEvent e) {
        //Dont use this
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
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

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
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
