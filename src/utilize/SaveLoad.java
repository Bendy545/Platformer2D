package utilize;

import gameClasses.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class SaveLoad {

    public static final String SPIKES = "spikes.png";
    public static final String PLAYER_ATLAS = "PlayerSpriteSheet2.png";
    public static final String LEVEL_ATLAS = "Textures4.png";

    public static final String MENU_BUTTONS = "Buttons3.png";
    public static final String MENU_BACKGROUND = "MenuBackGround.png";

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
}
