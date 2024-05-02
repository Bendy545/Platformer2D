package objects;

import levelClasses.Level;
import stateOfGame.Playing;
import static stateOfGame.Playing.*;
import utilize.SaveLoad;
import levelClasses.LevelHandler;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilize.SaveLoad.*;

public class ObjectHandler {


    private LevelHandler levelHandler;
    private Playing playing;
    private BufferedImage spikeImg;
    private BufferedImage passageImg;
    private ArrayList<Spike> spikes;
    private ArrayList<Passage> passages;

    public ObjectHandler(Playing playing, LevelHandler levelHandler) {
        this.playing = playing;
        this.levelHandler = levelHandler;
        loadImgs();
        loadObject(playing.getLevelHandler().getCurrentLevel());
    }


    public void checkPassageHit(Player p) {
        for (Passage passage : passages) {
            if (passage.getHitbox().intersects(p.getHitBox())) {
                levelHandler.loadNextLevel();
                playing.resetPlayerPosition();
            }
        }
    }
    public void checkSpikeHit(Player p) {
        for (Spike s : spikes) {
            if (s.getHitbox().intersects(p.getHitBox())) {
                p.kill();
            }
        }
    }
    public void loadImgs() {
        spikeImg = SaveLoad.GetSpriteAtlas(SPIKES);
        passageImg = SaveLoad.GetSpriteAtlas(PASSAGE);

    }

    public void loadObject(Level newLevel) {
        spikes = newLevel.getSpikes();
        passages = newLevel.getPassages();
    }

    private void drawPassages(Graphics g) {
        for (Passage passage : passages) {
            g.drawImage(passageImg,(int) (passage.getHitbox().x),(int)(passage.getHitbox().y), PASSAGE_WIDTH, PASSAGE_HEIGHT, null);
        }
    }
    private void drawSpikes(Graphics g) {

        for (Spike s : spikes) {
            g.drawImage(spikeImg,(int) (s.getHitbox().x), (int)(s.getHitbox().y), SPIKE_WIDTH, SPIKE_HEIGHT, null);
        }
    }
    public void draw(Graphics g) {
        drawSpikes(g);
        drawPassages(g);
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public ArrayList<Passage> getPassages() {
        return passages;
    }
}
