package objects;

public class Passage extends Object{

    private String nextLevelFileName;

    public Passage(int x, int y, int objType) {
        super(x, y, objType);
        this.nextLevelFileName = nextLevelFileName;
        initHitbox(32, 32);
    }

    public String getNextLevelFileName() {
        return nextLevelFileName;
    }
}
