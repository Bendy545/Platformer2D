package objects;

import utilz.Constants;
import utilz.SaveLoad;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 30;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int)x, (int)y,width,height, null);
        drawHitbox(g);

    }
    private void updateAnimationTick() {

        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(playerAction)) {
                animIndex = 0;
                attacking = false;
            }
        }

    }
    private void setAnimation() {

        int startAni = playerAction;
        if (attacking) {
            if (left) {
                playerAction = ATTACKLEFT;
            } else if (right) {
                playerAction = ATTACKRIGHT;
            }
        } else if (moving) {
            if (left) {
                playerAction = RUNNINGLEFT;
            } else if (right) {
                playerAction = RUNNINGRIGHT;
            }
        } else {
            playerAction = IDLE;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
        if (attacking) {
            left = false;
            right = false;
            up = false;
            down = false;
        }
    }


    private void resetAniTick() {
        animTick = 0;
        animIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {
            BufferedImage img = SaveLoad.GetSpriteAtlas(SaveLoad.PLAYER_ATLAS);
            animations = new BufferedImage[7][3];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
                }
            }
    }

    public void loadlvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void resetDirBolleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void  setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
