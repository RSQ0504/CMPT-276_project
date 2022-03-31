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
    private int endPointY = 540;

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
        tile = new Tile[20];
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
                board = setNewBoard(setWall(maplvl1));
                break;
            case 1:
                board =  setNewBoard(setWall(maplvl2));
                break;
            case 2:
                board =  setNewBoard(setWall(maplvl3));
                break;
            default:
                board = null;
                System.out.println("map level does not exist.");
        }
        return board;
    }

    private int[][] setWall(int[][] map){
        int newMap[][] = map;
        for(int i = 0; i < 24; i++){
            for (int j = 0; j < 32; j++) {
                if(map[i][j] == 0){
                    newMap[i][j] = 0;
                }
                else{
                    newMap[i][j] = 1;
                    if((i!=23) && (map[i+1][j]==0)){
                        // There are DownSide earth
                        newMap[i][j] = 2;
                    }
                }
            }
        }
        return newMap;
    }

    private int[][] setNewBoard(int[][] map) {
        int newMap[][] = new int[24][32];
        for(int i = 0; i < 24; i++){
            for (int j = 0; j < 32; j++) {
                if(map[i][j] == 0){
                    // It is an earth
                    newMap[i][j] = 0;
                    if((i!=0) && ((map[i-1][j]==1)||(map[i-1][j]==2))){
                        // There are upside wall
                        newMap[i][j] = 3;
                        if((j!=0) && ((map[i][j-1]==1)||(map[i][j-1]==2))){
                            // There are upside wall and leftist wall
                            newMap[i][j] = 5;
                        }
                    }
                    else if((j!=0) && ((map[i][j-1]==1)||(map[i][j-1]==2))){
                        // There are leftist wall
                        newMap[i][j] = 4;
                    }
                    else if((i!=0) && (j!=0) && ((map[i-1][j-1]==1)||(map[i-1][j-1]==2))){
                        // There are upLeftSide wall
                        newMap[i][j] = 6;
                    }
                }
                else {
                    // It is a wall
                    if(map[i][j] == 1)
                        newMap[i][j] = 1;
                    else if(map[i][j] == 2)
                        newMap[i][j] = 2;
                    if((i!=23) && (map[i+1][j]==0)){
                        // There are DownSide earth
                        newMap[i][j] = 2;
                    }
                    else if((i!=0) && (map[i-1][j]==0)){
                        // There are upSide earth
                        newMap[i][j] = 7;
                        if((j!=0) && ((map[i][j-1]==0) || (map[i][j-1]==2))){
                            // There are upSide earth and leftSide earth
                            newMap[i][j] = 10;
                            if((j!=31) && ((map[i][j+1]==0)||(map[i][j+1]==2))){
                                // There are upSide earth, leftSide earth and rightSide earth
                                newMap[i][j] = 11;
                            }
                        }
                        else if((j!=31) && ((map[i][j+1]==0)|| (map[i][j+1]==2))){
                            // There are upSide earth and rightSide earth
                            newMap[i][j] = 12;
                        }
                    }
                    else if((j!=0) && ((map[i][j-1]==0)|| (map[i][j-1]==2))){
                        // There are leftSide earth
                        newMap[i][j] = 8;
                        if((j!=31) && ((map[i][j+1]==0)|| (map[i][j+1]==2))){
                            // There are leftSide earth and rightSide earth
                            newMap[i][j] = 15;
                        }
                    }
                    else if((j!=31) && ((map[i][j+1]==0)|| (map[i][j+1]==2))){
                        // There are rightSide earth
                        newMap[i][j] = 9;
                    }
                    else if((i!=0) && (j!=0) && (map[i-1][j-1]==0)){
                        // There are upLeftSide earth
                        newMap[i][j] = 13;
                    }
                    else if((i!=0) && (j!=31) && (map[i-1][j+1]==0)){
                        // There are upRightSide earth
                        newMap[i][j] = 14;
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

            tile[7] = new Tile();
            tile[7].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_UpEarth.png"));
            tile[7].setCollision(true);

            tile[8] = new Tile();
            tile[8].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_leftEarth.png"));
            tile[8].setCollision(true);

            tile[9] = new Tile();
            tile[9].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_rightEarth.png"));
            tile[9].setCollision(true);

            tile[10] = new Tile();
            tile[10].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_leftUpEarth.png"));
            tile[10].setCollision(true);

            tile[11] = new Tile();
            tile[11].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_leftUpRightEarth.png"));
            tile[11].setCollision(true);

            tile[12] = new Tile();
            tile[12].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_rightUpEarth.png"));
            tile[12].setCollision(true);

            tile[13] = new Tile();
            tile[13].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_1.png"));
            tile[13].setCollision(true);

            tile[14] = new Tile();
            tile[14].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_2.png"));
            tile[14].setCollision(true);

            tile[15] = new Tile();
            tile[15].tileImg = ImageIO.read(new File("src/main/java/picture/Tiles/wall_leftRightEarth.png"));
            tile[15].setCollision(true);
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
