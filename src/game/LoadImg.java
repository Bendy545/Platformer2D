package game;

import objects.Object;
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
import java.util.stream.IntStream;

public class LoadImg {

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
        InputStream is = LoadImg.class.getResourceAsStream("/" + fileName);

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
        URL url = LoadImg.class.getResource("/levels");
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
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] levelData = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                levelData[y][x] = extractLevelValue(img.getRGB(x, y));
            }
        }
        return levelData;
    }
    private static int extractLevelValue(int rgb) {
        Color color = new Color(rgb);
        int value = color.getRed();
        return (value >= 10) ? 0 : value;
    }
    public static ArrayList<Spike> getSpikes(BufferedImage img) {
        return detectObjects(img, SPIKE);
    }
    public static ArrayList<Passage> getPassages(BufferedImage img) {
        return detectObjects(img, PASS);
    }
    private static <T extends Object> ArrayList<T> detectObjects(BufferedImage img, int targetColor) {
        ArrayList<T> objects = new ArrayList<>();
        int tileSize = Game.TILES_SIZE;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color color = new Color(img.getRGB(x, y));
                int value = color.getBlue();
                if (value == targetColor) {
                    if (targetColor == SPIKE) {
                        objects.add((T) new Spike(x * tileSize, y * tileSize, SPIKE));
                    } else if (targetColor == PASS) {
                        objects.add((T) new Passage(x * tileSize, y * tileSize, PASS));
                    }
                }
            }
        }
        return objects;
    }
}
