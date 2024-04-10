import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 30;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;


    public GamePanel() {


        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        }

    private void loadAnimations() {
        animations = new BufferedImage[7][3];
        for (int j = 0;j < animations.length; j++) {
        for (int i = 0; i < animations[j].length; i++) {
            animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
        }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/PlayerSpriteSheet2.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
            is.close();
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animations[playerAction][animIndex], (int)xDelta, (int)yDelta,128,128, null);

    }

    private void updatePos() {

        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta-=5;
                    break;
                case UP:
                    yDelta-=5;
                    break;
                case RIGHT:
                    xDelta+=5;
                    break;
                case DOWN:
                    yDelta+=5;
                    break;
            }
        }
    }

    private void setAnimation() {

        if (moving) {
            playerAction = RUNNINGRIGHT;
        } else {
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {

        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(playerAction)) {
                animIndex = 0;
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }
}

