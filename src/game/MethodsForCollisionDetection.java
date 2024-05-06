package game;

import game.Game;

import java.awt.geom.Rectangle2D;

public class MethodsForCollisionDetection {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        boolean isSolidAtCurrentPosition = IsSolid(x, y, lvlData);
        boolean isSolidAtTopRight = IsSolid(x + width, y, lvlData);
        boolean isSolidAtBottomLeft = IsSolid(x, y + height, lvlData);
        boolean isSolidAtBottomRight = IsSolid(x + width, y + height, lvlData);
        return !(isSolidAtCurrentPosition || isSolidAtTopRight || isSolidAtBottomLeft || isSolidAtBottomRight);
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        boolean isOutsideBounds = x < 0 || x >= Game.GAME_WIDTH || y < 0 || y >= Game.GAME_HEIGHT;
        if (isOutsideBounds) {
            return true;
        }
        int xIndex = (int) (x / Game.TILES_SIZE);
        int yIndex = (int) (y / Game.TILES_SIZE);
        int value = (xIndex >= 0 && xIndex < lvlData[0].length && yIndex >= 0 && yIndex < lvlData.length) ? lvlData[yIndex][xIndex] : -1;
        return value < 0 || value >= 10 || value != 4;
    }
    public static float GetWallEntity(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = getCurrentTile(hitbox);
        int tileXPos = getTileXPosition(currentTile);
        int xOffset = calculateXOffset(hitbox, xSpeed);
        return tileXPos + xOffset;
    }
    private static int getCurrentTile(Rectangle2D.Float hitbox) {
        return (int) (hitbox.x / Game.TILES_SIZE);
    }
    private static int getTileXPosition(int currentTile) {
        return currentTile * Game.TILES_SIZE;
    }
    private static int calculateXOffset(Rectangle2D.Float hitbox, float xSpeed) {
        if (xSpeed > 0) {
            return (int) (Game.TILES_SIZE - hitbox.width) - 1;
        }
        return 0;
    }
    public static boolean IsOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        int bottomLeftX = (int) hitbox.x;
        int bottomLeftY = (int) (hitbox.y + hitbox.height + 1);
        int bottomRightX = (int) (hitbox.x + hitbox.width);
        if (!IsSolid(bottomLeftX, bottomLeftY, lvlData) && !IsSolid(bottomRightX, bottomLeftY, lvlData)) {
            return false;
        }
        return true;
    }
    public static float GetYPosEntity(Rectangle2D.Float hitbox, float airTime) {
        final int tileSize = Game.TILES_SIZE;
        int tileYPos = (int) (hitbox.y / tileSize) * tileSize;
        if (airTime > 0) {
            return calculateAirborneYPos(tileYPos, tileSize, hitbox.height);
        }
        return tileYPos;
    }
    private static float calculateAirborneYPos(int tileYPos, int tileSize, float hitboxHeight) {
        int yOffset = tileSize - (int) hitboxHeight;
        return tileYPos + yOffset - 1;
    }
}
