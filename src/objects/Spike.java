package objects;

public class Spike extends Entity{

    public Spike(float x, float y, int width, int height) {
        super(x, y, width, height);
        initHitbox(x, y, width, height);
    }
}
