import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


/**
 * Class to manage Display Frame when Game is opened
 */
public class GameFrame extends JPanel implements Runnable{
    // BGM
    public Music bgm = new Music();
    //public Music soundEffect = new Music();
    public int musicState = 0; // 0 or 1
    public final int musicPaused = 0;
    public final int musicPlaying = 1;
    public int musicTrack = -1;
    public final int track3_playState = 3;
    public final int track4_titleState = 4;




    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1; // game play screen
    public final int pauseState = 2; // game pause screen
    public final int endState = 3; // game end screen
    public final int changeLevelState = 4; // change game level screen

    // TUTORIAL STATE
    public int tutorialState = 5;
    public final int tutorialIntro = 5; // navigation guide for tutorial and background-story screen
    public final int tutorial1 = 6;
    public final int tutorial2 = 7;
    public final int tutorial3 = 8;


    // NARRATION STATE
    public int narrationState = 9; // background story
    public final int narration1 = 9;
    public final int narration2 = 10;
    public final int narration3 = 11;
    public final int narration4 = 12;
    public final int narration5 = 13;
    public final int narration6 = 14;
    public final int narration7 = 15;
    public final int narration8 = 16;
    public final int narration9 = 17;
    public final int narration10 = 18;
    public final int narration11 = 19;



  //Game Result
    public int gameResult;
    public final int fail = 0;
    public final int win = 1;


    // menu
    public int numOfCommands = 3; // number of commands
    public int commandNum = 0; // start: 0, change level: 1, exit: 2
    public final int optionStart = 0;
    public final int optionChangeLevel = 1;
    public final int optionExit = 2;

    GameImage gameImage = new GameImage();

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
    public int numOfVaccines = 5;// easy: 5 intermediate: 7 challenge: 10
    GameMap tileFrame = new GameMap(this);
    public checkCollision check_collision = new checkCollision(this);
    public int[] startPoints = new int[2];

    //Timer
    private TimerClock clock = new TimerClock();
    private int timerState = 0;
    private int timerInactive = 0;// 00:00, ready to start counting
    private int timerInProgress = 1;// counting
    private int timerPaused = 2;// paused
    private int timerTerminated = 3;// completed counting, with final count of time

    // The characters
    public MainCharacter mc = new MainCharacter(this,key);
    private Zombie zombie1 = new Zombie(this,15,200,mc);
    private Zombie zombie2 = new Zombie(this,300,280,mc);
    private Zombie zombie3 = new Zombie(this,650,400,mc);

    // The static characters
    private KindSurvivor goodPerson1 = new KindSurvivor(this,key,mc,120,255,tileFrame.getOriginMap(gameLevel),23,1);
    private BadSurvivor badPerson1 = new BadSurvivor(this,key,mc,262,115);
    private BadSurvivor badPerson2 = new BadSurvivor(this,key,mc,300,280);
    private BadSurvivor badPerson3 = new BadSurvivor(this,key,mc,650,220);

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
      f.add(new Food(this,100,20));
      f.add(new Food(this,250,310));
      f.add(new Food(this,120,300));
      f.add(new Food(this,320,190));
      f.add(new Food(this,505,220));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    /**
     * Class Constructor
     * @param colm
     * @param rows
     * @param cellSize
     * @throws IOException
     */
    GameFrame(int colm, int rows, int cellSize) throws IOException {
        this.colm =colm;
        this.rows = rows;
        this.cellSize = cellSize;
        this.gameState = titleState;
        setUpScreen();
        setStartPoint(100,100);
    }

    /**
     * get default size for tiles
     * @return
     */
    public int getCellSize(){return cellSize;}


    /**
     * set start points when game is started
     * @param startPointX
     * @param startPointY
     */
    private void setStartPoint(int startPointX, int startPointY){
        this.startPointX = startPointX;
        this.startPointY = startPointY;
    }

   // private void setEndPoint(int endPointX, int endPointY){}

    /**
     * set up screen attributes when game is started
     */
    private void setUpScreen(){
        width = cellSize*colm;
        height = cellSize*rows;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0x123456));
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    /**
     * begin Thread for game updates
     */
    public void beginThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    /**
     * update game frame with character positioning, stat updates, and game state
     */
    @Override
    public void run() {
        System.out.println("[run/GameFrame.java] running GameFrame");
        double interval = 1000000000/frame_speed;
        double nextUpdate = System.nanoTime() + interval;
        while(gameThread != null) {
            runGame();
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

    /**
     * Function call while thread is running
     */
    public void runGame(){
        // 1.UPDATE
        updatePos();

        // check whether main character is dead
        Rectangle MC = new Rectangle(mc.x, mc.y,mc.width,mc.height);
        Rectangle endpoint = new Rectangle(tileFrame.getEndPointX(), tileFrame.getEndPointY(), 10, 10);
        if(zombie1.check(MC) || zombie2.check(MC) || zombie3.check(MC) || mc.getHP() == 0){
//                System.out.println("[run/GameFrame] check: main character dead!");
            mc.setHP(0);
            gameResult = fail;
            gameState = endState;
            timerState = timerTerminated;
        }
        // check whether main character is has won
        if(mc.getVaccines() >= numOfVaccines && endpoint.intersects(MC)){
//                System.out.println("[run/GameFrame] check: main character survived!");
            gameResult = win;
            gameState = endState;
            timerState = timerTerminated;
        }

        // 2.DRAW
        repaint();

        // TIMER
        if(gameState == playState) {
//                System.out.println("[run/GameFrame] Game playing");

            if(timerState == timerInactive) {
//                    System.out.println("[run/GameFrame] Start timer");
                // clock = new TimerClock();
                clock.startTimer();
                timerState = timerInProgress;
            }else if(timerState == pauseState) {
//                    System.out.println("[run/GameFrame] resume timer count");
            }

        }else if(gameState == pauseState) {
//                System.out.println("[run/GameFrame] Game paused");
        }else if(gameState == endState) {
            if(timerState == timerTerminated) {
//                    System.out.println("[run/GameFrame] game ended: timer terminated");
            }else {
                timerState = timerTerminated;
//                    System.out.println("[run/GameFrame] game ended: timer not terminated");
            }
        }else if(gameState == tutorialState) {
//                System.out.println("[run/GameFrame] tutorial state");
        }else if(gameState == tutorialState) {
//                System.out.println("[run/GameFrame] title state");
        }

    }

    /**
     * update dynamic character positioning
     */
    private void updatePos(){
        mc.update();
        zombie1.update();
        zombie2.update();
        zombie3.update();
    }

    /**
     * draw game frame and objects in each update
     * @param g
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //check whether mc is killed by zombies
        Rectangle MC = new Rectangle(mc.x, mc.y,mc.width,mc.height);
        Rectangle endpoint = new Rectangle(tileFrame.getEndPointX(), tileFrame.getEndPointY(), 10, 10);


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
            g2.drawImage(gameImage.bgTitleScreen, 0, 0, getWidth(), getHeight(), null);

            int x, y;
            int numBtn = 3; // number of buttons in menu
            int cursorSize = 64;
            String[] buttons = {"START GAME", "CHANGE LEVEL", "EXIT"};

            // display cursor
            for(int i = 0; i < numBtn; i++) {
                int btnLength = (int)g2.getFontMetrics().getStringBounds(buttons[i], g2).getWidth();

                // set position of button
                x = this.width/2 - btnLength/2;
                y = 280 + (cursorSize*i) - 10;

                // draw cursor
                if(commandNum == i) {
                    g2.drawImage(gameImage.cursor, x - (2*cursorSize + 25), y, cursorSize, cursorSize, null);
                }
            }

            // draw overlay (key instruction/guide at the bottom)
            g2.drawImage(gameImage.titleScreenOverlay, 0, getHeight() - 48, getWidth(), 48, null);

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


            //reward
            for(int i=0;i<v.size();i++)
                v.get(i).draw(g2);
            for(int i=0;i<f.size();i++)
                f.get(i).draw(g2);
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
            goodPerson1.resetBoard(tileFrame.getOriginMap(gameLevel));
            mc.draw(g2);
            zombie1.draw(g2);
            zombie2.draw(g2);
            zombie3.draw(g2);
            if(goodPerson1.appearStatus) {
                goodPerson1.draw(g2);
            }
            if(badPerson1.appearStatus) {
                badPerson1.draw(g2);
            }
            if(badPerson2.appearStatus) {
                badPerson2.draw(g2);
            }
            if(badPerson3.appearStatus) {
                badPerson3.draw(g2);
            }


            // draw frame (score, time, overlay)
            mc.drawScore(g2,645,4, numOfVaccines);
            clock.draw(g2,555,4);
            g2.drawImage(gameImage.overlayImage, 0, 0, 340, 28, null);


        }else if(gameState == changeLevelState) { // screen display for change-level-screen
            // display background image
            g2.drawImage(gameImage.bgChangeLevelScreen, 0, 0, getWidth(), getHeight(), null);

            int x, y;
            int numBtn = 3; // number of options
            int cursorSize = 64;
            String[] levels = {"EASY", "INTERMEDIATE", "CHALLENGE"};

            for(int i = 0; i < numBtn; i++) {
                int btnLength = (int)g2.getFontMetrics().getStringBounds(levels[i], g2).getWidth();

                // set position of button
                x = this.width/2 - btnLength/2;
                y = 280 + (cursorSize*i) - 10;

                // draw cursor
                if(commandNum == i) {
                    g2.drawImage(gameImage.cursor, x-(2*cursorSize + 10), y, cursorSize, cursorSize, null);
                }
            }

            // draw overlay (key instruction/guide at the bottom)
            g2.drawImage(gameImage.titleScreenOverlay, 0, getHeight() - 48, getWidth(), 48, null);
        }else if(gameState == pauseState) {
            g2.drawImage(gameImage.bgImage, 0, 0, getWidth(), getHeight(), null);
            // screen display when game is paused
            // not used
        }else if(gameState == endState) {

            if(gameResult == fail) {
                // background image
                g2.drawImage(gameImage.gameFailBg, 0, 0, getWidth(), getHeight(), null);
                g2.drawImage(gameImage.gamefail, 165, 165, null);
                clock.stopTimer();
                clock.draw(g2,260,280);
                mc.drawScore(g2,360,280, numOfVaccines);
            }

            if(gameResult == win) {
                // background image
                g2.drawImage(gameImage.gameWinBg, 0, 0, getWidth(), getHeight(), null);
                g2.drawImage(gameImage.gamewin, 165, 165, null);
                clock.stopTimer();
                clock.draw(g2,260,280);
                mc.drawScore(g2,360,280, numOfVaccines);

            }

            // add buttons (retry, return)
            if(commandNum == 0) {
                g2.drawImage(gameImage.retryButtonLight, 240, 390, null);// selected option
                g2.drawImage(gameImage.returnButtonRegular, 370, 390, null);
            }else if(commandNum == 1) {
                g2.drawImage(gameImage.retryButtonRegular, 240, 390, null);
                g2.drawImage(gameImage.returnButtonLight, 370, 390, null);// selected option
            }


            // draw overlay (key instruction/guide at the bottom)
            g2.drawImage(gameImage.endScreenOverlay, 0, getHeight() - 48, getWidth(), 48, null);


        }else if(gameState == tutorialState) {
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

            // tutorial screen
            if(tutorialState == tutorialIntro) {
                g2.drawImage(gameImage.getTutorialImage(0), 0, 0, getWidth(), getHeight(), null);
            }else if(tutorialState == tutorial1) {
                g2.drawImage(gameImage.getTutorialImage(1), 0, 0, getWidth(), getHeight(), null);
            }else if(tutorialState == tutorial2) {
                g2.drawImage(gameImage.getTutorialImage(2), 0, 0, getWidth(), getHeight(), null);
            }else if(tutorialState == tutorial3) {
                g2.drawImage(gameImage.getTutorialImage(3), 0, 0, getWidth(), getHeight(), null);
            }else {
                // exception -> display first page
                tutorialState = tutorialIntro;
            }


        }else if(gameState == narrationState) {
            // narration screen
            if(narrationState == narration1) {
                g2.drawImage(gameImage.getNarrationImage(0), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration2) {
                g2.drawImage(gameImage.getNarrationImage(1), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration3) {
                g2.drawImage(gameImage.getNarrationImage(2), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration4) {
                g2.drawImage(gameImage.getNarrationImage(3), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration5) {
                g2.drawImage(gameImage.getNarrationImage(4), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration6) {
                g2.drawImage(gameImage.getNarrationImage(5), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration7) {
                g2.drawImage(gameImage.getNarrationImage(6), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration8) {
                g2.drawImage(gameImage.getNarrationImage(7), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration9) {
                g2.drawImage(gameImage.getNarrationImage(8), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration10) {
                g2.drawImage(gameImage.getNarrationImage(9), 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration11) {
                g2.drawImage(gameImage.getNarrationImage(10), 0, 0, getWidth(), getHeight(), null);
            }
        }else {
            // exception -> go back to title screen
            gameState = titleState;
        }

        g2.dispose();
    }

    /**
     * play background music
     * @param i
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public void playBGM(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(musicState == musicPaused) {
            System.out.println("[playBGM] playing bgm.");
            if(bgm.setFile(i)) {
                bgm.play(i);
                bgm.loop(i);
                musicState = musicPlaying;
                musicTrack = i;// keeping track of which bgm is playing
                System.out.println("[playBGM] musicTrack: " + musicTrack);
            }
        }
    }

    /**
     * stop background music
     */
    public void stopBGM(int i) {
        System.out.println("[stopBGM] stopping bgm.");
        if(musicState == musicPlaying) {
            System.out.println("[stopBGM] stop playing bgm.");
            bgm.stop(i);
            musicTrack = -1;
            System.out.println("[stopBGM] musicTrack: " + musicTrack);
        }
        musicState = musicPaused;
    }

    /**
     * play sound effects
     * @param i
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public void playSoundEffect(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // sound effect music files are 0, 1, 2
        // 0: Click_music_1
        // 1: Click_music_2
        // 2: Click_music_3
        if(i >= 0 && i <= 2) {
            System.out.println("[playBGM] play sound effect.");
            bgm.setFile(i);
            bgm.play(i);
        }
    }

    public void resetGame(int resetMode) {
        // resetMode 0: partial reset - preserve game level of previous play
        // resetMode 1: full reset - starting from title screen with default values set to levelEasy settings
        if(resetMode == 0) {
            // partial reset
            if(gameLevel < 0 || gameLevel > 2) {
                // exception
                gameLevel = levelEasy;
            }

            // set number of vaccines
            if(gameLevel == levelEasy) {
                numOfVaccines = 5;
            }else if(gameLevel == levelIntermediate) {
                numOfVaccines = 7;
            }else if(gameLevel == levelChallenge) {
                numOfVaccines = 10;
            }
        }else if(resetMode == 1) {
            // full reset
            gameLevel = levelEasy;
            numOfVaccines = 5;
        }

        mc.resetAttributesMC();

        mc.setDefaultValue(tileFrame.getStartPoints(gameLevel));
        // The characters
        zombie1 = new Zombie(this,15,200,mc);
        zombie2 = new Zombie(this,300,280,mc);
        zombie3 = new Zombie(this,650,400,mc);

        // The static characters
        goodPerson1 = new KindSurvivor(this,key,mc,120,255,tileFrame.getOriginMap(gameLevel),23,1);
        badPerson1 = new BadSurvivor(this,key,mc,262,115);
        badPerson2 = new BadSurvivor(this,key,mc,300,280);
        badPerson3 = new BadSurvivor(this,key,mc,650,220);

        //reward
        v.clear();
        f.clear();
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
            f.add(new Food(this,100,20));
            f.add(new Food(this,250,320));
            f.add(new Food(this,120,300));
            f.add(new Food(this,320,190));
            f.add(new Food(this,695,220));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

    }

}
