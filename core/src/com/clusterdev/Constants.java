package com.clusterdev;

/**
 * Created by jazeem on 22/12/17.
 */

public class Constants {
    public enum GRID_STATE {
        NOT_FIRED, MISSED, HIT, MARKED, WRECKED
    }
//    public static final int RECT_SIZE = 40;
//    public static final int GAME_WIDTH = 960;
//    public static final int GAME_HEIGHT = 540;
//    public enum GAME_STATE {
//        LOBBY, ARRANGE, PLAYING, FULL
//    }
//    public static final int xOffset = 20, yOffset = 20;
//    public static final int xOffsetEnemy = 450, yOffsetEnemy = 20;
//    public static final int B1X_OFFSET = 40;
//    public static final int B1Y_OFFSET = 40;
//    public static final int B2X_OFFSET = 500;
//    public static final int B2Y_OFFSET = 40;
    public static final int RECT_SIZE = 80;
    public static final int GAME_WIDTH = 1920;
    public static final int GAME_HEIGHT = 1080;
    public enum GAME_STATE {
        LOBBY, ARRANGE, PLAYING, FULL
    }
    public static final int xOffset = 40, yOffset = 40;
    public static final int xOffsetEnemy = 900, yOffsetEnemy = 40;
    public static final int B1X_OFFSET = 80;
    public static final int B1Y_OFFSET = 80;
    public static final int B2X_OFFSET = 1000;
    public static final int B2Y_OFFSET = 80;
}
