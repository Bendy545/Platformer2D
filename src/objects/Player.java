package objects;

import gameClasses.Game;
import stateOfGame.GameState;
import utilize.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilize.Animations.PlayerConstants.*;
import static utilize.MethodsForCollisionDetection.*;

public class Player extends Entity {

    private float gravity = 0.04f * Game.SCALE;
    private float airTime = 0f;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 40;
    private int playerAction = IDLE;
    private boolean left, up, right, down, jump;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 1.0f * Game.SCALE;
    private int[][] lvlData;
    private float xDrawOffset = 10 * Game.SCALE;
    private float yDrawOffset = 1 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, (int)(16 * Game.SCALE), (int)(30 * Game.SCALE));
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset), width, height, null);
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
        } else if (inAir) {
            if (airTime < 0) {
                playerAction = JUMP;
            }
        } else if (moving) {
            if (left) {
                playerAction = RUNNINGLEFT;
            } else if (right) {
                playerAction = RUNNINGRIGHT;
            } else {
                playerAction = IDLE;
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

        if (jump && !inAir)
            jump();

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;

        if(right)
            xSpeed += playerSpeed;

        if (!inAir) {
            if (!IsOnFloor(hitBox,lvlData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (CanMoveHere(hitBox.x, hitBox.y + airTime, hitBox.width, hitBox.height, lvlData)) {
                hitBox.y += airTime;
                airTime += gravity;
                updateXPos(xSpeed);
            } else {
                hitBox.y = GetYPosEntity(hitBox, airTime);
                if (airTime > 0)
                    resetInAir();
                else
                    airTime = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        }else
            updateXPos(xSpeed);
        moving = true;
    }
    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airTime = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airTime = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
        }else {
            hitBox.x = GetWallEntity(hitBox, xSpeed);
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
        if (!IsOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
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
    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
