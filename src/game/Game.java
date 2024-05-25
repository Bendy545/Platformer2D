package game;
import ui.GameCompletedUI.GameCompleted;
import ui.GameOverUI.GameOver;
import stateOfGame.GameState;
import ui.MenuUI.Menu;
import stateOfGame.Playing;

import java.awt.*;


public class Game{

    private GameCompleted gameCompleted;
    private GameLoop gameLoop;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Playing playing;
    private Menu menu;
    private GameOver gameOver;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_WIDTH = 26;
    public final static int TILES_HEIGHt = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGHt;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gameLoop = new GameLoop(this, FPS_SET, UPS_SET);
       startGameLoop();
    }
    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
        gameOver = new GameOver(this);
        gameCompleted = new GameCompleted(this);

    }

    /**
     * Starts the game loop in a new thread.
     */
    private void startGameLoop() {
        gameThread = new Thread(gameLoop);
        gameThread.start();
    }

    /**
     * Updates the game state based on the current GameState.
     */
    public void update() {
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case GAME_OVER:
                gameOver.update();
                break;
            case QUIT:
                System.exit(0);
            case PLAY_AGAIN:
                GameState.state = GameState.PLAYING;
                break;
            case GAME_COMPLETED:
                gameCompleted.update();
            default:
                break;
        }
    }

    /**
     * Renders the current game state.
     *
     * @param g
     */
    public void render(Graphics g) {

        switch (GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case GAME_OVER:
                gameOver.draw(g);
                break;
            case GAME_COMPLETED:
                gameCompleted.draw(g);
            default:
                break;
        }
    }
    public void windowFocusLost() {
        if (GameState.state == GameState.PLAYING) {
            playing.getPlayer().resetDirBolleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
    public GameOver getGameOver() {
        return gameOver;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setPlaying(Playing playing) {
        this.playing = playing;
    }


    public GameCompleted getGameCompleted() {
        return gameCompleted;
    }
}
