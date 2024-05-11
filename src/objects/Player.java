package objects;

import game.Game;
import stateOfGame.GameState;
import game.LoadImg;

import java.awt.*;
import java.awt.image.BufferedImage;

import static game.PlayerAnimations.PlayerConstants.*;
import static game.MethodsForCollisionDetection.*;

public class Player extends Entity {

    private int x, y;
    Game game;
    private float gravity = 0.04f * Game.SCALE;
    private float airTime = 0f;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 40;
    private int playerAction = IDLE;
    private boolean left,up, down, right,jump;
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

        handleJump();

        float xSpeed = calculateXSpeed();

        if (!inAir) {
            checkIfInAir();
        }

        if (checkRightBorder()) {
            return;
        }

        if (inAir) {
            handleInAirMovement(xSpeed);
        } else {
            updateXPos(xSpeed);
        }

        moving = true;
    }

    private void handleJump() {
        if (jump && !inAir) {
            jump();
        }
    }

    private float calculateXSpeed() {
        float xSpeed = 0;
        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        return xSpeed;
    }

    private void checkIfInAir() {
        if (!IsOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
    }

    private boolean checkRightBorder() {
        if (hitBox.x + hitBox.width == lvlData[0].length * Game.TILES_SIZE) {
            GameState.state = GameState.PLAYING;
            game.getPlaying().loadNextLevel();
            return true;
        }
        return false;
    }

    private void handleInAirMovement(float xSpeed) {
        if (CanMoveHere(hitBox.x, hitBox.y + airTime, hitBox.width, hitBox.height, lvlData)) {
            hitBox.y += airTime;
            airTime += gravity;
            updateXPos(xSpeed);
        } else {
            handleCollision(xSpeed);
        }
    }

    private void handleCollision(float xSpeed) {
        hitBox.y = GetYPosEntity(hitBox, airTime);
        if (airTime > 0) {
            resetInAir();
        } else {
            airTime = fallSpeedAfterCollision;
        }
        updateXPos(xSpeed);
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
            BufferedImage img = LoadImg.getSpriteImg(LoadImg.PLAYER_SPRITE);
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
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void resetAll() {
        resetDirBolleans();
        inAir = false;
        moving = false;
        playerAction = IDLE;
        hitBox.x = x;
        hitBox.y = y;
        if (!IsOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
    }
    public void setX(int x) {
        this.x = x;
        this.hitBox.x = x;
    }
    public void setY(int y) {
        this.y = y;
        this.hitBox.y = y;
    }
}
