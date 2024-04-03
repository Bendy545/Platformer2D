import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 30;


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
        animations = new BufferedImage[4][3];
        for (int j = 0;j < animations.length; j++) {
        for (int i = 0; i < animations[j].length; i++) {
            animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
        }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Player_sprites.png");

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


    public void changeXDelta(int value) {
        this.xDelta += value;

    }
    public void changeYDelta(int value) {
        this.yDelta += value;

    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        updateAnimationTick();

        g.drawImage(animations[1][animIndex], (int)xDelta, (int)yDelta,96,96, null);

    }

    private void updateAnimationTick() {

        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= 3) {
                animIndex = 0;
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }
}

