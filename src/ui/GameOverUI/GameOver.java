package ui.GameOverUI;


import game.Game;
import static ui.Buttons.GameOverButtons.*;

import stateOfGame.GameState;
import stateOfGame.State;
import stateOfGame.StateMethods;
import game.LoadImg;

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
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgoundImg = LoadImg.getSpriteImg(LoadImg.GAME_OVER_BACKGROUND);
        gameOverWidth = (int) (backgoundImg.getWidth() * Game.SCALE);
        gameOverHeight = (int) (backgoundImg.getHeight() * Game.SCALE);
        setGameOverPosition();
    }
    private void setGameOverPosition() {
        gameOverX = Game.GAME_WIDTH / 2 + 90 - gameOverWidth / 2;
        gameOverY = (int) (10 * Game.SCALE);
    }
    private void loadButtons() {
        int xOffset = (int) (20 * Game.SCALE);
        buttons = new GameOverButtons[2];
        for (int i = 0; i < buttons.length; i++) {
            int columnIndex = i == 0? 0 : 1;
            int x = Game.GAME_WIDTH / 2 - BUTTON_WIDTH / 2 + (i * (BUTTON_WIDTH + xOffset));
            int y = (int) (105 * Game.SCALE);
            buttons[i] = new GameOverButtons(x, y, columnIndex, i == 0? GameState.PLAY_AGAIN: GameState.QUIT);
        }
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
                  if (gb.getState() == GameState.PLAY_AGAIN) {
                      game.getPlaying().resetAll();
                      GameState.state = GameState.PLAYING;
                  }
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
