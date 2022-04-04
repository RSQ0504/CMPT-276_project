/**
 * class to check collisions between wall and characters
 */
public class checkCollision {
    public GameFrame check_frame; //frame used to check collisions
    public checkCollision(GameFrame check_frame) {
        this.check_frame = check_frame;
    }

    /**
     * check for intersection between character and wall
     * set boolean collisionArea accordingly
     * @param obj
     */
    public void checkTile(DynamicCharacter obj){
        int scaledCellSize = check_frame.getCellSize()/2;

        //character position variables
        int charcTop = obj.y+obj.hitArea.y;
        int charcBottom = obj.y+obj.hitArea.y + obj.hitArea.height;
        int charcLeft = obj.x+obj.hitArea.x;
        int charcRight = obj.x+obj.hitArea.x + obj.hitArea.width;

        //Wall index variables
        int charcTopRow = charcTop/scaledCellSize;
        int charcBottomRow = charcBottom/scaledCellSize;
        int charcLeftCol = charcLeft/scaledCellSize;
        int charcRightCol = charcRight/scaledCellSize;

        int tile1,tile2;
        int[][] board = check_frame.tileFrame.getBoard(check_frame.gameLevel);

        switch(obj.direction){
            case "up":
                charcTopRow = (charcTop - obj.speed)/scaledCellSize;

                if (charcTopRow < 0 || charcTopRow > 23 || charcLeftCol < 0 || charcLeftCol > 31 || charcRightCol < 0 || charcRightCol > 31){
                    obj.collisionArea = true;
                } else {
                    tile1 = board[charcTopRow][charcLeftCol]; //left corner check
                    tile2 = board[charcTopRow][charcRightCol]; //right corner check

                    if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                        obj.collisionArea = true;
                    }
                }
                break;
            case "down":
                charcBottomRow = (charcBottom + obj.speed)/scaledCellSize;
                if (charcBottomRow < 0 || charcBottomRow > 23 || charcLeftCol < 0 || charcLeftCol > 31 || charcRightCol < 0 || charcRightCol > 31){
                    obj.collisionArea = true;
                } else {
                    tile1 = board[charcBottomRow][charcLeftCol]; //left corner check
                    tile2 = board[charcBottomRow][charcRightCol]; //right corner check

                    if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                        obj.collisionArea = true;
                    }
                }
                break;
            case "right":
                charcRightCol = (charcRight - obj.speed)/scaledCellSize;
                if (charcBottomRow < 0 || charcBottomRow > 23 || charcTopRow < 0 || charcTopRow > 23 || charcRightCol < 0 || charcRightCol > 31){
                    obj.collisionArea = true;
                } else {
                    tile1 = board[charcTopRow][charcRightCol+1]; //top corner check
                    tile2 = board[charcBottomRow][charcRightCol+1]; //bottom corner check

                    if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                        obj.collisionArea = true;
                    }
                }
                break;
            case "left":
                charcLeftCol = (charcLeft + obj.speed)/scaledCellSize;
                if (charcBottomRow < 0 || charcBottomRow > 23 || charcTopRow < 0 || charcTopRow > 23 || charcLeftCol < 0 || charcLeftCol > 31) {
                    obj.collisionArea = true;
                }else {
                    tile1 = board[charcTopRow][charcLeftCol]; //top corner check
                    tile2 = board[charcBottomRow][charcLeftCol]; //bottom corner check

                    if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                        obj.collisionArea = true;
                    }
                }
                break;
            default:break;
        }
    }
}
