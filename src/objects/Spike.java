package objects;

import gameClasses.Game;

public class Spike extends Object{


    public Spike(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32, 30);
        xDrawOffset = 0;
        yDrawOffset = (int) (Game.SCALE);
        hitbox.y += yDrawOffset;
    }
}
