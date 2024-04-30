package objects;

import gameClasses.Game;
import levelClasses.Level;
import stateOfGame.Playing;
import utilize.SaveLoad;
import static utilize.SaveLoad.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }
}
