package levelClasses;
import game.Game;
import stateOfGame.GameState;
import stateOfGame.Playing;
import game.LoadImg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private int lvlIndex = 0;

    public LevelHandler(Game game) {
        this.game = game;
        importLevelSprite();
        levels = new ArrayList<>();
        buildAllLevels();
    }
    public void setCurrentLevel(int index) {
        if (index >= 0 && index < levels.size()) {
            lvlIndex = index;
        }
    }
    public void updateObjectHandler(Playing playing) {
        playing.getObjectHandler().loadObject(getCurrentLevel());
    }
    public void loadNextLevel() {

        lvlIndex++;
        if (lvlIndex >=  levels.size()) {
            lvlIndex = 0;
            GameState.state = GameState.GAME_COMPLETED;
        }
        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getPlayer().loadlvlData(newLevel.getLvlData());
        updateObjectHandler(game.getPlaying());
    }

    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadImg.getAllLevels();
        for (BufferedImage img : allLevels) {
            levels.add(new Level(img));
        }
    }
    private void importLevelSprite() {
        BufferedImage img = LoadImg.getSpriteImg(LoadImg.TEXTURES);
        levelSprite = new BufferedImage[10];
        final int SPRITE_SIZE = 32;
        final int ROWS = 2;
        final int COLUMNS = 5;

        for (int i = 0; i < ROWS; i++) {
            for (int g = 0; g < COLUMNS; g++) {
                int index = i * COLUMNS + g;
                int x = g * SPRITE_SIZE;
                int y = i * SPRITE_SIZE;
                levelSprite[index] = img.getSubimage(x, y, SPRITE_SIZE, SPRITE_SIZE);
            }
        }
    }
    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_HEIGHt; j++)
            for (int i = 0; i < Game.TILES_WIDTH; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i , j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j,Game.TILES_SIZE,Game.TILES_SIZE,null);
            }
    }
    public void update() {
    }
    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = new ArrayList<>();
        this.levels.addAll(levels);
    }

}
