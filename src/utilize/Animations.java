package utilize;

import gameClasses.Game;

public class Animations {

    public static class UI {
        public static class Buttons {
            public static final int BUTTON_DEFAULT_WIDTH = 150;
            public static final int BUTTON_DEFAULT_HEIGHT = 70;
            public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAULT_WIDTH * Game.SCALE);
            public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Game.SCALE);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants {
        public static final int RUNNINGRIGHT= 1;
        public static final int RUNNINGLEFT = 4;
        public static final int IDLE = 0;
        public static final int JUMP = 2;
        public static final int ATTACKRIGHT = 3;
        public static final int ATTACKLEFT = 6;
        public static final int FALLING = 5;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDLE:
                    return 2;
                case RUNNINGRIGHT:
                    return 3;
                case JUMP:
                    return 3;
                case ATTACKRIGHT:
                    return 2;
                case RUNNINGLEFT:
                    return 3;
                case FALLING:
                    return 2;
                case ATTACKLEFT:
                    return 2;
                default:
                    return 1;
            }
        }
    }
}