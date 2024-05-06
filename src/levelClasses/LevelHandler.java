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
            System.out.println("Game completed");
            GameState.state = GameState.GAME_OVER;
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
        BufferedImage img = LoadImg.GetSpriteAtlas(LoadImg.TEXTURES);
        levelSprite = new BufferedImage[10];
        for (int i = 0; i < 2; i++) {
            for (int g = 0; g < 5; g++) {
                int index = i * 5 + g;
                levelSprite[index] = img.getSubimage(g * 32, i * 32, 32, 32);
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
}
