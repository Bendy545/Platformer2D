package MenuUI;
import stateOfGame.GameState;
import utilize.SaveLoad;
import static utilize.Animations.UI.MenuButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButtons {

    private Rectangle buttonHitbox;
    private boolean mouseOver, mousePressed;
    private int xPos, yPos, rowIndex, index;
    private GameState state;
    private int xOffsetCenter = BUTTON_WIDTH / 2;
    private BufferedImage[] buttonSprite;

    public MenuButtons(int xPos, int yPos, int rowIndex, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadButtonSprite();
        initButtonHitbox();
    }

    private void initButtonHitbox() {
        buttonHitbox = new Rectangle(xPos - xOffsetCenter, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void loadButtonSprite() {
        buttonSprite = new BufferedImage[3];
        BufferedImage temp = SaveLoad.GetSpriteAtlas(SaveLoad.MENU_BUTTONS);
        for (int i = 0; i < buttonSprite.length; i++) {
            buttonSprite[i] = temp.getSubimage(i * BUTTON_DEFAULT_WIDTH, rowIndex * BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }
    public void draw(Graphics g) {
        g.drawImage(buttonSprite[index], xPos - xOffsetCenter, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, null);
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

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getButtonHitbox() {
        return buttonHitbox;
    }

    public void setGameState() {
        GameState.state = state;

    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
}
