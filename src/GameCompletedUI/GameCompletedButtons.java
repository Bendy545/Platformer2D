package GameCompletedUI;

import game.LoadImg;
import stateOfGame.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import static game.Animations.UI.GameOverButtons.*;

public class GameCompletedButtons {

    private Rectangle buttonHitbox;
    private boolean mouseOver, mousePressed;
    private int x, y, columnIndex, index;
    private GameState state;
    private BufferedImage[] buttonSprite;

    public GameCompletedButtons(int x, int y, int columnIndex, GameState state) {
        this.x = x;
        this.y = y;
        this.columnIndex = columnIndex;
        this.state = state;
        loadButtonsTexture();
        ButtonHitbox();
    }
    public void draw(Graphics g) {
        g.drawImage(buttonSprite[index], x, y, BUTTON_WIDTH, BUTTON_HEIGHT, null);
    }
    private void ButtonHitbox() {
        buttonHitbox = new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }
    private void loadButtonsTexture() {
        buttonSprite = new BufferedImage[3];
        BufferedImage img = LoadImg.GetSpriteAtlas(LoadImg.GAME_COMPLETED_BUTTONS);
        for (int i = 0; i <buttonSprite.length; i++) {
            buttonSprite[i] = img.getSubimage(i * BUTTON_DEFAUlT_WIDTH, columnIndex * BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAUlT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }
    public void update() {

        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setState() {
        GameState.state = state;
    }
    public boolean isMousePressed() {
        return mousePressed;
    }
    public Rectangle getButtonHitbox() {
        return buttonHitbox;
    }

    public GameState getState() {
        return state;
    }
}
