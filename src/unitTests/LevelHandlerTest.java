package unitTests;

import game.Game;
import levelClasses.Level;
import levelClasses.LevelHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stateOfGame.GameState;
import stateOfGame.Playing;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class LevelHandlerTest {

    private Game game;
    private LevelHandler levelHandler;
    private Playing playing;

    @BeforeEach
    void setUp() {
        game = new Game();
        playing = game.getPlaying();
        levelHandler = new LevelHandler(game);
        levelHandler.setCurrentLevel(0);
        levelHandler.buildAllLevels();
        assertTrue(levelHandler.getCurrentLevel() != null, "Current level should not be null.");
        assertTrue(levelHandler.getLevels().size() > 1, "There should be more than one level.");
    }

    @Test
    void testLoadNextLevel() {
        int initialLevelIndex = levelHandler.getLvlIndex();
        levelHandler.loadNextLevel();
        int newLevelIndex = levelHandler.getLvlIndex();
        assertEquals(initialLevelIndex + 1, newLevelIndex, "Level index should be incremented.");
        levelHandler.setCurrentLevel(levelHandler.getLevels().size() - 1);
        levelHandler.loadNextLevel();
        assertEquals(0, levelHandler.getLvlIndex(), "Level index should be reset to 0.");
        assertEquals(GameState.GAME_COMPLETED, GameState.state, "Game state should be GAME_COMPLETED.");
    }

    private static class TestLevel extends Level {
        public TestLevel() {
            super(new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
        }

        @Override
        public int getSpriteIndex(int x, int y) {
            return 0;
        }
    }
}