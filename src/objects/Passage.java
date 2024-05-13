package objects;

public class Passage extends Object{

    public Passage(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32, 32);
    }
}
