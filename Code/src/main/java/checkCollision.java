public class checkCollision {
    public GameFrame check_frame; //frame used to check collisions
    public checkCollision(GameFrame check_frame) {
        this.check_frame = check_frame;
    }

    public void checkTile(DynamicCharacter obj){
        int scaledCellSize = check_frame.getCellSize()/2;
        int charcTop = obj.y+obj.hitArea.y;
        int charcBottom = obj.y+obj.hitArea.y + obj.hitArea.height;
        int charcLeft = obj.x+obj.hitArea.x;
        int charcRight = obj.x+obj.hitArea.x + obj.hitArea.width;

        int charcTopRow = charcTop/scaledCellSize;
        int charcBottomRow = charcBottom/scaledCellSize;
        int charcLeftCol = charcLeft/scaledCellSize;
        int charcRightCol = charcRight/scaledCellSize;

        int tile1,tile2;
        int[][] board = check_frame.tileFrame.getBoard(check_frame.gameLevel);

        switch(obj.direction){
            case "up":
                charcTopRow = (charcTop - obj.speed)/scaledCellSize;
                tile1 = board[charcTopRow][charcLeftCol]; //left corner check
                tile2 = board[charcTopRow][charcRightCol]; //right corner check

                if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                    obj.collisionArea = true;
                }
                break;
            case "down":
                charcBottomRow = (charcBottom + obj.speed)/scaledCellSize;
                tile1 = board[charcBottomRow][charcLeftCol]; //left corner check
                tile2 = board[charcBottomRow][charcRightCol]; //right corner check

                if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                    obj.collisionArea = true;
                }
                break;
            case "right":
                charcRightCol = (charcRight - obj.speed)/scaledCellSize;
                tile1 = board[charcTopRow][charcRightCol+1]; //left corner check
                tile2 = board[charcBottomRow][charcRightCol+1]; //right corner check

                if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                    obj.collisionArea = true;
                }
                break;
            case "left":
                charcLeftCol = (charcLeft + obj.speed)/scaledCellSize;
                tile1 = board[charcTopRow][charcLeftCol]; //left corner check
                tile2 = board[charcBottomRow][charcLeftCol]; //right corner check

                if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
                    obj.collisionArea = true;
                }
                break;
            default:break;
        }
    }
}
