package stateOfGame;

import gameClasses.Game;
import levelClasses.Level;
import levelClasses.LevelHandler;
import objects.*;
import objects.Object;
import utilize.SaveLoad;

import static utilize.SaveLoad.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {

    private static final int STARTING_X_POSITION = 32;
    private static final int STARTING_Y_POSITION = 384;
    private ObjectHandler objectHandler;
    private Player player;
    private LevelHandler levelHandler;

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

    public Playing(Game game) {
        super(game);
        initClasses();
        loadFirstLevel();
    }
    public void loadNextLevel() {
        resetAll();
        levelHandler.loadNextLevel();
    }
    public void resetAll() {
        player.resetAll();
        player.setX(200);
        player.setY(200);
/*
        Level currentLevel = levelHandler.getCurrentLevel();
        int[][] levelData = currentLevel.getLvlData();
        for (int i = 0; i < Game.TILES_HEIGHt; i++) {
            for (int j = 0; j < Game.TILES_WIDTH; j++) {
                int index = levelData[i][j];
                if (index == SPIKE) {
                    Spike spike = currentLevel.getSpikes().get(i * Game.TILES_WIDTH + j);
                    spike.setX(j * Game.TILES_SIZE);
                    spike.setY(i * Game.TILES_SIZE);
                }
            }
        }

 */
    }
    public void resetPlayerPosition() {
        player.setX(STARTING_X_POSITION);
        player.setY(STARTING_Y_POSITION);
    }

    private void loadFirstLevel() {
        levelHandler.setCurrentLevel(0);
    }

    private void initClasses() {
        levelHandler = new LevelHandler(game);
        objectHandler = new ObjectHandler(this, levelHandler);
        player = new Player(200, 200, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
        player.loadlvlData(levelHandler.getCurrentLevel().getLvlData());

    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBolleans();
    }

    @Override
    public void update() {
        if (GameState.state == GameState.PLAYING) {
            levelHandler.update();
            player.update();
            objectHandler.checkSpikeHit(player);
            objectHandler.checkPassageHit(player);
        }
    }

    @Override
    public void draw(Graphics g) {
        levelHandler.draw(g);
        player.render(g);
        objectHandler.draw(g);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
            player.setLeft(true);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            player.setAttacking(true);
            player.setRight(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public void checkHitSpike(Player p) {
        objectHandler.checkSpikeHit(p);
    }
    public ObjectHandler getObjectHandler() {
        return objectHandler;
    }
}
