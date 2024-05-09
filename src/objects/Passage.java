package objects;

public class Passage extends Object{

    private String nextLevelFileName;

    public Passage(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32, 32);
    }
}
