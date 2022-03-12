
import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

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
    //Reward
  LinkedList<Vaccine> v = new LinkedList<>();
  LinkedList <Food> f = new LinkedList<>();
  {
    try {
      v.add(new Vaccine(this,218,138));
      v.add(new Vaccine(this,485,48));
      v.add(new Vaccine(this,383,382));
      v.add(new Vaccine(this,340,512));
      v.add(new Vaccine(this,386,159));
      v.add(new Vaccine(this,102,383));
      v.add(new Vaccine(this,52,240));
      v.add(new Vaccine(this,330,353));
      v.add(new Vaccine(this,645,217));
      v.add(new Vaccine(this,695,453));

      f.add(new Food(this,575,230));
      f.add(new Food(this,450,100));
      f.add(new Food(this,350,20));
      f.add(new Food(this,250,320));
      f.add(new Food(this,120,300));
      f.add(new Food(this,50,530));
      f.add(new Food(this,320,190));
      f.add(new Food(this,695,220));
      f.add(new Food(this,387,220));
      f.add(new Food(this,550,420));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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

        //reward
        for(int i=0;i<v.size();i++)
          v.get(i).draw(g2);
        for(int i=0;i<f.size();i++)
          f.get(i).draw(g2);
        Rectangle MC = new Rectangle(mc.x, mc.y,mc.width,mc.height);
        for(int i=0;i<f.size();i++){
          if(f.get(i).check(MC)){
          f.get(i).setAppear(false);
          f.get(i).increaseHP(mc);
          f.remove(i);
          break;
          }
        }
        for (int i=0;i<v.size();i++){
          if(v.get(i).check(MC)){
          v.get(i).setAppear(false);
          v.get(i).increaseVaccine(mc);
          v.remove(i);
          break;
          }
        }

        g2.dispose();
    }
}
