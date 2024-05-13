package objects;

import game.Game;

import java.awt.geom.Rectangle2D;

public class Object {

    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;

    int xDrawOffset, yDrawOffset;

    public Object(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }



}
