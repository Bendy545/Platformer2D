package levelClasses;
import gameClasses.Game;
import levelClasses.Level;
import utilize.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelHandler(Game game) {
        this.game = game;
        importLevelSprite();
        levelOne = new Level(SaveLoad.GetLevelData());
    }

    private void importLevelSprite() {
        BufferedImage img = SaveLoad.GetSpriteAtlas(SaveLoad.LEVEL_ATLAS);
        levelSprite = new BufferedImage[14];
        for (int i = 0; i < 2; i++) {
            for (int g = 0; g < 7; g++) {
                int index = i * 7 + g;
                levelSprite[index] = img.getSubimage(g * 32, i * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_HEIGHt; j++)
            for (int i = 0; i < Game.TILES_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i , j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j,Game.TILES_SIZE,Game.TILES_SIZE,null);
            }
    }
    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
