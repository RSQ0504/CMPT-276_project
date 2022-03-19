import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * class to implement maze map
 */
public class GameMap {
    private GameFrame frame;
    public Tile[] tile;
    public int startPointX;
    public int startPointY;
    private int endPointX =30;
    private int endPointY;

    //Map for each Game levels
    private int[][] maplvl1 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,0,0,1} ,
            {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
            {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
            {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
            {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
            {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
            {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
            {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
            {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
            {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
            {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
            {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
            {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1},
            {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
            {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
            {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
            {1,1,1,1,1,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

    private int[][] maplvl2 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,0} ,
            {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
            {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
            {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
            {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
            {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
            {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
            {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
            {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
            {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
            {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
            {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
            {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,1,0,1},
            {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
            {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
            {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
            {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

    private int maplvl3[][] = {
            {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,0,0,1} ,
            {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,0,1},
            {1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1},
            {1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,0,1,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,1},
            {1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1},
            {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1},
            {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1},
            {1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1},
            {1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1},
            {1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1},
            {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1},
            {1,0,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1},
            {1,0,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1},
            {1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1},
            {1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,1},
            {1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1},
            {1,1,0,1,1,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1},
            {1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

    /**
     * class constructor
     * @param frame
     */
    GameMap(GameFrame frame){
        this.frame = frame;
        tile = new Tile[10];
        getTileImg();
    }


    /**
     * Get end x point for maze
     * @return
     */
    public int getEndPointX(){
      return endPointX;
    }

    /**
     * Get end y point for maze
     * @return
     */
    public int getEndPointY(){
      return endPointY;
    }

    /**
     * Get maze map depending on game level
     * @param lvl
     * @return
     */
    public int[][] getBoard(int lvl){
        int[][] board;
        switch(lvl){
            case 0:
                board = setNewBoard(maplvl1);
                break;
            case 1:
                board =  setNewBoard(maplvl2);
                break;
            case 2:
                board =  setNewBoard(maplvl3);
                break;
            default:
                board = null;
                System.out.println("map level does not exist.");
        }
        return board;
    }

    private int[][] setNewBoard(int[][] map) {
        int newMap[][] = new int[24][32];
        for(int i = 0; i < 24; i++){
            for (int j = 0; j < 32; j++) {
                if(map[i][j] == 0){
                    // It is an earth
                    newMap[i][j] = 0;
                    if((i!=0) && (map[i-1][j]==1)){
                        // There are upside wall
                        newMap[i][j] = 3;
                        if((j!=0) && (map[i][j-1]==1)){
                            // There are upside wall and leftist wall
                            newMap[i][j] = 5;
                        }
                    }
                    else if((j!=0) && (map[i][j-1]==1)){
                        // There are leftist wall
                        newMap[i][j] = 4;
                    }
                    else if((i!=0) && (j!=0) && (map[i-1][j-1]==1)){
                        // There are upLeftSide wall
                        newMap[i][j] = 6;
                    }
                }
                else {
                    // It is a wall
                    newMap[i][j] = 1;
                    if((i!=23) && (map[i+1][j]==0)){
                        newMap[i][j] = 2;
                    }
                }
            }
        }
        return newMap;
    }

    /**
     * Get the origin maze map depending on game level
     * @param lvl
     * @return
     */
    public int[][] getOriginMap(int lvl){
        int[][] board;
        switch(lvl){
            case 0:
                board = maplvl1;
                break;
            case 1:
                board =  maplvl2;
                break;
            case 2:
                board =  maplvl3;
                break;
            default:
                board = null;
                System.out.println("map level does not exist.");
        }
        return board;
    }


    /**
     * Get player starting point depending on game level
     * @param lvl
     * @return
     */
    public int[] getStartPoints(int lvl){
        int[] startPoints = new int[2];
        switch(lvl){
            case 0:
                startPointX = 365;
                startPointY = 0;
                break;
            case 1:
                startPointX = 730;
                startPointY = 0;
                break;
            case 2:
                startPointX = 265;
                startPointY = 0;
                break;
            default:
                System.out.println("map level does not exist.");
        }

        startPoints[0] = startPointX;
        startPoints[1] = startPointY;
        System.out.println(startPoints);

        return startPoints;
    }

    /**
     * Read and import images for walls and floor
     */
    public void getTileImg()  {
        try {
            tile[0] = new Tile();
            tile[0].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/earth.png"));
            tile[0].setCollision(false);

            tile[1] = new Tile();
            tile[1].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_dark.png"));
            tile[1].setCollision(true);

            tile[2] = new Tile();
            tile[2].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_new.png"));
            tile[2].setCollision(true);

            tile[3] = new Tile();
            tile[3].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/earth_upShadow.png"));
            tile[3].setCollision(false);

            tile[4] = new Tile();
            tile[4].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/earth_leftShadow.png"));
            tile[4].setCollision(false);

            tile[5] = new Tile();
            tile[5].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/earth_upLeftShadow.png"));
            tile[5].setCollision(false);

            tile[6] = new Tile();
            tile[6].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/earth_upLeftShadow2.png"));
            tile[6].setCollision(false);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Draw maze
     * @param g2
     * @param map
     */
    public void draw(Graphics2D g2, int[][] map){
        for(int i = 0; i < 24; i++){
            for (int j = 0; j < 32; j++){
                g2.drawImage(tile[map[i][j]].tileImg, j* frame.getCellSize()/2, i* frame.getCellSize()/2, 24, 24, null);
            }
        }
    }
}
