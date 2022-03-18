import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;


public class GameFrame extends JPanel implements Runnable{
    // BGM
    public Music bgm = new Music();
//    public Music soundEffect = new Music();
    public int musicState = 0; // 0 or 1
    public final int musicPaused = 0;
    public final int musicPlaying = 1;



    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1; // game play screen
    public final int pauseState = 2; // game pause screen
    public final int endState = 3; // game end screen
    public final int changeLevelState = 4; // change game level screen
    public final int narrationState = 5; // background story


    // TUTORIAL STATE
    public int tutorialState = 6;
    public final int tutorialIntro = 6; // navigation guide for tutorial and background-story screen
    public final int tutorial1 = 7;
    public final int tutorial2 = 8;
    public final int tutorial3 = 9;


    // menu
    public int numOfCommands = 3; // number of commands
    public int commandNum = 0; // start: 0, change level: 1, exit: 2
    public final int optionStart = 0;
    public final int optionChangeLevel = 1;
    public final int optionExit = 2;
    private Image bgImage;
    private Image tutorialImage;
    private Image narrationImage;

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
    private inputKey key = new inputKey(this);
    private int speed = 4;
    private int frame_speed = 60;
    public int gameLevel = 0;
    public final int levelEasy = 0;
    public final int levelIntermediate = 1;
    public final int levelChallenge = 2;
    GameMap tileFrame = new GameMap(this);
    public checkCollision check_collision = new checkCollision(this);
    public int[] startPoints = new int[2];

    //Timer
//    private TimerClock clock = new TimerClock();
    private TimerClock clock;
    private int timerState = 0;
    private int timerInactive = 0;// 00:00, ready to start counting
    private int timerInProgress = 1;// counting
    private int timerPaused = 2;// paused
    private int timerTerminated = 3;// completed counting, with final count of time

    // The characters
    public MainCharacter mc = new MainCharacter(this,key);
    private Zombie zombie1 = new Zombie(this,15,200,mc);
    private Zombie zombie2 = new Zombie(this,300,280,mc);
    private Zombie zombie3 = new Zombie(this,650,220,mc);

    // The static characters
    private KindSurvivor goodPerson1 = new KindSurvivor(this,key,mc,120,255);
    private BadSurvivor badPerson1 = new BadSurvivor(this,key,mc,262,115);

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

  GameFrame(int colm, int rows, int cellSize) throws MalformedURLException {
        this.colm =colm;
        this.rows = rows;
        this.cellSize = cellSize;
        this.gameState = titleState;
        setUpScreen();
        setStartPoint(100,100);

        bgImage = new ImageIcon("src/main/java/picture/GUI_image/titleScreenBg.jpg").getImage();
        tutorialImage = new ImageIcon("src/main/java/picture/GUI_image/tuto").getImage();
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
        this.setBackground(new Color(0x123456));
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void beginThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        System.out.println("[run/GameFrame.java] running GameFrame");
        double interval = 1000000000/frame_speed;
        double nextUpdate = System.nanoTime() + interval;
        while(gameThread != null) {
            // 1.UPDATE
            updatePos();
            // 2.DRAW
            repaint();

            // TIMER
            if(gameState == playState) {
//                System.out.println("[run/GameFrame] Game playing");

                if(timerState == timerInactive) {
                    System.out.println("[run/GameFrame] Start timer");
                    clock = new TimerClock();
//                    clock.startTimer();
                    timerState = timerInProgress;
                }else if(timerState == pauseState) {
                    System.out.println("[run/GameFrame] resume timer count");
                }

            }else if(gameState == pauseState) {
                System.out.println("[run/GameFrame] Game paused");
            }else if(gameState == endState) {
                System.out.println("[run/GameFrame] Game ended");
            }
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


        // control display depending on game state
        if(gameState == titleState) {
            // play bgm
            try {
                playBGM(4);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // display background image
            g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);

            // show title
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64));
            String title = "Survive in the end";
            int x = 0;
            int y = 0;
            int titleLength = (int)g2.getFontMetrics().getStringBounds(title, g2).getWidth();
            x = this.width/2 - titleLength/2;
            y = 250;
            g2.setColor(Color.white);
            g2.drawString(title, x, y);


            // show menu button
            int numBtn = 3; // number of buttons in menu
            int btnLength = 0;
            int fontSize = 20;
            int margin = 12;
            String[] buttons = {"START GAME", "CHANGE LEVEL", "EXIT"};


            // set font for buttons
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, fontSize));

            // display buttons
            for(int i = 0; i < numBtn; i++) {
                btnLength = (int)g2.getFontMetrics().getStringBounds(buttons[i], g2).getWidth();

                // set position of button
                x = this.width/2 - btnLength/2;
                y = 320 + (fontSize + margin)*i;

                // draw button
                g2.drawString(buttons[i], x, y);

                // display '>' before selected option in menu
                if(commandNum == i) {
                    g2.drawString(">", x-24, y);
                }
            }



        }else if(gameState == playState) {
            // play bgm
            try {
                playBGM(3);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // setup game
            tileFrame.draw(g2, tileFrame.getBoard(gameLevel));
            mc.drawMC(g2);
            zombie1.drawZombie(g2);
            zombie2.drawZombie(g2);
            zombie3.drawZombie(g2);
            goodPerson1.drawKindCharacter(g2);
            badPerson1.drawBadCharacter(g2);
            clock.draw(g2);

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


        }else if(gameState == changeLevelState) { // screen display for change-level-screen
            // display background image
            g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);

            // show title
            int x = 0;
            int y = 0;
            int numBtn = 3; // number of options
            int btnLength = 0;
            int fontSize = 32;
            int margin = 12;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontSize));
            String title = "Select the level of game you would like to play.";

            int titleLength = (int)g2.getFontMetrics().getStringBounds(title, g2).getWidth();
            x = this.width/2 - titleLength/2;
            y = 240;
            g2.setColor(Color.white);
            g2.drawString(title, x, y);


            // show menu button
            fontSize = 20;
            String[] levels = {"EASY", "INTERMEDIATE", "CHALLENGE"};

            // set font for buttons
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, fontSize));

            // display buttons
            for(int i = 0; i < numBtn; i++) {
                btnLength = (int)g2.getFontMetrics().getStringBounds(levels[i], g2).getWidth();

                // set position of button
                x = this.width/2 - btnLength/2;
                y = 320 + (fontSize + margin)*i;

                // draw button
                g2.drawString(levels[i], x, y);

                // display '>' before selected option in menu
                if(commandNum == i) {
                    g2.drawString(">", x-24, y);
                }
            }
        }else if(gameState == pauseState) {
            // screen display when game is paused
        }else if(gameState == endState) {
            // screen display when game ends
        }else if(gameState == tutorialState) {
            // display background image
            g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);

        }else {
            // exception -> go back to title screen
            gameState = titleState;
        }

        g2.dispose();
    }

    public void playBGM(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(musicState == musicPaused) {
            System.out.println("[playBGM] playing bgm.");
            if(bgm.setFile(i)) {
                bgm.play();
                bgm.loop();
                musicState = musicPlaying;
            }
        }
    }

    public void stopBGM() {
        System.out.println("[stopBGM] stopping bgm.");
        if(musicState == musicPlaying) {
            System.out.println("[stopBGM] stop playing bgm.");
            bgm.stop();
        }
        musicState = musicPaused;
    }

    public void playSoundEffect(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // sound effect music files are 0, 1, 2
        // 0: Click_music_1
        // 1: Click_music_2
        // 2: Click_music_3
        if(i >= 0 && i <= 2) {
            System.out.println("[playBGM] play sound effect.");
            bgm.setFile(i);
            bgm.play();
        }
    }

    public int[] getStartPoints(){
      System.out.println(gameLevel);
      startPoints = tileFrame.getStartPoints(gameLevel);
      return startPoints;
    }

}
