package utilize;

import game.Game;
import objects.Passage;
import objects.Spike;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class SaveLoad {

    public static final String GAMEOVER_BUTTONS = "GameOverButtons.png";
    public static final String PASSAGE = "Passage.png";
    public static final int PASS = 5;
    public static final int PASSAGE_WIDTH_DEFAULT = 32;
    public static final int PASSAGE_HEIGHT_DEFAULT = 32;
    public static final int PASSAGE_WIDTH = (int) (Game.SCALE * PASSAGE_WIDTH_DEFAULT);
    public static final int PASSAGE_HEIGHT = (int) (Game.SCALE * PASSAGE_HEIGHT_DEFAULT);
    public static final String SPIKES = "spikes2.png";
    public static final int SPIKE = 4;
    public static final int SPIKE_WIDTH_DEFAULT = 32;
    public static final int SPIKE_HEIGHT_DEFAULT = 32;
    public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
    public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);
    public static final String PLAYER_SPRITE = "PlayerSpriteSheet2.png";
    public static final String TEXTURES = "Textures4.png";
    public static final String MENU_BUTTONS = "Buttons3.png";
    public static final String MENU_BACKGROUND = "MenuBackGround.png";
    public static final String GAME_OVER_BACKGROUND = "GameOver.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = SaveLoad.class.getResourceAsStream("/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return img;
    }

    public static BufferedImage[] getAllLevels() {
        URL url = SaveLoad.class.getResource("/levels");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File[] files = file.listFiles();
        File[] fileSorted = new File[files.length];
        for (int i = 0; i < fileSorted.length; i++) {
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    fileSorted[i] = files[j];
                }
            }
        }

        BufferedImage[] imgs = new BufferedImage[fileSorted.length];

        for (int i = 0; i < imgs.length; i++) {
            try {
                imgs[i] = ImageIO.read(fileSorted[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imgs;
    }

    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if (value >= 10)
                    value= 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }
    public static ArrayList<Spike> GetSpikes(BufferedImage img) {
        ArrayList<Spike> list = new ArrayList<>();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getBlue();
                if (value == SPIKE) {
                    list.add(new Spike(j * Game.TILES_SIZE, i * Game.TILES_SIZE, SPIKE));
                }
            }
        }
        return list;
    }
    public static ArrayList<Passage> getPassages(BufferedImage img) {
        ArrayList<Passage> list = new ArrayList<>();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getBlue();
                if (value == PASS) {
                    list.add(new Passage(j * Game.TILES_SIZE, i * Game.TILES_SIZE, PASS));
                }
            }
        }
        return list;
    }
}
