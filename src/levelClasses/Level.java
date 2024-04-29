package levelClasses;

import objects.Spike;
import utilize.SaveLoad;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilize.SaveLoad.GetLevelData;

public class Level {
    private BufferedImage img;
    private int[][] lvlData;
    private ArrayList<Spike> spikes;

    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createSpikes();
    }

    private void createSpikes() {
        spikes = SaveLoad.GetSpikes(img);
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
    public ArrayList<Spike> getSpikes() {
        return spikes;
    }
}
