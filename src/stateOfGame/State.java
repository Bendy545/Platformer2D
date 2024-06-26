package stateOfGame;

import ui.GameCompletedUI.GameCompletedButtons;
import ui.GameOverUI.GameOverButtons;
import ui.MenuUI.MenuButtons;
import game.Game;

import java.awt.event.MouseEvent;

public class State {

    protected Game game;
    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean OnButton(MouseEvent e, MenuButtons mb) {
        return mb.getButtonHitbox().contains(e.getX(), e.getY());
    }
    public boolean OnButton1(MouseEvent e, GameOverButtons gb) {
        return gb.getButtonHitbox().contains(e.getX(), e.getY());
    }
    public boolean OnButton2(MouseEvent e, GameCompletedButtons gb) {
        return gb.getButtonHitbox().contains(e.getX(), e.getY());
    }
}
