package GameOverUI;

import MenuUI.MenuButtons;
import game.Game;
import stateOfGame.GameState;
import stateOfGame.State;
import stateOfGame.StateMethods;
import utilize.SaveLoad;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameOver extends State implements StateMethods {

    private BufferedImage backgoundImg;
    private int gameOverX, gameOverY, gameOverWidth, gameOverHeight;
    private GameOverButtons[] buttons = new GameOverButtons[2];
    public GameOver(Game game) {
        super(game);

    }

    private void loadBackground() {
        backgoundImg = SaveLoad.GetSpriteAtlas(SaveLoad.GAME_OVER_BACKGROUND);
        gameOverWidth = (int) (backgoundImg.getWidth() * Game.SCALE);
        gameOverHeight = (int) (backgoundImg.getHeight() * Game.SCALE);
        setGameOverPosition();
    }
    private void setGameOverPosition() {
        gameOverX = Game.GAME_WIDTH / 2 - gameOverWidth / 2;
        gameOverY = (int) (50 * Game.SCALE);
    }
    private void loadButtons() {
        buttons[0] = new GameOverButtons(Game.GAME_WIDTH, (int)(200 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new GameOverButtons(Game.GAME_WIDTH, (int)(200 * Game.SCALE), 1, GameState.QUIT);
    }
    @Override
    public void update() {
        for (GameOverButtons gb : buttons) {
            gb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgoundImg, gameOverX, gameOverY, gameOverWidth, gameOverHeight, null);
        for (GameOverButtons gb : buttons) {
            gb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (GameOverButtons gb : buttons) {
            if (OnButton1(e, gb)) {
                gb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameOverButtons gb : buttons) {
            gb.setMouseOver(false);
        }
        for (GameOverButtons gb : buttons) {
            if (OnButton1(e, gb)) {
                gb.setMouseOver(true);
                break;
            }
        }
    }

    private void resetButtons() {
        for (GameOverButtons gb : buttons) {
            gb.resetBooleans();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GameOverButtons gb : buttons) {
            if (OnButton1(e, gb)) {
                if (gb.isMousePressed()) {
                    gb.setState();
                }
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
