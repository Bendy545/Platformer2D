package game;

public class GameLoop implements Runnable{
    private Game game;
    private final int FPS_SET;
    private final int UPS_SET;

    public GameLoop(Game game, int FPS_SET, int UPS_SET) {
        this.game = game;
        this.FPS_SET = FPS_SET;
        this.UPS_SET = UPS_SET;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while (true) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                game.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                game.getGamePanel().repaint();
                frames++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + ", UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }


    }
}
