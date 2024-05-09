package stateOfGame;

public enum GameState {

    PLAYING, MENU, QUIT, GAME_OVER, PLAY_AGAIN, GAME_COMPLETED;

    public static GameState state = MENU;
}
