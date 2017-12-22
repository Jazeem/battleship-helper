package com.clusterdev.gameobjects;

import com.clusterdev.Constants;

/**
 * Created by jazeem on 22/12/17.
 */

public class Grid {
    int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Constants.GRID_STATE getState() {
        return state;
    }

    Constants.GRID_STATE state;
    public Grid(int x, int y, Constants.GRID_STATE state){
        this.x = x;
        this.y = y;
        this.state = state;
    }
}
