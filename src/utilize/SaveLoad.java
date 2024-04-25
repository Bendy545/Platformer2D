package utilize;

import gameClasses.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SaveLoad {

    public static final String PLAYER_ATLAS = "PlayerSpriteSheet2.png";
    public static final String LEVEL_ATLAS = "Textures.png";
    public static final String LEVEL_ONE_DATA = "LevelONE.png";
    public static final String MENU_BUTTONS = "Buttons3.png";

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

    public static int[][] GetLevelData() {
        int[][] lvlData = new int[Game.TILES_HEIGHt][Game.TILES_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

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