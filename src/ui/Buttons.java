package ui;

import game.Game;

public class Buttons {

    public static class GameCompletedButtons {
        public static final int BUTTON_DEFAUlT_WIDTH = 100;
        public static final int BUTTON_DEFAULT_HEIGHT = 80;
        public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Game.SCALE);
        public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAUlT_WIDTH * Game.SCALE);
    }
    public static class GameOverButtons {
        public static final int BUTTON_DEFAUlT_WIDTH = 100;
        public static final int BUTTON_DEFAULT_HEIGHT = 80;
        public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Game.SCALE);
        public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAUlT_WIDTH * Game.SCALE);
    }
    public static class MenuButtons {
        public static final int BUTTON_DEFAULT_WIDTH = 150;
        public static final int BUTTON_DEFAULT_HEIGHT = 70;
        public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAULT_WIDTH * Game.SCALE);
        public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Game.SCALE);
    }
}

