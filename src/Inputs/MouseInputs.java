package Inputs;

import game.GamePanel;
import stateOfGame.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case GAME_OVER:
                gamePanel.getGame().getGameOver().mousePressed(e);
            case GAME_COMPLETED:
                gamePanel.getGame().getGameCompleted().mousePressed(e);
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case GAME_OVER:
                gamePanel.getGame().getGameOver().mouseReleased(e);
            case GAME_COMPLETED:
                gamePanel.getGame().getGameCompleted().mouseReleased(e);
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case GAME_OVER:
                gamePanel.getGame().getGameOver().mouseMoved(e);
            case GAME_COMPLETED:
                gamePanel.getGame().getGameCompleted().mouseMoved(e);
            default:
                break;
        }
    }
}
