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

    // background images, initialized in GameFrame constructor
    private Image bgImage;
    private Image bgTitleScreen;
    private Image bgChangeLevelScreen;
    private Image overlayImage;
    private Image endScreenOverlay;
//    private Image winEndScreenOverlay;
//    private Image failEndScreenOverlay;
    private Image titleScreenOverlay;
    private Image gameWinBg;
    private Image gameFailBg;
    private Image cursor;

    // buttons for end screen
    private Image retryButtonRegular;
    private Image retryButtonLight;
    private Image returnButtonRegular;
    private Image returnButtonLight;


    // tutorial images
    private Image tutorialPage;
    private Image tutorialPage1;
    private Image tutorialPage2;
    private Image tutorialPage3;

    // narration images
    private Image narrationPage1;
    private Image narrationPage2;
    private Image narrationPage3;
    private Image narrationPage4;
    private Image narrationPage5;
    private Image narrationPage6;
    private Image narrationPage7;
    private Image narrationPage8;
    private Image narrationPage9;
    private Image narrationPage10;
    private Image narrationPage11;

//    private BufferedImage gamewin = ImageIO.read(new File("src/main/java/picture/GUI_image/GameWin_interface.png"));
//    private BufferedImage gamefail = ImageIO.read(new File("src/main/java/picture/GUI_image/GameOver_interface.png"));

    private BufferedImage gamewin = ImageIO.read(new File("src/main/java/picture/GUI_image/GameWin_interface_noButtons.png"));
    private BufferedImage gamefail = ImageIO.read(new File("src/main/java/picture/GUI_image/GameOver_interface_noButtons.png"));

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
      f.add(new Food(this,350,20));
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

        bgImage = new ImageIcon("src/main/java/picture/GUI_image/titleScreenBg.jpg").getImage();
        bgTitleScreen = new ImageIcon("src/main/java/picture/GUI_image/title_screen_bg.png").getImage();
        bgChangeLevelScreen = new ImageIcon("src/main/java/picture/GUI_image/change_level_screen_bg.png").getImage();
        overlayImage = new ImageIcon("src/main/java/picture/GUI_image/overlay_instructions.png").getImage();
        titleScreenOverlay = new ImageIcon("src/main/java/picture/GUI_image/title_screen_overlay.png").getImage();
        endScreenOverlay = new ImageIcon("src/main/java/picture/GUI_image/end_screen_overlay.png").getImage();
//        winEndScreenOverlay = new ImageIcon("src/main/java/picture/GUI_image/win_end_screen_overlay.png").getImage();
//        failEndScreenOverlay = new ImageIcon("src/main/java/picture/GUI_image/fail_end_screen_overlay.png").getImage();
        gameWinBg = new ImageIcon("src/main/java/picture/GUI_image/escape_success_bg.jpg").getImage();
        gameFailBg = new ImageIcon("src/main/java/picture/GUI_image/escape_fail_bg.jpg").getImage();
        cursor = new ImageIcon("src/main/java/picture/GUI_image/redHand_icon.png").getImage();

        // get images for buttons in end screen
        retryButtonRegular = new ImageIcon("src/main/java/picture/GUI_image/RetryButton_noLight.png").getImage();
        retryButtonLight = new ImageIcon("src/main/java/picture/GUI_image/RetryButton_light.png").getImage();
        returnButtonRegular = new ImageIcon("src/main/java/picture/GUI_image/ReturnButton_noLight.png").getImage();
        returnButtonLight = new ImageIcon("src/main/java/picture/GUI_image/ReturnButton_light.png").getImage();


        // get images for tutorial screen
        tutorialPage = new ImageIcon("src/main/java/picture/GUI_image/tutorial/tutorial_sample.png").getImage();
        tutorialPage1 = new ImageIcon("src/main/java/picture/GUI_image/tutorial/tutorial1_sample.png").getImage();
        tutorialPage2 = new ImageIcon("src/main/java/picture/GUI_image/tutorial/tutorial2_sample.png").getImage();
        tutorialPage3 = new ImageIcon("src/main/java/picture/GUI_image/tutorial/tutorial3_sample.png").getImage();

        // get images for narration screen
        narrationPage1 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story1.png").getImage();
        narrationPage2 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story2.png").getImage();
        narrationPage3 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story3.png").getImage();
        narrationPage4 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story4.png").getImage();
        narrationPage5 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story5.png").getImage();
        narrationPage6 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story6.png").getImage();
        narrationPage7 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story7.png").getImage();
        narrationPage8 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story8.png").getImage();
        narrationPage9 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story9.png").getImage();
        narrationPage10 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story10.png").getImage();
        narrationPage11 = new ImageIcon("src/main/java/picture/GUI_image/background_story/background_story11.png").getImage();
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
     * update dynamic character positioning
     */
    private void updatePos(){
        mc.updateMC();
        zombie1.updateZombie();
        zombie2.updateZombie();
        zombie3.updateZombie();
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
            g2.drawImage(bgTitleScreen, 0, 0, getWidth(), getHeight(), null);

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
                    g2.drawImage(cursor, x - (2*cursorSize + 25), y, cursorSize, cursorSize, null);
                }
            }

            // draw overlay (key instruction/guide at the bottom)
            g2.drawImage(titleScreenOverlay, 0, getHeight() - 48, getWidth(), 48, null);

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
            mc.drawMC(g2);
            zombie1.drawZombie(g2);
            zombie2.drawZombie(g2);
            zombie3.drawZombie(g2);
            if(goodPerson1.status) {
                goodPerson1.drawKindCharacter(g2);
                goodPerson1.drawKindCharcMsgBox(g2);
            }
            if(badPerson1.status) {
                badPerson1.drawBadCharacter(g2);
            }
            if(badPerson2.status) {
                badPerson2.drawBadCharacter(g2);
            }
            if(badPerson3.status) {
                badPerson3.drawBadCharacter(g2);
            }


            // draw frame (score, time, overlay)
            mc.drawScore(g2,645,4, numOfVaccines);
            clock.draw(g2,555,4);
            g2.drawImage(overlayImage, 0, 0, 340, 28, null);


        }else if(gameState == changeLevelState) { // screen display for change-level-screen
            // display background image
            g2.drawImage(bgChangeLevelScreen, 0, 0, getWidth(), getHeight(), null);

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
                    g2.drawImage(cursor, x-(2*cursorSize + 10), y, cursorSize, cursorSize, null);
                }
            }

            // draw overlay (key instruction/guide at the bottom)
            g2.drawImage(titleScreenOverlay, 0, getHeight() - 48, getWidth(), 48, null);
        }else if(gameState == pauseState) {
            g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
            // screen display when game is paused
            // not used
        }else if(gameState == endState) {

            if(gameResult == fail) {
                // background image
                g2.drawImage(gameFailBg, 0, 0, getWidth(), getHeight(), null);

                g2.drawImage(gamefail, 165, 165, null);
                clock.stopTimer();
                clock.draw(g2,260,280);
                mc.drawScore(g2,360,280, numOfVaccines);
            }

            if(gameResult == win) {
                // background image
                g2.drawImage(gameWinBg, 0, 0, getWidth(), getHeight(), null);

                g2.drawImage(gamewin, 165, 165, null);
                clock.stopTimer();
                clock.draw(g2,260,280);
                mc.drawScore(g2,360,280, numOfVaccines);

            }

            // add buttons (retry, return)
            if(commandNum == 0) {
                g2.drawImage(retryButtonLight, 240, 390, null);// selected option
                g2.drawImage(returnButtonRegular, 370, 390, null);
            }else if(commandNum == 1) {
                g2.drawImage(retryButtonRegular, 240, 390, null);
                g2.drawImage(returnButtonLight, 370, 390, null);// selected option
            }


            // draw overlay (key instruction/guide at the bottom)
            g2.drawImage(endScreenOverlay, 0, getHeight() - 48, getWidth(), 48, null);


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
                g2.drawImage(tutorialPage, 0, 0, getWidth(), getHeight(), null);
            }else if(tutorialState == tutorial1) {
                g2.drawImage(tutorialPage1, 0, 0, getWidth(), getHeight(), null);
            }else if(tutorialState == tutorial2) {
                g2.drawImage(tutorialPage2, 0, 0, getWidth(), getHeight(), null);
            }else if(tutorialState == tutorial3) {
                g2.drawImage(tutorialPage3, 0, 0, getWidth(), getHeight(), null);
            }else {
                // exception -> display first page
                tutorialState = tutorialIntro;
            }


        }else if(gameState == narrationState) {
            // narration screen
            if(narrationState == narration1) {
                g2.drawImage(narrationPage1, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration2) {
                g2.drawImage(narrationPage2, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration3) {
                g2.drawImage(narrationPage3, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration4) {
                g2.drawImage(narrationPage4, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration5) {
                g2.drawImage(narrationPage5, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration6) {
                g2.drawImage(narrationPage6, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration7) {
                g2.drawImage(narrationPage7, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration8) {
                g2.drawImage(narrationPage8, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration9) {
                g2.drawImage(narrationPage9, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration10) {
                g2.drawImage(narrationPage10, 0, 0, getWidth(), getHeight(), null);
            }else if(narrationState == narration11) {
                g2.drawImage(narrationPage11, 0, 0, getWidth(), getHeight(), null);
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
          f.add(new Food(this,350,20));
          f.add(new Food(this,250,310));
          f.add(new Food(this,120,300));
          f.add(new Food(this,320,190));
          f.add(new Food(this,505,220));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

}
