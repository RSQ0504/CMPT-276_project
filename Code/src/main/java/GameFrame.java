
import javax.swing.JPanel;
import java.awt.*;

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

    // The characters
    private MainCharacter mc = new MainCharacter(this,key);
    private Zombie zombie1 = new Zombie(this,15,200,mc);
    private Zombie zombie2 = new Zombie(this,300,30,mc);
    private Zombie zombie3 = new Zombie(this,550,220,mc);

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
        mc.updateMC();
        zombie1.updateZombie();
        zombie2.updateZombie();
        zombie3.updateZombie();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tile.draw(g2);

        mc.drawMC(g2);
        zombie1.drawZombie(g2);
        zombie2.drawZombie(g2);
        zombie3.drawZombie(g2);

        g2.dispose();
    }
}
