package levelClasses;

import java.awt.image.BufferedImage;

import static utilize.SaveLoad.GetLevelData;

public class Level {
    private BufferedImage img;
    private int[][] lvlData;

    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
    }

    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLvlData() {
        return lvlData;
    }
}
