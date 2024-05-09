package game;

import Inputs.MouseInputs;
import Inputs.KeyboardInputs;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;
    private MouseInputs mouseInputs;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size: " + Game.GAME_WIDTH + " : " + Game.GAME_HEIGHT);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame() {
        return game;
    }


}

