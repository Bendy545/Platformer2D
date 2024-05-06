package levelClasses;

import game.LoadImg;
import objects.Passage;
import objects.Spike;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static game.LoadImg.GetLevelData;

public class Level {
    private BufferedImage img;
    private int[][] lvlData;
    private ArrayList<Spike> spikes;
    private ArrayList<Passage> passages;

    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createSpikes();
        createPassages();
    }
    private void createPassages() {
        passages = LoadImg.getPassages(img);
    }
    private void createSpikes() {
        spikes = LoadImg.getSpikes(img);
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
    public ArrayList<Passage> getPassages() {
        return passages;
    }
}
