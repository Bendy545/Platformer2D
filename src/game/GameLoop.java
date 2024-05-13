package game;

public class GameLoop implements Runnable{
    private static final long NANOSECONDS_IN_SECOND = 1_000_000_000L;

    private final Game game;
    private final int targetFPS;
    private final int targetUPS;

    public GameLoop(Game game, int targetFPS, int targetUPS) {
        this.game = game;
        this.targetFPS = targetFPS;
        this.targetUPS = targetUPS;
    }

    @Override
    public void run() {
        long nanoPerFrame = NANOSECONDS_IN_SECOND / targetFPS;
        long nanoPerUpdate = NANOSECONDS_IN_SECOND / targetUPS;

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        int frames = 0;
        int updates = 0;

        while (true) {
            long now = System.nanoTime();
            long elapsed = now - lastTime;
            deltaU += elapsed / (double) nanoPerUpdate;
            deltaF += elapsed / (double) nanoPerFrame;
            lastTime = now;

            if (deltaU >= 1.0) {
                game.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1.0) {
                game.getGamePanel().repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                printFPSAndUPS(frames, updates);
                frames = 0;
                updates = 0;
            }

            long sleepTime = (lastTime - System.nanoTime() + nanoPerFrame) / NANOSECONDS_IN_SECOND;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printFPSAndUPS(int frames, int updates) {
        System.out.println("FPS: " + frames + ", UPS: " + updates);
    }
}
