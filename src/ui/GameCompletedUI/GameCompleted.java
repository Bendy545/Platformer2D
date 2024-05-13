package ui.GameCompletedUI;

import game.Game;
import game.LoadImg;
import stateOfGame.GameState;
import stateOfGame.State;
import stateOfGame.StateMethods;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static ui.Buttons.GameOverButtons.BUTTON_WIDTH;

public class GameCompleted extends State implements StateMethods {

    private BufferedImage backgoundImg;
    private int gameCompletedX, gameGameCompletedY, gameCompletedWidth, gameCompletedHeight;
    private GameCompletedButtons[] buttons = new GameCompletedButtons[2];
    public GameCompleted(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }
    private void loadBackground() {
        backgoundImg = LoadImg.getSpriteImg(LoadImg.GAME_COMPLETED_BACKGROUND);
        gameCompletedWidth = (int) (backgoundImg.getWidth() * Game.SCALE);
        gameCompletedHeight = (int) (backgoundImg.getHeight() * Game.SCALE);
        setGameOverPosition();
    }
    private void setGameOverPosition() {
        gameCompletedX = Game.GAME_WIDTH / 2 + 60 - gameCompletedWidth / 2;
        gameGameCompletedY = (int) (10 * Game.SCALE);
    }
    private void loadButtons() {
        int x2Offset = (int) (-190 * Game.SCALE);
        int xOffset = (int) (250 * Game.SCALE);
        buttons = new GameCompletedButtons[2];
        for (int i = 0; i < buttons.length; i++) {
            int columnIndex = i == 0? 0 : 1;
            int x = Game.GAME_WIDTH / 2 - BUTTON_WIDTH / 2 + (i * (BUTTON_WIDTH + xOffset)) + x2Offset;
            int y = (int) (110 * Game.SCALE);
            buttons[i] = new GameCompletedButtons(x, y, columnIndex, i == 0? GameState.PLAY_AGAIN: GameState.QUIT);
        }
    }

    @Override
    public void update() {
        for (GameCompletedButtons gb : buttons) {
            gb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgoundImg, gameCompletedX, gameGameCompletedY, gameCompletedWidth, gameCompletedHeight, null);
        for (GameCompletedButtons gb : buttons) {
            gb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (GameCompletedButtons gb : buttons) {
            if (OnButton2(e, gb)) {
                gb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameCompletedButtons gb : buttons) {
            gb.setMouseOver(false);
        }
        for (GameCompletedButtons gb : buttons) {
            if (OnButton2(e, gb)) {
                gb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GameCompletedButtons gb : buttons) {
            if (OnButton2(e, gb)) {
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
    private void resetButtons() {
        for (GameCompletedButtons gb : buttons) {
            gb.resetBooleans();
        }
    }
}