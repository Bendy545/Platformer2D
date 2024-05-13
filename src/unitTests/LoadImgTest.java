package unitTests;

import game.LoadImg;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class LoadImgTest {

    @Test
    void getAllLevels() {
        BufferedImage[] levels = LoadImg.getAllLevels();

        assertNotNull(levels);

        assertTrue(levels.length > 0);

        for (BufferedImage level : levels) {
            assertNotNull(level);
        }
    }
    @Test
    public void testGetSpriteImg() {
        String fileName = "UITextures/GameCompletedButtons.png";
        BufferedImage image = LoadImg.getSpriteImg(fileName);
        assertNotNull(image, "BufferedImage should not be null");
        assertEquals(540, image.getWidth(), "Image width should be 100");
        assertEquals(180, image.getHeight(), "Image height should be 50");
        int rgb = image.getRGB(0, 0);
        assertEquals(-16777216, rgb, "Color of pixel (0, 0) should be blue");
    }
}