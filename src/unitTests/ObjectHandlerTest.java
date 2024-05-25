package unitTests;

import game.Game;
import levelClasses.LevelHandler;
import objects.ObjectHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import stateOfGame.Playing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ObjectHandlerTest {

    private ObjectHandler objectHandler;

    @TempDir
    File tempDir;

    @BeforeEach
    public void setUp() {
        Playing playing = new Playing(new Game());
        LevelHandler levelHandler = new LevelHandler(new Game());
        objectHandler = new ObjectHandler(playing, levelHandler);
    }

    @Test
    public void testLoadImgs() throws IOException {
        objectHandler.loadImgs();
        BufferedImage spikeImg = objectHandler.getSpikeImg();
        BufferedImage passageImg = objectHandler.getPassageImg();
        assertNotNull(spikeImg, "Spike image should not be null");
        assertNotNull(passageImg, "Passage image should not be null");
        assertEquals(32, spikeImg.getWidth(), "Spike image width should be 32");
        assertEquals(32, spikeImg.getHeight(), "Spike image height should be 32");
        assertEquals(32, passageImg.getWidth(), "Passage image width should be 32");
        assertEquals(32, passageImg.getHeight(), "Passage image height should be 32");
    }
}