
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JPanel implements Runnable{
    //attributes of GameMap
    public GameObject[][] Map;
    private int startPointX;
    private int startPointY;
    private int endPointX;
    private int endPointY;
    private int cellSize;
    private int width;
    private int height;
    private int colm;
    private int rows;
    private Thread gameThread;
    private inputKey key = new inputKey();
    private int speed = 4;
    private int frame_speed = 60;
    GameMap tile = new GameMap(this);

    GameFrame(int colm, int rows, int cellSize){
        this.colm =colm;
        this.rows = rows;
        this.cellSize = cellSize;

        setUpScreen();
        setStartPoint(100,100);
    }

    public int getCellSize(){return cellSize;}

    public void placeWall(Wall wall){}
    private void setStartPoint(int startPointX, int startPointY){
        this.startPointX = startPointX;
        this.startPointY = startPointY;
    }
   // private void setEndPoint(int endPointX, int endPointY){}
    private void setUpScreen(){
        width = cellSize*colm;
        height = cellSize*rows;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.CYAN);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void beginThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double interval = 1000000000/frame_speed;
        double nextUpdate = System.nanoTime() + interval;
        while(gameThread != null) {
            // 1.UPDATE
            updatePos();
            // 2.DRAW
            repaint();

            try {
                double sleepTime = (nextUpdate - System.nanoTime())/1000000;
                if (sleepTime < 0) {
                    sleepTime = 0;
                }
                Thread.sleep((long)sleepTime);
                nextUpdate += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void updatePos(){
        if(key.pressedUp == true){
            startPointY -= speed;
        }
        else if(key.pressedDown == true){
            startPointY += speed;
        }
        else if(key.pressedLeft == true){
            startPointX -= speed;
        }
        else if(key.pressedRight == true){
            startPointX += speed;
        }

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        tile.draw(g2);
        g2.fillRect(startPointX, startPointY, cellSize/3, cellSize/3);
        g2.dispose();
    }



}
