package MenuClasses;

import gameClasses.Game;
import main.GameState;
import main.State;
import main.StateMethods;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods {

    private MenuButtons[] buttons = new MenuButtons[2];

    public Menu(Game game) {
        super(game);
        loadButtons();
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
