package game;

import objects.Object;
import objects.Passage;
import objects.Spike;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LoadImg {
    public static final String BACKGROUND_IMAGE = "objects&textures/backGroundImage.png";
    public static final String GAME_COMPLETED_BUTTONS = "UITextures/GameCompletedButtons.png";
    public static final String GAMEOVER_BUTTONS = "UITextures/GameOverButtons.png";
    public static final String PASSAGE = "objects&textures/Passage.png";
    public static final int PASS = 5;
    public static final int PASSAGE_WIDTH_DEFAULT = 32;
    public static final int PASSAGE_HEIGHT_DEFAULT = 32;
    public static final int PASSAGE_WIDTH = (int) (Game.SCALE * PASSAGE_WIDTH_DEFAULT);
    public static final int PASSAGE_HEIGHT = (int) (Game.SCALE * PASSAGE_HEIGHT_DEFAULT);
    public static final String SPIKES = "objects&textures/spikes2.png";
    public static final int SPIKE = 4;
    public static final int SPIKE_WIDTH_DEFAULT = 32;
    public static final int SPIKE_HEIGHT_DEFAULT = 32;
    public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
    public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);
    public static final String PLAYER_SPRITE = "player/PlayerSpriteSheet2.png";
    public static final String TEXTURES = "objects&textures/Textures4.png";
    public static final String MENU_BUTTONS = "UITextures/MenuButtons.png";
    public static final String MENU_BACKGROUND = "UITextures/MenuBackGround.png";
    public static final String GAME_OVER_BACKGROUND = "UITextures/GameOver.png";
    public static final String GAME_COMPLETED_BACKGROUND = "UITextures/GameCompleted.png";

    /**
     * Loads and returns a BufferedImage from a given file name.
     *
     * @param fileName
     * @return
     */
    public static BufferedImage getSpriteImg(String fileName) {
        try (InputStream is = LoadImg.class.getResourceAsStream("/" + fileName)) {
            if (is == null) {
                throw new FileNotFoundException("File not found: " + fileName);
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    /**
     * Loads all level images from the 'levels' directory and returns them as an array of BufferedImages.
     *
     * @return an array of BufferedImage objects representing all level images
     */
    public static BufferedImage[] getAllLevels() {
        URL url = LoadImg.class.getResource("/levels");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File[] files = file.listFiles();
        Arrays.sort(files, Comparator.comparingInt(f -> Integer.parseInt(f.getName().replace(".png", ""))));
        BufferedImage[] imgs = new BufferedImage[files.length];
        for (int i = 0; i < imgs.length; i++) {
            try{
                imgs[i] = ImageIO.read(files[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imgs;
    }

    /**
     * Extracts level data from a BufferedImage where each pixel's red value represents a tile value.
     *
     * @param img
     * @return
     */
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

    /**
     * Extracts the level value from a pixel's RGB value. The red component represents the value.
     *
     * @param rgb
     * @return
     */
    private static int extractLevelValue(int rgb) {
        Color color = new Color(rgb);
        int value = color.getRed();
        return (value >= 10) ? 0 : value;
    }

    /**
     * Detects and returns an ArrayList of Spike objects in a level image based on the blue color value.
     *
     * @param img
     * @return
     */
    public static ArrayList<Spike> getSpikes(BufferedImage img) {
        return detectObjects(img, SPIKE);
    }

    /**
     * Detects and returns an ArrayList of Passage objects in a level image based on the blue color value.
     *
     * @param img
     * @return
     */
    public static ArrayList<Passage> getPassages(BufferedImage img) {
        return detectObjects(img, PASS);
    }

    /**
     * Generic method to detect objects in a level image based on the blue color value.
     *
     * @param img
     * @param targetColor
     * @return
     * @param <T>
     */
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
