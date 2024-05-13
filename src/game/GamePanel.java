package game;

import Inputs.MouseInputs;
import Inputs.KeyboardInputs;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private Game game;
    private MouseInputs mouseInputs;
    private BufferedImage backGroundImage;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        backGroundImage = LoadImg.getSpriteImg("objects&textures/backGroundImage.png");

    }
    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size: " + Game.GAME_WIDTH + " : " + Game.GAME_HEIGHT);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), null);
        game.render(g);
    }
    public Game getGame() {
        return game;
    }


}

