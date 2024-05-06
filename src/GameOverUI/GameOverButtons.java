package GameOverUI;


import stateOfGame.GameState;
import utilize.SaveLoad;

import static utilize.Animations.UI.GameOverButtons.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverButtons {

    private Rectangle buttonHitbox;
    private boolean mouseOver, mousePressed;
    private int x, y, columnIndex, index;
    private GameState state;
    private BufferedImage[] buttonSprite;

    public GameOverButtons(int x, int y, int columnIndex, GameState state) {
        this.x = x;
        this.y = y;
        this.columnIndex = columnIndex;
        this.state = state;
        loadButtonsTexture();
        ButtonHitbox();
    }

    private void ButtonHitbox() {
        buttonHitbox = new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void loadButtonsTexture() {
        buttonSprite = new BufferedImage[3];
        BufferedImage img = SaveLoad.GetSpriteAtlas(SaveLoad.GAMEOVER_BUTTONS);
        for (int i = 0; i <buttonSprite.length; i++) {
            buttonSprite[i] = img.getSubimage(i * BUTTON_DEFAUlT_WIDTH, columnIndex * BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAUlT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(buttonSprite[index], x, y, BUTTON_WIDTH, BUTTON_HEIGHT, null);
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
}
