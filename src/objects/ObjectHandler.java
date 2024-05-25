package objects;

import levelClasses.Level;
import stateOfGame.GameState;
import stateOfGame.Playing;
import game.LoadImg;
import levelClasses.LevelHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static game.LoadImg.*;

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

    /**
     * Checks if the player intersects with any passage, and if so, loads the next level and resets the player position.
     *
     * @param p
     */
    public void checkPassageHit(Player p) {
        for (Passage passage : passages) {
            if (passage.getHitbox().intersects(p.getHitBox())) {
                levelHandler.loadNextLevel();
                playing.resetPlayerPosition();
            }
        }
    }

    /**
     * Checks if the player intersects with any spike, and if so, sets the game state to GAME_OVER.
     *
     * @param p
     */
    public void checkSpikeHit(Player p) {
        for (Spike s : spikes) {
            if (s.getHitbox().intersects(p.getHitBox())) {
                GameState.state = GameState.GAME_OVER;
            }
        }
    }

    /**
     * Loads images for spikes and passages from the resources.
     */
    public void loadImgs() {
        spikeImg = LoadImg.getSpriteImg(SPIKES);
        passageImg = LoadImg.getSpriteImg(PASSAGE);
    }

    /**
     * Loads the spikes and passages from the specified level.
     *
     * @param newLevel
     */
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
    public BufferedImage getSpikeImg() {
        return spikeImg;
    }

    public BufferedImage getPassageImg() {
        return passageImg;
    }
}
