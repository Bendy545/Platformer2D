package MenuClasses;

import gameClasses.Game;
import stateOfGame.GameState;
import stateOfGame.State;
import stateOfGame.StateMethods;
import utilize.SaveLoad;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {

    private BufferedImage backgoundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    private MenuButtons[] buttons = new MenuButtons[2];

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackgound();
    }

    private void loadBackgound() {
        backgoundImg = SaveLoad.GetSpriteAtlas(SaveLoad.MENU_BACKGROUND);
        menuWidth = (int) (backgoundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgoundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (50 * Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButtons(Game.GAME_WIDTH / 2,(int)(150 * Game.SCALE) , 0, GameState.PLAYING);
        buttons[1] = new MenuButtons(Game.GAME_WIDTH / 2,(int)(240 * Game.SCALE) , 1, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButtons mb : buttons) {
            mb.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgoundImg, menuX, menuY, menuWidth, menuHeight, null);
        for (MenuButtons mb : buttons) {
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButtons mb : buttons) {
            if (OnButton(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButtons mb : buttons) {
            mb.setMouseOver(false);
        }
        for (MenuButtons mb : buttons) {
            if (OnButton(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButtons mb : buttons) {
            if (OnButton(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.setGameState();
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButtons mb : buttons) {
            mb.resetBooleans();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
