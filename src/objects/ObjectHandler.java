package objects;

import levelClasses.Level;
import stateOfGame.Playing;
import static stateOfGame.Playing.*;
import utilize.SaveLoad;
import levelClasses.LevelHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilize.SaveLoad.*;

public class ObjectHandler {


    private Playing playing;
    private BufferedImage spikeImg;
    private ArrayList<Spike> spikes;

    public ObjectHandler(Playing playing) {
        this.playing = playing;
        loadImgs();
        loadObject(playing.getLevelHandler().getCurrentLevel());
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
    }

    public void loadObject(Level newLevel) {
        spikes = newLevel.getSpikes();
    }


    private void drawSpikes(Graphics g) {

        for (Spike s : spikes) {
            g.drawImage(spikeImg,(int) (s.getHitbox().x), (int)(s.getHitbox().y), SPIKE_WIDTH, SPIKE_HEIGHT, null);
        }
    }
    public void draw(Graphics g) {
        drawSpikes(g);
    }


}
