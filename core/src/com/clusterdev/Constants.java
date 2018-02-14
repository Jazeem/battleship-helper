package com.clusterdev;

/**
 * Created by jazeem on 22/12/17.
 */

public class Constants {
    public enum GRID_STATE {
        NOT_FIRED, MISSED, HIT, MARKED, WRECKED
    }
    public static final int RECT_SIZE = 20;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 300;
    public enum GAME_STATE {
        LOBBY, ARRANGE, PLAYING, FULL
    }
    public static final int xOffset = 40, yOffset = 40;
}
