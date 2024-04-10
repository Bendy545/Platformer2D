package utilz;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants {
        public static final int RUNNING = 1;
        public static final int IDLE = 0;
        public static final int JUMP = 2;
        public static final int ATTACK = 3;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDLE:
                    return 2;
                case RUNNING:
                    return 3;
                case JUMP:
                    return 3;
                case ATTACK:
                    return 2;
                default:
                    return 1;
            }
        }
    }
}
